### Mate by knight promotion, ordered by loosing side material advantage

```sql
select url,materialFromMatedSideView,gameType, blackElo,whiteElo
from game_aggregates
where mateByPromotion = 1
  and matePiece == 'N'
order by materialFromMatedSideView desc
LIMIT 25;
```

| #  | url                          | materialFromMatedSideView | gameType | blackElo | whiteElo | 
|----|------------------------------|---------------------------|----------|----------|----------| 
| 1  | https://lichess.org/E8kqcHIF | 1225                      | 2        | 1888     | 1912     | 
| 2  | https://lichess.org/oKgxoBoi | 1175                      | 2        | 2006     | 2059     | 
| 3  | https://lichess.org/cfaemRL5 | 1050                      | 3        | 1488     | 1862     | 
| 4  | https://lichess.org/HyEOfGBn | 1025                      | 2        | 1853     | 1873     | 
| 5  | https://lichess.org/naM6PAtT | 1000                      | 1        | 1701     | 1727     | 
| 6  | https://lichess.org/eGZb71Lo | 1000                      | 2        | 1810     | 1948     | 
| 7  | https://lichess.org/U48e0kbn | 975                       | 2        | 1989     | 2012     | 
| 8  | https://lichess.org/LiM3s8Rj | 900                       | 2        | 2069     | 2025     | 
| 9  | https://lichess.org/GcXGOp5T | 875                       | 2        | 1787     | 1783     | 
| 10 | https://lichess.org/YGO33n0d | 875                       | 2        | 1566     | 1581     | 
| 11 | https://lichess.org/aYu19KfP | 825                       | 3        | 1467     | 1611     | 
| 12 | https://lichess.org/wghyNNGJ | 800                       | 3        | 1127     | 1073     | 
| 13 | https://lichess.org/Xiyn5RG7 | 800                       | 2        | 1240     | 1141     | 
| 14 | https://lichess.org/SnHgdRs5 | 800                       | 2        | 1697     | 1642     | 
| 15 | https://lichess.org/PcB4hxIi | 725                       | 2        | 1927     | 1902     | 
| 16 | https://lichess.org/ihbNUJoC | 725                       | 3        | 1912     | 1947     | 
| 17 | https://lichess.org/aVb5sISb | 700                       | 1        | 1803     | 1725     | 
| 18 | https://lichess.org/L8B0JGgN | 700                       | 1        | 2102     | 2310     | 
| 19 | https://lichess.org/Vgmddkmi | 675                       | 2        | 1645     | 1525     | 
| 20 | https://lichess.org/emqkzOKe | 675                       | 2        | 1731     | 1830     | 
| 21 | https://lichess.org/aIUAfcT4 | 675                       | 2        | 1734     | 1897     | 
| 22 | https://lichess.org/LlDaqWu0 | 625                       | 3        | 1168     | 1020     | 
| 23 | https://lichess.org/PKjE6aF4 | 625                       | 2        | 1570     | 1681     | 
| 24 | https://lichess.org/LDCWkgeX | 625                       | 2        | 1523     | 1497     | 
| 25 | https://lichess.org/o1SvmrtW | 625                       | 4        | 1897     | 1778     | 


#### By bishop promotion

