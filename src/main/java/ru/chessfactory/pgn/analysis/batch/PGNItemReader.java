package ru.chessfactory.pgn.analysis.batch;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.io.*;
import java.util.Scanner;


public class PGNItemReader implements ItemReader<ByteArrayInputStream> {

    private Scanner sc;
    private int readedGames;

    private Long readLimit;
    private String fileName;


    public PGNItemReader(String fileName, Long readLimit) throws IOException {
        this.fileName = fileName;
        this.readLimit = readLimit;

        File database = new File(fileName);
        InputStream res = new BZip2CompressorInputStream(new FileInputStream(database));
        sc = new Scanner(res, "utf-8").useDelimiter("\n");
    }

    @Override
    public ByteArrayInputStream read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
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
    }
}
