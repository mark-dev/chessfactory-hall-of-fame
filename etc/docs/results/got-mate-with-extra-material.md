### Got mate with piece advantage. With filter rating.
 
 Results sorted by lose side material advantage

```sql
select url || '#' || toString(plies) as mateUrl,materialFromMatedSideView,gameType,blackElo,whiteElo
from game_aggregates
where avgRating > 1800
order by materialFromMatedSideView desc
limit 25;
```

| #  | mateUrl                          | materialFromMatedSideView | gameType | blackElo | whiteElo | 
|----|----------------------------------|---------------------------|----------|----------|----------| 
| 1  | https://lichess.org/yWzjvFI2#107 | 4400                      | 1        | 1945     | 2068     | 
| 2  | https://lichess.org/ReyHEI41#46  | 4150                      | 2        | 2198     | 2041     | 
| 3  | https://lichess.org/0bamDQAZ#41  | 3225                      | 2        | 1816     | 1815     | 
| 4  | https://lichess.org/y7iXw85k#34  | 3050                      | 1        | 2213     | 1917     | 
| 5  | https://lichess.org/moCUump9#71  | 3025                      | 1        | 1756     | 1903     | 
| 6  | https://lichess.org/qHi8janX#37  | 2925                      | 2        | 2152     | 1824     | 
| 7  | https://lichess.org/yROSDQN8#50  | 2700                      | 2        | 1920     | 1896     | 
| 8  | https://lichess.org/blju5gVm#18  | 2700                      | 1        | 2067     | 2283     | 
| 9  | https://lichess.org/meZZEpPU#57  | 2650                      | 1        | 2022     | 1691     | 
| 10 | https://lichess.org/wI8HzLfI#66  | 2600                      | 1        | 1884     | 1862     | 
| 11 | https://lichess.org/XnjqsSIg#61  | 2600                      | 1        | 2382     | 2590     | 
| 12 | https://lichess.org/jjginMk7#96  | 2600                      | 2        | 1971     | 1828     | 
| 13 | https://lichess.org/CeScrZMH#72  | 2575                      | 2        | 1957     | 1946     | 
| 14 | https://lichess.org/NC5LAbaA#76  | 2575                      | 2        | 2156     | 2164     | 
| 15 | https://lichess.org/8ktW6g1h#35  | 2550                      | 3        | 1996     | 1793     | 
| 16 | https://lichess.org/uH112q1e#52  | 2525                      | 1        | 1778     | 1825     | 
| 17 | https://lichess.org/N9i902M8#14  | 2525                      | 3        | 1852     | 1935     | 
| 18 | https://lichess.org/GDub4tLe#54  | 2525                      | 1        | 1857     | 1883     | 
| 19 | https://lichess.org/58XqsRaR#40  | 2525                      | 1        | 1910     | 1891     | 
| 20 | https://lichess.org/eAsOdiAb#44  | 2525                      | 2        | 2064     | 2035     | 
| 21 | https://lichess.org/INEsmHiv#60  | 2525                      | 4        | 1926     | 1891     | 
| 22 | https://lichess.org/45n9imXM#115 | 2525                      | 3        | 2051     | 2039     | 
| 23 | https://lichess.org/15SzsZza#71  | 2500                      | 2        | 1854     | 1857     | 
| 24 | https://lichess.org/ftghVcJa#76  | 2500                      | 1        | 1920     | 1718     | 
| 25 | https://lichess.org/5H5bwDnl#66  | 2500                      | 1        | 1879     | 1849     | 


### Without rating filter

