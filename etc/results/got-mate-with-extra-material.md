### Got mate with piece advantage. With filter rating.
 
 Results sorted by lose side material advantage

```sql
select url,materialFromMatedSideView,gameType, blackElo,whiteElo
from game_aggregates
where avgRating > 1800
order by materialFromMatedSideView desc
LIMIT 25;
```

| #  | url                          | materialFromMatedSideView | gameType | blackElo | whiteElo | 
|----|------------------------------|---------------------------|----------|----------|----------| 
| 1  | https://lichess.org/yWzjvFI2 | 4400                      | 1        | 1945     | 2068     | 
| 2  | https://lichess.org/ReyHEI41 | 4150                      | 2        | 2198     | 2041     | 
| 3  | https://lichess.org/0bamDQAZ | 3225                      | 2        | 1816     | 1815     | 
| 4  | https://lichess.org/y7iXw85k | 3050                      | 1        | 2213     | 1917     | 
| 5  | https://lichess.org/moCUump9 | 3025                      | 1        | 1756     | 1903     | 
| 6  | https://lichess.org/qHi8janX | 2925                      | 2        | 2152     | 1824     | 
| 7  | https://lichess.org/yROSDQN8 | 2700                      | 2        | 1920     | 1896     | 
| 8  | https://lichess.org/blju5gVm | 2700                      | 1        | 2067     | 2283     | 
| 9  | https://lichess.org/meZZEpPU | 2650                      | 1        | 2022     | 1691     | 
| 10 | https://lichess.org/jjginMk7 | 2600                      | 2        | 1971     | 1828     | 
| 11 | https://lichess.org/wI8HzLfI | 2600                      | 1        | 1884     | 1862     | 
| 12 | https://lichess.org/XnjqsSIg | 2600                      | 1        | 2382     | 2590     | 
| 13 | https://lichess.org/CeScrZMH | 2575                      | 2        | 1957     | 1946     | 
| 14 | https://lichess.org/NC5LAbaA | 2575                      | 2        | 2156     | 2164     | 
| 15 | https://lichess.org/8ktW6g1h | 2550                      | 3        | 1996     | 1793     | 
| 16 | https://lichess.org/eAsOdiAb | 2525                      | 2        | 2064     | 2035     | 
| 17 | https://lichess.org/uH112q1e | 2525                      | 1        | 1778     | 1825     | 
| 18 | https://lichess.org/58XqsRaR | 2525                      | 1        | 1910     | 1891     | 
| 19 | https://lichess.org/N9i902M8 | 2525                      | 3        | 1852     | 1935     | 
| 20 | https://lichess.org/GDub4tLe | 2525                      | 1        | 1857     | 1883     | 
| 21 | https://lichess.org/45n9imXM | 2525                      | 3        | 2051     | 2039     | 
| 22 | https://lichess.org/INEsmHiv | 2525                      | 4        | 1926     | 1891     | 
| 23 | https://lichess.org/cSyDspF4 | 2500                      | 1        | 1858     | 1796     | 
| 24 | https://lichess.org/ftghVcJa | 2500                      | 1        | 1920     | 1718     | 
| 25 | https://lichess.org/5H5bwDnl | 2500                      | 1        | 1879     | 1849     | 

### Without rating filter