| #  | url                          | materialFromMatedSideView | gameType | blackElo | whiteElo | 
|----|------------------------------|---------------------------|----------|----------|----------| 
| 1  | https://lichess.org/V7qf9NjK | 875                       | 2        | 2000     | 1734     | 
| 2  | https://lichess.org/oBizPvMq | 800                       | 1        | 1690     | 1734     | 
| 3  | https://lichess.org/ShblS3xt | 700                       | 3        | 1673     | 1689     | 
| 4  | https://lichess.org/0YdY7cTV | 675                       | 1        | 1612     | 1602     | 
| 5  | https://lichess.org/mcIsDWg4 | 575                       | 2        | 1799     | 1739     | 
| 6  | https://lichess.org/cm0mfLdr | 575                       | 2        | 1568     | 1561     | 
| 7  | https://lichess.org/yq4sSFBf | 500                       | 2        | 1783     | 1726     | 
| 8  | https://lichess.org/N7IFic4Y | 500                       | 2        | 2209     | 2213     | 
| 9  | https://lichess.org/AodhdHrF | 475                       | 1        | 1576     | 1554     | 
| 10 | https://lichess.org/xuCGMSyZ | 400                       | 2        | 1756     | 1890     | 
| 11 | https://lichess.org/8BlNxuZi | 375                       | 2        | 2100     | 2337     | 
| 12 | https://lichess.org/qYncDbp5 | 275                       | 3        | 1298     | 1055     | 
| 13 | https://lichess.org/fSJw7NIZ | 275                       | 2        | 2014     | 1945     | 
| 14 | https://lichess.org/pUumWvA4 | 275                       | 2        | 1820     | 1957     | 
| 15 | https://lichess.org/IBJ81WeU | 275                       | 2        | 2386     | 2297     | 
| 16 | https://lichess.org/Q8ApWLx5 | 200                       | 3        | 1653     | 1960     | 
| 17 | https://lichess.org/j8cEqPMn | 200                       | 3        | 1775     | 1796     | 
| 18 | https://lichess.org/9GhLzjWN | 175                       | 2        | 1859     | 1899     | 
| 19 | https://lichess.org/Mu2yaGeb | 175                       | 2        | 1795     | 1296     | 
| 20 | https://lichess.org/dbLxqWRk | 175                       | 3        | 1365     | 1315     | 
| 21 | https://lichess.org/sYd3NdOO | 175                       | 2        | 1770     | 2142     | 
| 22 | https://lichess.org/IvSmTbwP | 150                       | 3        | 1604     | 1321     | 
| 23 | https://lichess.org/n5R5eDB3 | 100                       | 2        | 1989     | 1995     | 
| 24 | https://lichess.org/ylUXkaVT | 75                        | 2        | 1684     | 1653     | 
| 25 | https://lichess.org/tluSckUe | 75                        | 2        | 1865     | 1761     | 


##### by rook promotion

| #  | url                          | materialFromMatedSideView | gameType | blackElo | whiteElo | 
|----|------------------------------|---------------------------|----------|----------|----------| 
| 1  | https://lichess.org/2VpSuY5F | 2000                      | 1        | 1754     | 1788     | 
| 2  | https://lichess.org/9DnZ6SY9 | 1900                      | 2        | 1872     | 1843     | 
| 3  | https://lichess.org/zuu33w5f | 1725                      | 2        | 1504     | 1533     | 
| 4  | https://lichess.org/Whxp6wwf | 1650                      | 2        | 884      | 800      | 
| 5  | https://lichess.org/B4e2jCe0 | 1625                      | 3        | 1286     | 1620     | 
| 6  | https://lichess.org/bWftujMb | 1525                      | 0        | 1488     | 1279     | 
| 7  | https://lichess.org/q79FFQ3i | 1500                      | 1        | 1306     | 1371     | 
| 8  | https://lichess.org/WDfdmBMA | 1500                      | 2        | 2033     | 2029     | 
| 9  | https://lichess.org/qclwwG9B | 1500                      | 0        | 1500     | 1500     | 
| 10 | https://lichess.org/uflVmEXk | 1425                      | 1        | 1371     | 1376     | 
| 11 | https://lichess.org/g8BRCDn0 | 1400                      | 1        | 1622     | 1605     | 
| 12 | https://lichess.org/L4yqRqVf | 1375                      | 2        | 1532     | 1491     | 
| 13 | https://lichess.org/8Ysli9kq | 1325                      | 2        | 1750     | 1784     | 
| 14 | https://lichess.org/N4MVDwnA | 1300                      | 2        | 1437     | 1742     | 
| 15 | https://lichess.org/UdQ2rIAN | 1300                      | 3        | 1051     | 1224     | 
| 16 | https://lichess.org/gpczT9X6 | 1300                      | 3        | 1722     | 1743     | 
| 17 | https://lichess.org/lTtfoh4o | 1300                      | 2        | 1805     | 1746     | 
| 18 | https://lichess.org/m8rw4syt | 1300                      | 2        | 1543     | 1466     | 
| 19 | https://lichess.org/YBe6XILC | 1225                      | 2        | 1273     | 1353     | 
| 20 | https://lichess.org/FuhNrGEM | 1225                      | 1        | 1360     | 1223     | 
| 21 | https://lichess.org/CzJQl3VR | 1225                      | 1        | 1794     | 1659     | 
| 22 | https://lichess.org/CzJQl3VR | 1225                      | 1        | 1794     | 1659     | 
| 23 | https://lichess.org/BRs696ni | 1225                      | 2        | 1126     | 1153     | 
| 24 | https://lichess.org/BxIKzgrU | 1225                      | 3        | 1613     | 1626     | 
| 25 | https://lichess.org/TQCElEqo | 1200                      | 3        | 1361     | 1426     | 


