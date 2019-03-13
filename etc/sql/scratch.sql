truncate table game_aggregates;


--Создание таблицы

drop table game_aggregates;
create table if not exists game_aggregates
(
  url                          String,
  insertTime                   DateTime DEFAULT now(),
  utc                          DateTime,
  fileName                     String,
  whiteElo                     UInt16,
  blackElo                     UInt16,
  whitePlayer                  String,
  blackPlayer                  String,
  avgRating                    UInt16   DEFAULT (whiteElo + blackElo) / 2,
  gameType                     UInt8,
  plies                        UInt16,

  epMoves                      UInt16,
  epMate                       UInt8,
  avgRangedCaptureDistance     Float64,
  promotionCount               UInt8,
  maxCaptureSharpness          UInt8,
  maxCaptureSharpnessPly       UInt16,
  matePiece                    FixedString(1),
  mateByPromotion              UInt8,
  rangedCaptureMoves           UInt16,
  mvpFrags                     UInt8,
  mvpInitialPosition           FixedString(2),
  mvpType                      FixedString(1),
  knightLongestPath            UInt8,
  knightLongestPathDetails     Array(FixedString(2)),
  longestKnightInitialPosition FixedString(2),
  maxForkMaterialAmount        UInt16,
  maxForkMaterialAmountPieces  Array(FixedString(1)),
  maxForkMaterialAmountPly     UInt16,

  allPiecesInColumnPly         UInt16,
  allPiecesInColumnCol         UInt8,
  allPiecesInColMaterialValue  UInt16,
  materialFromMatedSideView    Int16
)
  engine = ReplacingMergeTree(insertTime)
    PARTITION BY (toStartOfMonth(utc))
    ORDER BY (avgRating, plies, gameType, url);

--Индексы, нужен нативный клиент
SET allow_experimental_data_skipping_indices = 1;
ALTER TABLE game_aggregates
  ADD INDEX maxForkMaterialAmountIndex(maxForkMaterialAmount) TYPE minmax GRANULARITY 3;

--Общее кол-во
select count()
from game_aggregates;
--Фигуры в одну вертикаль стоят
select url || '#' || toString(allPiecesInColumnPly) as directLinkMove,
       blackElo,
       whiteElo,
       allPiecesInColumnPly,
       allPiecesInColumnCol
from game_aggregates
WHERE allPiecesInColumnPly != 0
  and allPiecesInColumnCol = 7
ORDER BY avgRating DESC
limit 25;

--Когда заматовали, а у проигравшей стороны много лишнего материала
--TODO: Включить условие поиска по заматовавшей фигуре
select materialFromMatedSideView,url,gameType,blackElo,whiteElo
from game_aggregates
WHERE avgRating > 1800
      --and gameType = 3
order by materialFromMatedSideView desc
limit 10;

--Взятие на проходе
select url,blackElo,whiteElo,epMoves
from game_aggregates
WHERE blackElo > 2000
  and whiteElo > 2000
order by epMoves DESC
limit 5;

--Длинные взятия дальнобойными фигурами
select url,avgRangedCaptureDistance,rangedCaptureMoves,blackElo,whiteElo
from game_aggregates
WHERE rangedCaptureMoves > 5
  and blackElo > 2000
  and whiteElo > 2000
order by rangedCaptureMoves desc,avgRangedCaptureDistance desc
limit 20;

-- Мат в следствии взятия на проходе
-- TODO Использовать заматовавшую фигуру, чтобы отсечь скрытые шахи
select url, blackElo,whiteElo
from game_aggregates
where epMate = 1
  and avgRating > 2000
order by materialFromMatedSideView desc
limit 5;

-- Фигура с наибольшим кол-вом фрагов
select url,mvpFrags,mvpType,mvpInitialPosition
from game_aggregates
WHERE avgRating > 2000
  and gameType > 3
order by mvpFrags desc
limit 10;

-- Вилка с наибольшим значением материала
select (url || '#' || toString(maxForkMaterialAmountPly)) as directLinkMove,
       maxForkMaterialAmount,
       blackElo,
       whiteElo,
       maxForkMaterialAmountPieces
from game_aggregates
WHERE avgRating > 2000 and not has(maxForkMaterialAmountPieces,cast('N' as FixedString(1)))
order by maxForkMaterialAmount DESC
limit 100;

--Путь коня без повторений
select url,knightLongestPath,longestKnightInitialPosition, knightLongestPathDetails
from game_aggregates
order by knightLongestPath DESC
limit 5;

--Позиции в которых много взятий
select maxCaptureSharpness,
       maxCaptureSharpnessPly,
       url || '#' || toString(maxCaptureSharpnessPly)
from game_aggregates
ORDER BY maxCaptureSharpness DESC
limit 5;

--Мат путем превращения в коня(или др. фигуру)
select url,materialFromMatedSideView,gameType, blackElo,whiteElo
from game_aggregates
where mateByPromotion = 1
  and matePiece == 'N'
  and avgRating > 2000
order by materialFromMatedSideView desc
LIMIT 5;

select fileName,count()
from game_aggregates
group by fileName
order by fileName

select *
from game_aggregates
where url = 'https://lichess.org/vmMB0SGL'