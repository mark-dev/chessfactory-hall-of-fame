package ru.chessfactory.hof.core.util;

import lombok.SneakyThrows;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.springframework.messaging.MessagingException;

import java.io.*;
import java.util.Scanner;

public class BZipPNGReader implements Closeable {
    protected Scanner sc;
    protected int readedGames;

    protected Long readLimit;


    public static BZipPNGReader of(InputStream is, Long readLimit) {
        return new BZipPNGReader(is, readLimit);
    }

    @SneakyThrows
    public static BZipPNGReader of(File f, Long readLimit) {
        return new BZipPNGReader(new FileInputStream(f), readLimit);
    }

    @SneakyThrows
    public static BZipPNGReader of(String fileName, Long readLimit) {
        File database = new File(fileName);
        return new BZipPNGReader(new FileInputStream(database), readLimit);
    }

    @SneakyThrows
    private BZipPNGReader(InputStream is, Long readLimit) {
        this.readLimit = readLimit;

        InputStream res = new BZip2CompressorInputStream(is);
        sc = new Scanner(res, "utf-8").useDelimiter("\n");
    }

    private boolean limitNotReached() {
        if (readLimit < 0)
            return true;
        else
            return readedGames <= readLimit;
    }

    public ByteArrayInputStream readNextGame() {
        try {
            ByteArrayOutputStream baos = null;
            while (limitNotReached() && sc.hasNext()) {
                if (baos == null) {
                    baos = new ByteArrayOutputStream();
                }
                String str = sc.next();

                //TODO: - Тут проблема в том,что lichess по разному формирует PNG файлы
                // - Которые на странице с database, и которые можно скачать по запросу пользователя
                // - Подумать, возможно можно как-то более элегантно добиться того же самого
                if (str.equals("")) {
                    baos.write("\n".getBytes());
                } else {
                    baos.write(str.getBytes());
                    baos.write("\n".getBytes());

                    if (!str.startsWith("[")) {
                        //Значит мы только что зачитали саму строку с PNG нотацией, готовы
                        ByteArrayInputStream pngBytes = new ByteArrayInputStream(baos.toByteArray());
                        readedGames++;
                        return pngBytes;
                    }
                }
            }
            sc.close();
            return null;
        } catch (IOException ex) {
            throw new MessagingException("Failed to read file", ex);
        }
    }

    @Override
    public void close() throws IOException {
        sc.close();
    }
}
