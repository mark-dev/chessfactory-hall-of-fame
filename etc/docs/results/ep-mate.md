### Mate by en passant (including discovered mates)
```sql
select url || '#' || toString(plies) as mateUrl, blackElo,whiteElo
from game_aggregates
where epMate = 1
  and avgRating > 2000
order by materialFromMatedSideView desc
limit 25;
```

| #  | mateUrl                         | blackElo | whiteElo | 
|----|---------------------------------|----------|----------| 
| 1  | https://lichess.org/hND1G90l#37 | 2195     | 2154     | 
| 2  | https://lichess.org/xsy1SRcK#54 | 2162     | 2433     | 
| 3  | https://lichess.org/Vv4WkDc1#84 | 2056     | 2113     | 
| 4  | https://lichess.org/3VkpZJ0j#59 | 2123     | 2071     | 
| 5  | https://lichess.org/cxubq0xe#51 | 2200     | 1983     | 
| 6  | https://lichess.org/mYgdlKE4#48 | 2189     | 2184     | 
| 7  | https://lichess.org/7T8RwDmi#65 | 2278     | 2165     | 
| 8  | https://lichess.org/v3Ht7WNr#74 | 2159     | 2164     | 
| 9  | https://lichess.org/Ar2RIkEL#78 | 2153     | 2271     | 
| 10 | https://lichess.org/5qhBCGVN#73 | 2004     | 2113     | 
| 11 | https://lichess.org/LU0FPwy5#48 | 2103     | 2008     | 
| 12 | https://lichess.org/ohZA8iBH#75 | 1984     | 2026     | 
| 13 | https://lichess.org/rUxtooH2#69 | 1992     | 2056     | 
| 14 | https://lichess.org/mg1u1F68#63 | 2331     | 1892     | 
| 15 | https://lichess.org/KglkXSNS#47 | 2108     | 1965     | 
| 16 | https://lichess.org/AbtuOd5H#65 | 2048     | 1988     | 
| 17 | https://lichess.org/YSJFuUU0#67 | 2268     | 1914     | 
| 18 | https://lichess.org/u5P9BO4U#55 | 2132     | 2060     | 
| 19 | https://lichess.org/rFC8XfTu#27 | 2212     | 2209     | 
| 20 | https://lichess.org/zasmHSDY#27 | 2133     | 2055     | 
| 21 | https://lichess.org/cmCmtzka#62 | 2008     | 2039     | 
| 22 | https://lichess.org/k0tg529l#31 | 2162     | 2194     | 
| 23 | https://lichess.org/krjomsQy#37 | 2102     | 2119     | 
| 24 | https://lichess.org/ZOjs3nuc#33 | 2175     | 2130     | 
| 25 | https://lichess.org/yWQyxiVB#47 | 2068     | 2172     | 