| #   | mateUrl                          | materialFromMatedSideView | gameType | blackElo | whiteElo | 
|-----|----------------------------------|---------------------------|----------|----------|----------| 
| 1   | https://lichess.org/Rf2MYCis#258 | 6625                      | 1        | 1609     | 1451     | 
| 2   | https://lichess.org/726i1yi9#117 | 5325                      | 3        | 1406     | 1180     | 
| 3   | https://lichess.org/yWzjvFI2#107 | 4400                      | 1        | 1945     | 2068     | 
| 4   | https://lichess.org/YQlPmSI6#41  | 4300                      | 2        | 1001     | 800      | 
| 5   | https://lichess.org/8wZnYQHf#87  | 4300                      | 2        | 949      | 950      | 
| 6   | https://lichess.org/ReyHEI41#46  | 4150                      | 2        | 2198     | 2041     | 
| 7   | https://lichess.org/0wUjU6ge#49  | 3850                      | 2        | 1474     | 1472     | 
| 8   | https://lichess.org/fDqCMKHu#98  | 3600                      | 2        | 1220     | 1193     | 
| 9   | https://lichess.org/jotskE9F#65  | 3550                      | 2        | 1435     | 1457     | 
| 10  | https://lichess.org/TLsKKaq4#75  | 3525                      | 2        | 947      | 1221     | 
| 11  | https://lichess.org/itftPGGi#118 | 3425                      | 3        | 1345     | 1143     | 
| 12  | https://lichess.org/3iYXHCPE#60  | 3400                      | 2        | 1320     | 1307     | 
| 13  | https://lichess.org/gkaRCk5g#39  | 3350                      | 1        | 1842     | 1751     | 
| 14  | https://lichess.org/GUMvK9W1#34  | 3350                      | 1        | 1865     | 1428     | 
| 15  | https://lichess.org/MMKa9TjU#32  | 3325                      | 2        | 1617     | 1536     | 
| 16  | https://lichess.org/hC5ovAPI#20  | 3325                      | 3        | 1163     | 1186     | 
| 17  | https://lichess.org/AOKH0irE#30  | 3325                      | 2        | 1103     | 1029     | 
| 18  | https://lichess.org/UTckoUyx#26  | 3325                      | 1        | 1802     | 1487     | 
| 19  | https://lichess.org/vYCCWolR#60  | 3275                      | 3        | 990      | 1108     | 
| 20  | https://lichess.org/0bamDQAZ#41  | 3225                      | 2        | 1816     | 1815     | 
| 21  | https://lichess.org/n6PZzGg9#63  | 3225                      | 1        | 1267     | 1232     | 
| 22  | https://lichess.org/n8pVw2OD#78  | 3225                      | 3        | 1191     | 1190     | 
| 23  | https://lichess.org/CQfG8gS7#93  | 3225                      | 2        | 1271     | 1266     | 
| 24  | https://lichess.org/ESI82xKl#63  | 3225                      | 2        | 1056     | 986      | 
| 25  | https://lichess.org/WVHfRLMJ#50  | 3200                      | 3        | 1617     | 1289     | 
| 26  | https://lichess.org/VFxvM6Mz#56  | 3200                      | 3        | 1192     | 1283     | 
| 27  | https://lichess.org/suf8pc2z#62  | 3125                      | 1        | 1228     | 1097     | 
| 28  | https://lichess.org/ub1cizbR#73  | 3125                      | 1        | 1106     | 1526     | 
| 29  | https://lichess.org/OFIRIjUt#53  | 3125                      | 0        | 1500     | 1500     | 
| 30  | https://lichess.org/gyAYLHxV#31  | 3125                      | 3        | 1296     | 1246     | 
| 31  | https://lichess.org/mYyyIOSA#31  | 3100                      | 1        | 863      | 936      | 
| 32  | https://lichess.org/fiqRd8HB#39  | 3050                      | 2        | 1023     | 1015     | 
| 33  | https://lichess.org/7R2HEuyC#34  | 3050                      | 1        | 1504     | 1779     | 
| 34  | https://lichess.org/y7iXw85k#34  | 3050                      | 1        | 2213     | 1917     | 
| 35  | https://lichess.org/eWHPN0jO#27  | 3025                      | 2        | 795      | 921      | 
| 36  | https://lichess.org/moCUump9#71  | 3025                      | 1        | 1756     | 1903     | 
| 37  | https://lichess.org/InFDpKsL#72  | 3025                      | 2        | 1282     | 1588     | 
| 38  | https://lichess.org/SfRG38A2#67  | 3025                      | 1        | 1833     | 1480     | 
| 39  | https://lichess.org/9FSq2YVO#30  | 3025                      | 2        | 1150     | 1245     | 
| 40  | https://lichess.org/wEH4km2O#91  | 3025                      | 1        | 1382     | 1309     | 
| 41  | https://lichess.org/mn4UD9ex#44  | 2950                      | 0        | 1652     | 1347     | 
| 42  | https://lichess.org/7eikzamt#33  | 2950                      | 4        | 1447     | 1072     | 
| 43  | https://lichess.org/xN9jguFL#50  | 2950                      | 1        | 1793     | 1465     | 
| 44  | https://lichess.org/r35mFvdk#49  | 2950                      | 2        | 927      | 1104     | 
| 45  | https://lichess.org/rUKcJQc0#78  | 2950                      | 2        | 1139     | 1128     | 
| 46  | https://lichess.org/tIliFn7m#66  | 2950                      | 3        | 1076     | 1049     | 
| 47  | https://lichess.org/loxkXqIa#27  | 2950                      | 2        | 1010     | 1245     | 
| 48  | https://lichess.org/x8VDa7YB#42  | 2950                      | 2        | 978      | 864      | 
| 49  | https://lichess.org/gbcA6Rab#38  | 2925                      | 3        | 1234     | 1319     | 
| 50  | https://lichess.org/mZbeGH67#31  | 2925                      | 3        | 1224     | 1123     | 
| 51  | https://lichess.org/N1yC2Wke#71  | 2925                      | 2        | 1267     | 1291     | 
| 52  | https://lichess.org/qHi8janX#37  | 2925                      | 2        | 2152     | 1824     | 
| 53  | https://lichess.org/2jIDLQKP#74  | 2925                      | 2        | 1228     | 1296     | 
| 54  | https://lichess.org/RLN024HA#49  | 2925                      | 1        | 1581     | 1635     | 
| 55  | https://lichess.org/mvyudWHE#34  | 2925                      | 3        | 1353     | 836      | 
| 56  | https://lichess.org/TyTdA4VX#56  | 2925                      | 3        | 1319     | 1080     | 
| 57  | https://lichess.org/50Gx4Hvi#72  | 2900                      | 1        | 1647     | 1624     | 
| 58  | https://lichess.org/DnMbKwiO#105 | 2900                      | 3        | 1623     | 1632     | 
| 59  | https://lichess.org/ZA91cKqp#65  | 2900                      | 3        | 1454     | 1453     | 
| 60  | https://lichess.org/mCp94Se9#108 | 2900                      | 2        | 1571     | 1587     | 
| 61  | https://lichess.org/r9RUyh0e#29  | 2900                      | 1        | 1737     | 1429     | 
| 62  | https://lichess.org/dcVWbn8B#72  | 2850                      | 4        | 1137     | 1500     | 
| 63  | https://lichess.org/tRKzcvKL#64  | 2850                      | 4        | 1316     | 1268     | 
| 64  | https://lichess.org/IeBLTugK#81  | 2850                      | 1        | 1527     | 1571     | 
| 65  | https://lichess.org/u42PEdVk#47  | 2850                      | 1        | 1617     | 1796     | 
| 66  | https://lichess.org/Vt6xCYRD#29  | 2850                      | 1        | 1364     | 1070     | 
| 67  | https://lichess.org/jGzzJp6l#42  | 2850                      | 2        | 954      | 1105     | 
| 68  | https://lichess.org/W7F4Izpd#31  | 2825                      | 2        | 952      | 1000     | 
| 69  | https://lichess.org/87iIiic6#34  | 2825                      | 2        | 982      | 829      | 
| 70  | https://lichess.org/Ib0y7LWc#24  | 2825                      | 2        | 1544     | 1612     | 
| 71  | https://lichess.org/oqWdnpy5#31  | 2825                      | 2        | 1421     | 1390     | 
| 72  | https://lichess.org/xzR2C9dH#36  | 2825                      | 1        | 931      | 1018     | 
| 73  | https://lichess.org/PPmt1em4#35  | 2825                      | 1        | 1398     | 1272     | 
| 74  | https://lichess.org/sRlDp1Oh#73  | 2825                      | 3        | 1656     | 1679     | 
| 75  | https://lichess.org/lTeyGwST#31  | 2825                      | 1        | 1713     | 1644     | 
| 76  | https://lichess.org/HOyTKQKj#47  | 2825                      | 2        | 1035     | 1204     | 
| 77  | https://lichess.org/bnI470ET#23  | 2825                      | 4        | 1654     | 1321     | 
| 78  | https://lichess.org/OFOCKqY3#59  | 2825                      | 1        | 1066     | 1048     | 
| 79  | https://lichess.org/exvO6zGt#99  | 2825                      | 3        | 1349     | 1177     | 
| 80  | https://lichess.org/CB03btmH#40  | 2800                      | 3        | 1403     | 1376     | 
| 81  | https://lichess.org/zwFR4UlR#47  | 2800                      | 4        | 1015     | 1128     | 
| 82  | https://lichess.org/y7MY9wfY#76  | 2800                      | 1        | 1474     | 1465     | 
| 83  | https://lichess.org/FlvbgrJj#107 | 2800                      | 2        | 1236     | 1261     | 
| 84  | https://lichess.org/Kbt1fCOA#73  | 2800                      | 3        | 961      | 1087     | 
| 85  | https://lichess.org/8JY0D8k7#131 | 2800                      | 2        | 1160     | 1240     | 
| 86  | https://lichess.org/BhcxaAFK#81  | 2775                      | 2        | 1525     | 1568     | 
| 87  | https://lichess.org/CFOe5bbo#48  | 2750                      | 3        | 817      | 1071     | 
| 88  | https://lichess.org/AQ7PasK9#60  | 2750                      | 2        | 1014     | 1015     | 
| 89  | https://lichess.org/HhYNPxxk#66  | 2750                      | 4        | 1919     | 1324     | 
| 90  | https://lichess.org/nOq3b2e5#61  | 2750                      | 1        | 1385     | 1190     | 
| 91  | https://lichess.org/hqLI3Miu#44  | 2750                      | 3        | 987      | 1008     | 
| 92  | https://lichess.org/bfyg9fJr#68  | 2750                      | 3        | 1261     | 1335     | 
| 93  | https://lichess.org/J9o6RoeR#44  | 2750                      | 1        | 1524     | 1502     | 
| 94  | https://lichess.org/ALl5500B#18  | 2725                      | 2        | 1047     | 1364     | 
| 95  | https://lichess.org/EcM0MtGc#24  | 2725                      | 2        | 800      | 800      | 
| 96  | https://lichess.org/q7aNgJE2#14  | 2725                      | 2        | 1520     | 1402     | 
| 97  | https://lichess.org/Bwk9ppv2#75  | 2725                      | 2        | 1343     | 1274     | 
| 98  | https://lichess.org/mVRHdYOu#83  | 2725                      | 1        | 1465     | 1314     | 
| 99  | https://lichess.org/zVp1W8vX#40  | 2725                      | 1        | 1575     | 1553     | 
| 100 | https://lichess.org/uNeA96kY#38  | 2725                      | 1        | 1534     | 1538     | 


