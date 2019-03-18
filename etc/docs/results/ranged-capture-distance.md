### Top games by ranged capture distance

```sql
select url,avgRangedCaptureDistance,rangedCaptureMoves,blackElo,whiteElo
from game_aggregates
WHERE avgRating > 1800 and rangedCaptureMoves > 20
order by avgRangedCaptureDistance desc
limit 25;
```

| #  | url                          | avgRangedCaptureDistance | rangedCaptureMoves | blackElo | whiteElo | 
|----|------------------------------|--------------------------|--------------------|----------|----------| 
| 1  | https://lichess.org/lpfY89ek | 4.2882773271029695       | 21                 | 2357     | 2197     | 
| 2  | https://lichess.org/055pJWUX | 4.255016145682256        | 21                 | 1808     | 1799     | 
| 3  | https://lichess.org/B5123bvi | 4.215567234980056        | 21                 | 1899     | 1793     | 
| 4  | https://lichess.org/D2HBA4pC | 4.164564005843606        | 21                 | 1996     | 1977     | 
| 5  | https://lichess.org/V2gSwkV9 | 4.153009687409353        | 21                 | 2145     | 1798     | 
| 6  | https://lichess.org/0hVGcMDX | 4.135267639693616        | 21                 | 1997     | 2005     | 
| 7  | https://lichess.org/yxaeuOUz | 4.131883458176214        | 21                 | 1881     | 1857     | 
| 8  | https://lichess.org/x58i60Yh | 4.1284992766588084       | 21                 | 2366     | 2406     | 
| 9  | https://lichess.org/k7We9Ajw | 4.120329139741961        | 21                 | 1824     | 1819     | 
| 10 | https://lichess.org/7IbxUZzz | 4.115543184342517        | 21                 | 1868     | 1772     | 
| 11 | https://lichess.org/2LXovSDq | 4.110757228943072        | 21                 | 1811     | 1814     | 
| 12 | https://lichess.org/GzSWuutO | 4.089050365956611        | 21                 | 2208     | 2318     | 
| 13 | https://lichess.org/DjCrVMRT | 4.072635443020303        | 22                 | 1880     | 2534     | 
| 14 | https://lichess.org/zaGV6CXx | 4.061606320878517        | 22                 | 2263     | 2315     | 
| 15 | https://lichess.org/cdMftdEG | 4.0577715921712585       | 21                 | 1912     | 1897     | 
| 16 | https://lichess.org/ytYO9Tzx | 4.054387410653855        | 21                 | 1974     | 1918     | 
| 17 | https://lichess.org/SYZdWriT | 4.052985636771814        | 21                 | 2261     | 2203     | 
| 18 | https://lichess.org/n68EuE1w | 4.052985636771814        | 21                 | 2120     | 2080     | 
| 19 | https://lichess.org/PO9HkjLK | 4.016920907587018        | 21                 | 1870     | 1945     | 
| 20 | https://lichess.org/1JJGo1rX | 4.013536726069614        | 21                 | 2329     | 2499     | 
| 21 | https://lichess.org/fS3pDmOU | 4.0087507706701695       | 21                 | 1783     | 1827     | 
| 22 | https://lichess.org/DRNHIlep | 4.007348996788129        | 21                 | 1859     | 1802     | 
| 23 | https://lichess.org/8QWhyH0C | 3.993812270718515        | 21                 | 1981     | 2055     | 
| 24 | https://lichess.org/vcoas7eq | 3.9924104968364738       | 21                 | 1791     | 1857     | 
| 25 | https://lichess.org/0i3wPC2R | 3.9808561784022225       | 21                 | 1801     | 1880     | 

