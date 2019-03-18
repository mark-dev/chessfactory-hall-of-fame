truncate table game_aggregates;


--Create table

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

--Total count
select count(*) from game_aggregates;

-- One column pieces
select url || '#' || toString(allPiecesInColumnPly) as directLinkMove,
       blackElo,
       whiteElo,
       allPiecesInColumnPly,
       allPiecesInColumnCol,
       allPiecesInColMaterialValue
from game_aggregates
WHERE
      avgRating > 2000 and
      allPiecesInColumnPly != 0
ORDER BY allPiecesInColMaterialValue DESC ,avgRating DESC
limit 25;


select
       allPiecesInColumnCol,
       round(avg(allPiecesInColMaterialValue)) as avgMaterial,
       min(allPiecesInColMaterialValue) as minMaterial,
       max(allPiecesInColMaterialValue) as maxMaterial
from game_aggregates
WHERE
     -- avgRating > 2000 and
      allPiecesInColumnPly != 0
GROUP BY allPiecesInColumnCol;


-- Mates sorted by defeat side piece advantage
select url || '#' || toString(plies) as mateUrl,materialFromMatedSideView,gameType,blackElo,whiteElo
from game_aggregates
where avgRating > 1800
order by materialFromMatedSideView desc
limit 25;


select url || '#' || toString(plies) as mateUrl,materialFromMatedSideView,gameType, blackElo,whiteElo
from game_aggregates
--where avgRating > 1800
order by materialFromMatedSideView desc
LIMIT 100;

--en passant moves
select url,blackElo,whiteElo,epMoves
from game_aggregates
--WHERE avgRating > 1800
order by epMoves DESC
limit 25;

--Ranged capture distance
select url,avgRangedCaptureDistance,rangedCaptureMoves,blackElo,whiteElo
from game_aggregates
WHERE avgRating > 1800 and rangedCaptureMoves > 20
order by avgRangedCaptureDistance desc
limit 25;

-- ep mates
select url || '#' || toString(plies) as mateUrl, blackElo,whiteElo
from game_aggregates
where epMate = 1
  and avgRating > 2000
order by materialFromMatedSideView desc
limit 25;

-- mvp pieces
select mvpType,max(mvpFrags) as maxMvpFrags
from game_aggregates
group by mvpType

-- Fork by fork amount
select (url || '#' || toString(maxForkMaterialAmountPly)) as directLinkMove,
       maxForkMaterialAmount,
       blackElo,
       whiteElo,
       maxForkMaterialAmountPieces
from game_aggregates
WHERE not has(maxForkMaterialAmountPieces,cast('N' as FixedString(1)))
order by maxForkMaterialAmount DESC
limit 100;

--Longest uncrossed knight path
select url,knightLongestPath,longestKnightInitialPosition, knightLongestPathDetails
from game_aggregates
order by knightLongestPath DESC
limit 25;

--Capture sharpness
select maxCaptureSharpness,
       maxCaptureSharpnessPly,
       url || '#' || toString(maxCaptureSharpnessPly)
from game_aggregates
ORDER BY maxCaptureSharpness DESC
limit 25;

--pawn mates
select  url || '#' || toString(plies) as mateUrl,materialFromMatedSideView,gameType, blackElo,whiteElo
from game_aggregates
where matePiece == 'P'
order by materialFromMatedSideView desc
LIMIT 25;




--count statistics per filename
select fileName,count()
from game_aggregates
group by fileName
order by fileName