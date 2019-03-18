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