package ru.chessfactory.pgn.analysis.integration;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.springframework.integration.endpoint.AbstractMessageSource;
import org.springframework.messaging.MessagingException;

import java.io.*;
import java.util.Scanner;

public class PGNMessageSource extends AbstractMessageSource<ByteArrayInputStream> {
    private Scanner sc;
    private int readedGames;

    private Long readLimit;
    private String fileName;

    public PGNMessageSource(String fileName, Long readLimit) {
        this.fileName = fileName;
        this.readLimit = readLimit;
        try {
            File database = new File(fileName);
            InputStream res = new BZip2CompressorInputStream(new FileInputStream(database));
            sc = new Scanner(res, "utf-8").useDelimiter("\n");
        } catch (IOException ex) {
            throw new RuntimeException("io while opening file", ex);
        }

    }

    @Override
    protected Object doReceive() {
        try {
            if (readLimit > 0 && readedGames >= readLimit) return null;

            //Try read game
            ByteArrayOutputStream baos = null;
            while (sc.hasNext()) {
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
            return null;
        } catch (IOException ex) {
            throw new MessagingException("Failed to read file", ex);
        }
    }

    @Override
    public String getComponentType() {
        return "inbound-channel-adapter";
    }
}
