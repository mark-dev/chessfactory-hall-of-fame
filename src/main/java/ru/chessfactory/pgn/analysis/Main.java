package ru.chessfactory.pgn.analysis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class Main implements CommandLineRunner {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job lichessParseJob;

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Override
    public void run(String... args) throws Exception {
        long before = System.currentTimeMillis();

        Map<String, JobParameter> parameterMap = new HashMap<>();
        parameterMap.put("readLimit", new JobParameter(500000L));
        parameterMap.put("fileName", new JobParameter("/media/mark/DATAPART1/lichess_db_standard_rated_2018-12.pgn.bz2"));

        JobParameters jobParameters = new JobParameters(parameterMap);

        jobLauncher.run(lichessParseJob, jobParameters);
        long takes = System.currentTimeMillis() - before;

        log.info("Task takes: {} ms",takes);
    }
}
