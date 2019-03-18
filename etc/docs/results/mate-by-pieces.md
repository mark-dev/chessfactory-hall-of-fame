### Mate by knight promotion

_tip: results ordered by losed side material advantage_

```sql
select  url || '#' || toString(plies) as mateUrl,materialFromMatedSideView,gameType, blackElo,whiteElo
from game_aggregates
where matePiece == 'N' and
  mateByPromotion = 1
order by materialFromMatedSideView desc
LIMIT 25;
```

| #  | mateUrl                          | materialFromMatedSideView | gameType | blackElo | whiteElo | 
|----|----------------------------------|---------------------------|----------|----------|----------| 
| 1  | https://lichess.org/E8kqcHIF#67  | 1225                      | 2        | 1888     | 1912     | 
| 2  | https://lichess.org/oKgxoBoi#55  | 1175                      | 2        | 2006     | 2059     | 
| 3  | https://lichess.org/cfaemRL5#93  | 1050                      | 3        | 1488     | 1862     | 
| 4  | https://lichess.org/HyEOfGBn#63  | 1025                      | 2        | 1853     | 1873     | 
| 5  | https://lichess.org/naM6PAtT#100 | 1000                      | 1        | 1701     | 1727     | 
| 6  | https://lichess.org/eGZb71Lo#111 | 1000                      | 2        | 1810     | 1948     | 
| 7  | https://lichess.org/U48e0kbn#97  | 975                       | 2        | 1989     | 2012     | 
| 8  | https://lichess.org/LiM3s8Rj#88  | 900                       | 2        | 2069     | 2025     | 
| 9  | https://lichess.org/GcXGOp5T#103 | 875                       | 2        | 1787     | 1783     | 
| 10 | https://lichess.org/YGO33n0d#83  | 875                       | 2        | 1566     | 1581     | 
| 11 | https://lichess.org/aYu19KfP#94  | 825                       | 3        | 1467     | 1611     | 
| 12 | https://lichess.org/Xiyn5RG7#119 | 800                       | 2        | 1240     | 1141     | 
| 13 | https://lichess.org/wghyNNGJ#51  | 800                       | 3        | 1127     | 1073     | 
| 14 | https://lichess.org/SnHgdRs5#87  | 800                       | 2        | 1697     | 1642     | 
| 15 | https://lichess.org/PcB4hxIi#55  | 725                       | 2        | 1927     | 1902     | 
| 16 | https://lichess.org/ihbNUJoC#108 | 725                       | 3        | 1912     | 1947     | 
| 17 | https://lichess.org/aVb5sISb#78  | 700                       | 1        | 1803     | 1725     | 
| 18 | https://lichess.org/L8B0JGgN#75  | 700                       | 1        | 2102     | 2310     | 
| 19 | https://lichess.org/aIUAfcT4#103 | 675                       | 2        | 1734     | 1897     | 
| 20 | https://lichess.org/Vgmddkmi#65  | 675                       | 2        | 1645     | 1525     | 
| 21 | https://lichess.org/emqkzOKe#91  | 675                       | 2        | 1731     | 1830     | 
| 22 | https://lichess.org/RiJDkhyM#82  | 625                       | 2        | 1981     | 1778     | 
| 23 | https://lichess.org/LDCWkgeX#64  | 625                       | 2        | 1523     | 1497     | 
| 24 | https://lichess.org/PKjE6aF4#83  | 625                       | 2        | 1570     | 1681     | 
| 25 | https://lichess.org/LlDaqWu0#88  | 625                       | 3        | 1168     | 1020     | 



### By bishop promotion