#### Bonus: pawn mates

| #  | url                          | materialFromMatedSideView | gameType | blackElo | whiteElo | 
|----|------------------------------|---------------------------|----------|----------|----------| 
| 1  | https://lichess.org/yWzjvFI2 | 4400                      | 1        | 1945     | 2068     | 
| 2  | https://lichess.org/8wZnYQHf | 4300                      | 2        | 949      | 950      | 
| 3  | https://lichess.org/ReyHEI41 | 4150                      | 2        | 2198     | 2041     | 
| 4  | https://lichess.org/0wUjU6ge | 3850                      | 2        | 1474     | 1472     | 
| 5  | https://lichess.org/jotskE9F | 3550                      | 2        | 1435     | 1457     | 
| 6  | https://lichess.org/TLsKKaq4 | 3525                      | 2        | 947      | 1221     | 
| 7  | https://lichess.org/itftPGGi | 3425                      | 3        | 1345     | 1143     | 
| 8  | https://lichess.org/3iYXHCPE | 3400                      | 2        | 1320     | 1307     | 
| 9  | https://lichess.org/UTckoUyx | 3325                      | 1        | 1802     | 1487     | 
| 10 | https://lichess.org/OFIRIjUt | 3125                      | 0        | 1500     | 1500     | 
| 11 | https://lichess.org/ub1cizbR | 3125                      | 1        | 1106     | 1526     | 
| 12 | https://lichess.org/fiqRd8HB | 3050                      | 2        | 1023     | 1015     | 
| 13 | https://lichess.org/SfRG38A2 | 3025                      | 1        | 1833     | 1480     | 
| 14 | https://lichess.org/wEH4km2O | 3025                      | 1        | 1382     | 1309     | 
| 15 | https://lichess.org/xN9jguFL | 2950                      | 1        | 1793     | 1465     | 
| 16 | https://lichess.org/2jIDLQKP | 2925                      | 2        | 1228     | 1296     | 
| 17 | https://lichess.org/DnMbKwiO | 2900                      | 3        | 1623     | 1632     | 
| 18 | https://lichess.org/IeBLTugK | 2850                      | 1        | 1527     | 1571     | 
| 19 | https://lichess.org/oqWdnpy5 | 2825                      | 2        | 1421     | 1390     | 
| 20 | https://lichess.org/FlvbgrJj | 2800                      | 2        | 1236     | 1261     | 
| 21 | https://lichess.org/J9o6RoeR | 2750                      | 1        | 1524     | 1502     | 
| 22 | https://lichess.org/pxq4KOvJ | 2725                      | 2        | 1052     | 1030     | 
| 23 | https://lichess.org/LZi0zuOS | 2700                      | 1        | 1660     | 1857     | 
| 24 | https://lichess.org/CbD2a5Ap | 2650                      | 2        | 976      | 941      | 
| 25 | https://lichess.org/gokql83F | 2650                      | 4        | 1038     | 1009     | 