|     |                              |                           |          |          |          | 
|-----|------------------------------|---------------------------|----------|----------|----------| 
| #   | url                          | materialFromMatedSideView | gameType | blackElo | whiteElo | 
| 1   | https://lichess.org/Rf2MYCis | 6625                      | 1        | 1609     | 1451     | 
| 2   | https://lichess.org/726i1yi9 | 5325                      | 3        | 1406     | 1180     | 
| 3   | https://lichess.org/yWzjvFI2 | 4400                      | 1        | 1945     | 2068     | 
| 4   | https://lichess.org/YQlPmSI6 | 4300                      | 2        | 1001     | 800      | 
| 5   | https://lichess.org/8wZnYQHf | 4300                      | 2        | 949      | 950      | 
| 6   | https://lichess.org/ReyHEI41 | 4150                      | 2        | 2198     | 2041     | 
| 7   | https://lichess.org/0wUjU6ge | 3850                      | 2        | 1474     | 1472     | 
| 8   | https://lichess.org/fDqCMKHu | 3600                      | 2        | 1220     | 1193     | 
| 9   | https://lichess.org/jotskE9F | 3550                      | 2        | 1435     | 1457     | 
| 10  | https://lichess.org/TLsKKaq4 | 3525                      | 2        | 947      | 1221     | 
| 11  | https://lichess.org/itftPGGi | 3425                      | 3        | 1345     | 1143     | 
| 12  | https://lichess.org/3iYXHCPE | 3400                      | 2        | 1320     | 1307     | 
| 13  | https://lichess.org/GUMvK9W1 | 3350                      | 1        | 1865     | 1428     | 
| 14  | https://lichess.org/gkaRCk5g | 3350                      | 1        | 1842     | 1751     | 
| 15  | https://lichess.org/MMKa9TjU | 3325                      | 2        | 1617     | 1536     | 
| 16  | https://lichess.org/hC5ovAPI | 3325                      | 3        | 1163     | 1186     | 
| 17  | https://lichess.org/AOKH0irE | 3325                      | 2        | 1103     | 1029     | 
| 18  | https://lichess.org/UTckoUyx | 3325                      | 1        | 1802     | 1487     | 
| 19  | https://lichess.org/vYCCWolR | 3275                      | 3        | 990      | 1108     | 
| 20  | https://lichess.org/CQfG8gS7 | 3225                      | 2        | 1271     | 1266     | 
| 21  | https://lichess.org/ESI82xKl | 3225                      | 2        | 1056     | 986      | 
| 22  | https://lichess.org/n8pVw2OD | 3225                      | 3        | 1191     | 1190     | 
| 23  | https://lichess.org/n6PZzGg9 | 3225                      | 1        | 1267     | 1232     | 
| 24  | https://lichess.org/0bamDQAZ | 3225                      | 2        | 1816     | 1815     | 
| 25  | https://lichess.org/WVHfRLMJ | 3200                      | 3        | 1617     | 1289     | 
| 26  | https://lichess.org/VFxvM6Mz | 3200                      | 3        | 1192     | 1283     | 
| 27  | https://lichess.org/OFIRIjUt | 3125                      | 0        | 1500     | 1500     | 
| 28  | https://lichess.org/gyAYLHxV | 3125                      | 3        | 1296     | 1246     | 
| 29  | https://lichess.org/suf8pc2z | 3125                      | 1        | 1228     | 1097     | 
| 30  | https://lichess.org/ub1cizbR | 3125                      | 1        | 1106     | 1526     | 
| 31  | https://lichess.org/mYyyIOSA | 3100                      | 1        | 863      | 936      | 
| 32  | https://lichess.org/fiqRd8HB | 3050                      | 2        | 1023     | 1015     | 
| 33  | https://lichess.org/7R2HEuyC | 3050                      | 1        | 1504     | 1779     | 
| 34  | https://lichess.org/y7iXw85k | 3050                      | 1        | 2213     | 1917     | 
| 35  | https://lichess.org/SfRG38A2 | 3025                      | 1        | 1833     | 1480     | 
| 36  | https://lichess.org/eWHPN0jO | 3025                      | 2        | 795      | 921      | 
| 37  | https://lichess.org/9FSq2YVO | 3025                      | 2        | 1150     | 1245     | 
| 38  | https://lichess.org/moCUump9 | 3025                      | 1        | 1756     | 1903     | 
| 39  | https://lichess.org/InFDpKsL | 3025                      | 2        | 1282     | 1588     | 
| 40  | https://lichess.org/wEH4km2O | 3025                      | 1        | 1382     | 1309     | 
| 41  | https://lichess.org/tIliFn7m | 2950                      | 3        | 1076     | 1049     | 
| 42  | https://lichess.org/7eikzamt | 2950                      | 4        | 1447     | 1072     | 
| 43  | https://lichess.org/xN9jguFL | 2950                      | 1        | 1793     | 1465     | 
| 44  | https://lichess.org/r35mFvdk | 2950                      | 2        | 927      | 1104     | 
| 45  | https://lichess.org/rUKcJQc0 | 2950                      | 2        | 1139     | 1128     | 
| 46  | https://lichess.org/x8VDa7YB | 2950                      | 2        | 978      | 864      | 
| 47  | https://lichess.org/mn4UD9ex | 2950                      | 0        | 1652     | 1347     | 
| 48  | https://lichess.org/loxkXqIa | 2950                      | 2        | 1010     | 1245     | 
| 49  | https://lichess.org/qHi8janX | 2925                      | 2        | 2152     | 1824     | 
| 50  | https://lichess.org/N1yC2Wke | 2925                      | 2        | 1267     | 1291     | 
| 51  | https://lichess.org/gbcA6Rab | 2925                      | 3        | 1234     | 1319     | 
| 52  | https://lichess.org/TyTdA4VX | 2925                      | 3        | 1319     | 1080     | 
| 53  | https://lichess.org/mZbeGH67 | 2925                      | 3        | 1224     | 1123     | 
| 54  | https://lichess.org/RLN024HA | 2925                      | 1        | 1581     | 1635     | 
| 55  | https://lichess.org/mvyudWHE | 2925                      | 3        | 1353     | 836      | 
| 56  | https://lichess.org/2jIDLQKP | 2925                      | 2        | 1228     | 1296     | 
| 57  | https://lichess.org/r9RUyh0e | 2900                      | 1        | 1737     | 1429     | 
| 58  | https://lichess.org/50Gx4Hvi | 2900                      | 1        | 1647     | 1624     | 
| 59  | https://lichess.org/mCp94Se9 | 2900                      | 2        | 1571     | 1587     | 
| 60  | https://lichess.org/DnMbKwiO | 2900                      | 3        | 1623     | 1632     | 
| 61  | https://lichess.org/ZA91cKqp | 2900                      | 3        | 1454     | 1453     | 
| 62  | https://lichess.org/tRKzcvKL | 2850                      | 4        | 1316     | 1268     | 
| 63  | https://lichess.org/u42PEdVk | 2850                      | 1        | 1617     | 1796     | 
| 64  | https://lichess.org/jGzzJp6l | 2850                      | 2        | 954      | 1105     | 
| 65  | https://lichess.org/Vt6xCYRD | 2850                      | 1        | 1364     | 1070     | 
| 66  | https://lichess.org/IeBLTugK | 2850                      | 1        | 1527     | 1571     | 
| 67  | https://lichess.org/dcVWbn8B | 2850                      | 4        | 1137     | 1500     | 
| 68  | https://lichess.org/PPmt1em4 | 2825                      | 1        | 1398     | 1272     | 
| 69  | https://lichess.org/HOyTKQKj | 2825                      | 2        | 1035     | 1204     | 
| 70  | https://lichess.org/OFOCKqY3 | 2825                      | 1        | 1066     | 1048     | 
| 71  | https://lichess.org/bnI470ET | 2825                      | 4        | 1654     | 1321     | 
| 72  | https://lichess.org/W7F4Izpd | 2825                      | 2        | 952      | 1000     | 
| 73  | https://lichess.org/Ib0y7LWc | 2825                      | 2        | 1544     | 1612     | 
| 74  | https://lichess.org/sRlDp1Oh | 2825                      | 3        | 1656     | 1679     | 
| 75  | https://lichess.org/xzR2C9dH | 2825                      | 1        | 931      | 1018     | 
| 76  | https://lichess.org/lTeyGwST | 2825                      | 1        | 1713     | 1644     | 
| 77  | https://lichess.org/exvO6zGt | 2825                      | 3        | 1349     | 1177     | 
| 78  | https://lichess.org/oqWdnpy5 | 2825                      | 2        | 1421     | 1390     | 
| 79  | https://lichess.org/87iIiic6 | 2825                      | 2        | 982      | 829      | 
| 80  | https://lichess.org/Kbt1fCOA | 2800                      | 3        | 961      | 1087     | 
| 81  | https://lichess.org/FlvbgrJj | 2800                      | 2        | 1236     | 1261     | 
| 82  | https://lichess.org/8JY0D8k7 | 2800                      | 2        | 1160     | 1240     | 
| 83  | https://lichess.org/y7MY9wfY | 2800                      | 1        | 1474     | 1465     | 
| 84  | https://lichess.org/CB03btmH | 2800                      | 3        | 1403     | 1376     | 
| 85  | https://lichess.org/zwFR4UlR | 2800                      | 4        | 1015     | 1128     | 
| 86  | https://lichess.org/BhcxaAFK | 2775                      | 2        | 1525     | 1568     | 
| 87  | https://lichess.org/hqLI3Miu | 2750                      | 3        | 987      | 1008     | 
| 88  | https://lichess.org/bfyg9fJr | 2750                      | 3        | 1261     | 1335     | 
| 89  | https://lichess.org/CFOe5bbo | 2750                      | 3        | 817      | 1071     | 
| 90  | https://lichess.org/J9o6RoeR | 2750                      | 1        | 1524     | 1502     | 
| 91  | https://lichess.org/nOq3b2e5 | 2750                      | 1        | 1385     | 1190     | 
| 92  | https://lichess.org/AQ7PasK9 | 2750                      | 2        | 1014     | 1015     | 
| 93  | https://lichess.org/HhYNPxxk | 2750                      | 4        | 1919     | 1324     | 
| 94  | https://lichess.org/WbxGQ360 | 2725                      | 1        | 1505     | 1498     | 
| 95  | https://lichess.org/nNxXmGGe | 2725                      | 3        | 1169     | 1523     | 
| 96  | https://lichess.org/LVEbRPEn | 2725                      | 2        | 1657     | 1501     | 
| 97  | https://lichess.org/w9qJAUIE | 2725                      | 1        | 1536     | 1536     | 
| 98  | https://lichess.org/149RoiDV | 2725                      | 2        | 1060     | 851      | 
| 99  | https://lichess.org/JUSF61Is | 2725                      | 1        | 1289     | 1502     | 
| 100 | https://lichess.org/aurfTINR | 2725                      | 2        | 1088     | 1142     | 