| #  | mateUrl                          | materialFromMatedSideView | gameType | blackElo | whiteElo | 
|----|----------------------------------|---------------------------|----------|----------|----------| 
| 1  | https://lichess.org/V7qf9NjK#67  | 875                       | 2        | 2000     | 1734     | 
| 2  | https://lichess.org/oBizPvMq#63  | 800                       | 1        | 1690     | 1734     | 
| 3  | https://lichess.org/ShblS3xt#50  | 700                       | 3        | 1673     | 1689     | 
| 4  | https://lichess.org/0YdY7cTV#73  | 675                       | 1        | 1612     | 1602     | 
| 5  | https://lichess.org/mcIsDWg4#130 | 575                       | 2        | 1799     | 1739     | 
| 6  | https://lichess.org/cm0mfLdr#29  | 575                       | 2        | 1568     | 1561     | 
| 7  | https://lichess.org/yq4sSFBf#68  | 500                       | 2        | 1783     | 1726     | 
| 8  | https://lichess.org/N7IFic4Y#74  | 500                       | 2        | 2209     | 2213     | 
| 9  | https://lichess.org/AodhdHrF#69  | 475                       | 1        | 1576     | 1554     | 
| 10 | https://lichess.org/xuCGMSyZ#91  | 400                       | 2        | 1756     | 1890     | 
| 11 | https://lichess.org/8BlNxuZi#66  | 375                       | 2        | 2100     | 2337     | 
| 12 | https://lichess.org/qYncDbp5#43  | 275                       | 3        | 1298     | 1055     | 
| 13 | https://lichess.org/pUumWvA4#48  | 275                       | 2        | 1820     | 1957     | 
| 14 | https://lichess.org/IBJ81WeU#102 | 275                       | 2        | 2386     | 2297     | 
| 15 | https://lichess.org/fSJw7NIZ#92  | 275                       | 2        | 2014     | 1945     | 
| 16 | https://lichess.org/Q8ApWLx5#94  | 200                       | 3        | 1653     | 1960     | 
| 17 | https://lichess.org/j8cEqPMn#84  | 200                       | 3        | 1775     | 1796     | 
| 18 | https://lichess.org/9GhLzjWN#59  | 175                       | 2        | 1859     | 1899     | 
| 19 | https://lichess.org/dbLxqWRk#66  | 175                       | 3        | 1365     | 1315     | 
| 20 | https://lichess.org/Mu2yaGeb#77  | 175                       | 2        | 1795     | 1296     | 
| 21 | https://lichess.org/sYd3NdOO#78  | 175                       | 2        | 1770     | 2142     | 
| 22 | https://lichess.org/IvSmTbwP#63  | 150                       | 3        | 1604     | 1321     | 
| 23 | https://lichess.org/n5R5eDB3#55  | 100                       | 2        | 1989     | 1995     | 
| 24 | https://lichess.org/BP9hgr3z#54  | 75                        | 2        | 2027     | 1986     | 
| 25 | https://lichess.org/tluSckUe#65  | 75                        | 2        | 1865     | 1761     | 



### by rook promotion

| #  | mateUrl                          | materialFromMatedSideView | gameType | blackElo | whiteElo | 
|----|----------------------------------|---------------------------|----------|----------|----------| 
| 1  | https://lichess.org/2VpSuY5F#83  | 2000                      | 1        | 1754     | 1788     | 
| 2  | https://lichess.org/9DnZ6SY9#94  | 1900                      | 2        | 1872     | 1843     | 
| 3  | https://lichess.org/zuu33w5f#65  | 1725                      | 2        | 1504     | 1533     | 
| 4  | https://lichess.org/Whxp6wwf#62  | 1650                      | 2        | 884      | 800      | 
| 5  | https://lichess.org/B4e2jCe0#71  | 1625                      | 3        | 1286     | 1620     | 
| 6  | https://lichess.org/bWftujMb#72  | 1525                      | 0        | 1488     | 1279     | 
| 7  | https://lichess.org/q79FFQ3i#74  | 1500                      | 1        | 1306     | 1371     | 
| 8  | https://lichess.org/WDfdmBMA#55  | 1500                      | 2        | 2033     | 2029     | 
| 9  | https://lichess.org/qclwwG9B#109 | 1500                      | 0        | 1500     | 1500     | 
| 10 | https://lichess.org/uflVmEXk#72  | 1425                      | 1        | 1371     | 1376     | 
| 11 | https://lichess.org/g8BRCDn0#97  | 1400                      | 1        | 1622     | 1605     | 
| 12 | https://lichess.org/L4yqRqVf#89  | 1375                      | 2        | 1532     | 1491     | 
| 13 | https://lichess.org/8Ysli9kq#93  | 1325                      | 2        | 1750     | 1784     | 
| 14 | https://lichess.org/N4MVDwnA#106 | 1300                      | 2        | 1437     | 1742     | 
| 15 | https://lichess.org/lTtfoh4o#111 | 1300                      | 2        | 1805     | 1746     | 
| 16 | https://lichess.org/gpczT9X6#66  | 1300                      | 3        | 1722     | 1743     | 
| 17 | https://lichess.org/m8rw4syt#100 | 1300                      | 2        | 1543     | 1466     | 
| 18 | https://lichess.org/UdQ2rIAN#68  | 1300                      | 3        | 1051     | 1224     | 
| 19 | https://lichess.org/CzJQl3VR#74  | 1225                      | 1        | 1794     | 1659     | 
| 20 | https://lichess.org/CzJQl3VR#74  | 1225                      | 1        | 1794     | 1659     | 
| 21 | https://lichess.org/BxIKzgrU#45  | 1225                      | 3        | 1613     | 1626     | 
| 22 | https://lichess.org/BRs696ni#81  | 1225                      | 2        | 1126     | 1153     | 
| 23 | https://lichess.org/YBe6XILC#56  | 1225                      | 2        | 1273     | 1353     | 
| 24 | https://lichess.org/FuhNrGEM#65  | 1225                      | 1        | 1360     | 1223     | 
| 25 | https://lichess.org/tgmmsp7G#65  | 1200                      | 3        | 1585     | 1381     | 
 


