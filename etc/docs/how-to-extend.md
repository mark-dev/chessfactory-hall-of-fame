## Developer info

This code read *.pgn.bz2 files from lichess database, extract pgn games from it, and pass this games though processing pipeline.

During pipeline some aggregated game metrics are calculated and stored into clickhouse database in batch manner.

After file processing finish, we can use SQL to ask clickhouse 'give me games where ...'

#### Getted started

1. Install clickhouse database and setup [schema](/etc/sql/schema.sql)
2. Download some *.pgn.bz2 files from [lichess database](https://database.lichess.org/) 
3. Configure the application (eg: change pgn files directory). [Configuration file](/src/main/resource/application.yaml)
4. Run application

##### Your own handler:
1. Take look for [existing handlers](/src/main/java/ru/chessfactory/hof/core/calc/handlers) and implement your own. 

    (you need implement interface and put handler to this package)
    
2. Annotate result fields with @AggregateResultField and extend your clickhouse schema according this fields


Probably you will have to fork and extend [this](https://github.com/mark-dev/chesspresso) project, for custom position research.