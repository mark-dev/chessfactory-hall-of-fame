package ru.chessfactory.pgn.analysis.test;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;

@Slf4j
public class PGNPlaybackTest {

    @Test
    @SneakyThrows
    public void test() {
        int LIMIT = 20000;
//        File f = new File("/media/mark/DATAPART1/lichess_db_standard_rated_2018-12.pgn");
//        File f = new File("/media/mark/DATAPART1/lichess_gensrv_2019-01-28.pgn");
//        File f = new File("/home/mark/code/opensource/pgn-analysis/src/test/resources/3.pgn");
        File f = new File("/home/mark/code/opensource/pgn-analysis/src/test/resources/buggy.pgn");
        SimpleDebugPNGScanner pngScanner = new SimpleDebugPNGScanner(f);
        pngScanner.scan(LIMIT);

        log.info("Most frags: " + pngScanner.mostFragsGame + "\t" + pngScanner.mvp);
        log.info("Most ranged: {} is {}", pngScanner.mostRangedGame, pngScanner.maxRangedDst);
    }

}