#### Bonus: pawn mates

| #  | mateUrl                          | materialFromMatedSideView | gameType | blackElo | whiteElo | 
|----|----------------------------------|---------------------------|----------|----------|----------| 
| 1  | https://lichess.org/yWzjvFI2#107 | 4400                      | 1        | 1945     | 2068     | 
| 2  | https://lichess.org/8wZnYQHf#87  | 4300                      | 2        | 949      | 950      | 
| 3  | https://lichess.org/ReyHEI41#46  | 4150                      | 2        | 2198     | 2041     | 
| 4  | https://lichess.org/0wUjU6ge#49  | 3850                      | 2        | 1474     | 1472     | 
| 5  | https://lichess.org/jotskE9F#65  | 3550                      | 2        | 1435     | 1457     | 
| 6  | https://lichess.org/TLsKKaq4#75  | 3525                      | 2        | 947      | 1221     | 
| 7  | https://lichess.org/itftPGGi#118 | 3425                      | 3        | 1345     | 1143     | 
| 8  | https://lichess.org/3iYXHCPE#60  | 3400                      | 2        | 1320     | 1307     | 
| 9  | https://lichess.org/UTckoUyx#26  | 3325                      | 1        | 1802     | 1487     | 
| 10 | https://lichess.org/OFIRIjUt#53  | 3125                      | 0        | 1500     | 1500     | 
| 11 | https://lichess.org/ub1cizbR#73  | 3125                      | 1        | 1106     | 1526     | 
| 12 | https://lichess.org/fiqRd8HB#39  | 3050                      | 2        | 1023     | 1015     | 
| 13 | https://lichess.org/SfRG38A2#67  | 3025                      | 1        | 1833     | 1480     | 
| 14 | https://lichess.org/wEH4km2O#91  | 3025                      | 1        | 1382     | 1309     | 
| 15 | https://lichess.org/xN9jguFL#50  | 2950                      | 1        | 1793     | 1465     | 
| 16 | https://lichess.org/2jIDLQKP#74  | 2925                      | 2        | 1228     | 1296     | 
| 17 | https://lichess.org/DnMbKwiO#105 | 2900                      | 3        | 1623     | 1632     | 
| 18 | https://lichess.org/IeBLTugK#81  | 2850                      | 1        | 1527     | 1571     | 
| 19 | https://lichess.org/oqWdnpy5#31  | 2825                      | 2        | 1421     | 1390     | 
| 20 | https://lichess.org/FlvbgrJj#107 | 2800                      | 2        | 1236     | 1261     | 
| 21 | https://lichess.org/J9o6RoeR#44  | 2750                      | 1        | 1524     | 1502     | 
| 22 | https://lichess.org/pxq4KOvJ#68  | 2725                      | 2        | 1052     | 1030     | 
| 23 | https://lichess.org/LZi0zuOS#28  | 2700                      | 1        | 1660     | 1857     | 
| 24 | https://lichess.org/CbD2a5Ap#66  | 2650                      | 2        | 976      | 941      | 
| 25 | https://lichess.org/gokql83F#61  | 2650                      | 4        | 1038     | 1009     | 

