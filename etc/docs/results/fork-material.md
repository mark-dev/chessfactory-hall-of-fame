### Strongest knight forks

```sql
select (url || '#' || toString(maxForkMaterialAmountPly)) as directLinkMove,
       maxForkMaterialAmount,
       blackElo,
       whiteElo,
       maxForkMaterialAmountPieces
from game_aggregates
WHERE not has(maxForkMaterialAmountPieces,cast('N' as FixedString(1)))
order by maxForkMaterialAmount DESC
limit 100;
```

| #   | directLinkMove                  | maxForkMaterialAmount | blackElo | whiteElo | maxForkMaterialAmountPieces | 
|-----|---------------------------------|-----------------------|----------|----------|-----------------------------| 
| 1   | https://lichess.org/1XSp4Qkp#48 | 3400                  | 1646     | 1634     | {R,K,Q,P,P,Q}               | 
| 2   | https://lichess.org/p6d0q5ab#52 | 3325                  | 1443     | 1640     | {R,K,Q,R,P,P,B}             | 
| 3   | https://lichess.org/N3lH6V1X#56 | 3325                  | 1831     | 1861     | {R,R,Q,K,P,B,P}             | 
| 4   | https://lichess.org/AMr0pk2p#59 | 3325                  | 1789     | 1794     | {P,B,P,Q,K,R,R}             | 
| 5   | https://lichess.org/OC8S90fk#59 | 3325                  | 2050     | 1885     | {P,P,B,R,K,Q,R}             | 
| 6   | https://lichess.org/Ar4obkyk#48 | 3325                  | 1840     | 1864     | {R,R,Q,K,P,P,B}             | 
| 7   | https://lichess.org/LnCno4t7#56 | 3325                  | 1998     | 2034     | {R,R,Q,K,P,P,B}             | 
| 8   | https://lichess.org/bbl2Ba7H#56 | 3325                  | 1627     | 1519     | {Q,R,B,K,P,R,P}             | 
| 9   | https://lichess.org/Dr1RKHRM#35 | 3325                  | 1656     | 869      | {P,Q,B,P,K,R,R}             | 
| 10  | https://lichess.org/LaBqTLoO#61 | 3325                  | 1659     | 1927     | {R,P,P,Q,B,R,K}             | 
| 11  | https://lichess.org/MlyLyK0Y#79 | 3325                  | 1919     | 1866     | {P,R,P,B,K,Q,R}             | 
| 12  | https://lichess.org/52T14igj#51 | 3325                  | 1430     | 1427     | {P,P,B,R,K,Q,R}             | 
| 13  | https://lichess.org/AqqvcX2g#54 | 3325                  | 2415     | 2292     | {K,R,B,P,P,Q,R}             | 
| 14  | https://lichess.org/2bcu40hv#44 | 3325                  | 1481     | 1408     | {R,R,B,K,P,Q,P}             | 
| 15  | https://lichess.org/iH77GDpA#61 | 3325                  | 966      | 996      | {P,R,P,B,K,R,Q}             | 
| 16  | https://lichess.org/VfDdWAYH#44 | 3325                  | 2235     | 2241     | {K,R,Q,P,R,B,P}             | 
| 17  | https://lichess.org/siqh4ACk#61 | 3325                  | 1511     | 1645     | {P,P,Q,K,B,R,R}             | 
| 18  | https://lichess.org/XKj4TbRi#54 | 3325                  | 2040     | 1765     | {Q,R,B,K,P,R,P}             | 
| 19  | https://lichess.org/7wAqzlpa#45 | 3325                  | 1837     | 1826     | {R,B,P,Q,P,R,K}             | 
| 20  | https://lichess.org/fl6iMokz#58 | 3325                  | 1800     | 1747     | {R,R,Q,K,P,P,B}             | 
| 21  | https://lichess.org/pcrYxK0C#69 | 3325                  | 1831     | 1827     | {P,B,Q,P,R,K,R}             | 
| 22  | https://lichess.org/v0uDFQWc#47 | 3325                  | 1362     | 1405     | {P,Q,B,P,K,R,R}             | 
| 23  | https://lichess.org/B7nLiECl#60 | 3325                  | 1563     | 1412     | {B,R,Q,K,P,P,R}             | 
| 24  | https://lichess.org/61f2q19r#57 | 3325                  | 1639     | 1852     | {B,P,Q,P,K,R,R}             | 
| 25  | https://lichess.org/91B0RMuF#45 | 3325                  | 1821     | 1908     | {B,P,P,R,K,Q,R}             | 
| 26  | https://lichess.org/H90epx5l#56 | 3325                  | 2112     | 1971     | {R,K,Q,B,P,R,P}             | 
| 27  | https://lichess.org/5Shir2ns#40 | 3325                  | 2165     | 2090     | {R,R,Q,K,P,P,B}             | 
| 28  | https://lichess.org/srGP705f#60 | 3325                  | 1889     | 1888     | {R,R,Q,K,B,P,P}             | 
| 29  | https://lichess.org/avLBwdV1#44 | 3325                  | 2066     | 2014     | {R,K,Q,B,P,P,R}             | 
| 30  | https://lichess.org/y6AGU2TW#75 | 3300                  | 2094     | 2009     | {Q,Q,P,K,R}                 | 
| 31  | https://lichess.org/jlZwUudQ#86 | 3300                  | 1333     | 1387     | {R,K,P,Q,Q}                 | 
| 32  | https://lichess.org/XUNOtNoA#44 | 3225                  | 1123     | 1160     | {R,R,P,K,B,Q}               | 
| 33  | https://lichess.org/VRpcpPqx#57 | 3225                  | 1695     | 1524     | {Q,P,K,B,R,R}               | 
| 34  | https://lichess.org/RFVUikFe#62 | 3225                  | 1737     | 1717     | {K,R,Q,P,B,R}               | 
| 35  | https://lichess.org/fWjlavxT#37 | 3225                  | 1286     | 1640     | {Q,B,R,P,R,K}               | 
| 36  | https://lichess.org/zEWuD2Tb#46 | 3225                  | 1453     | 1440     | {R,K,Q,P,R,B}               | 
| 37  | https://lichess.org/BKLyOaQH#47 | 3225                  | 1819     | 1761     | {R,P,Q,B,R,K}               | 
| 38  | https://lichess.org/ejtK3i8r#43 | 3225                  | 1306     | 1209     | {B,P,Q,K,R,R}               | 
| 39  | https://lichess.org/QDkcG1PP#47 | 3225                  | 1707     | 1693     | {R,B,P,Q,R,K}               | 
| 40  | https://lichess.org/sI3uC3Us#67 | 3225                  | 1922     | 1735     | {R,Q,P,B,K,R}               | 
| 41  | https://lichess.org/DioOlCv3#40 | 3225                  | 2093     | 2024     | {R,R,Q,K,P,B}               | 
| 42  | https://lichess.org/IOlgeGaQ#50 | 3225                  | 2244     | 2224     | {R,K,Q,P,R,B}               | 
| 43  | https://lichess.org/JDc3wf5b#34 | 3225                  | 1053     | 1110     | {R,R,K,B,Q,P}               | 
| 44  | https://lichess.org/m8fWJ6Us#35 | 3225                  | 1460     | 1348     | {B,Q,R,P,R,K}               | 
| 45  | https://lichess.org/XZHfwDCj#53 | 3225                  | 1716     | 1812     | {P,Q,B,K,R,R}               | 
| 46  | https://lichess.org/PNHeG5Tx#68 | 3225                  | 1824     | 1754     | {R,K,Q,R,P,B}               | 
| 47  | https://lichess.org/07tcKBRC#72 | 3225                  | 1392     | 1364     | {K,R,R,Q,B,P}               | 
| 48  | https://lichess.org/XVNtGwxQ#64 | 3225                  | 2128     | 2126     | {R,K,Q,P,B,R}               | 
| 49  | https://lichess.org/XAuEkwHQ#40 | 3225                  | 1419     | 1456     | {R,K,Q,P,R,B}               | 
| 50  | https://lichess.org/cMd8tVFZ#44 | 3225                  | 1557     | 1555     | {R,Q,K,R,P,B}               | 
| 51  | https://lichess.org/1GlJBhlw#56 | 3225                  | 1500     | 1555     | {R,R,Q,K,B,P}               | 
| 52  | https://lichess.org/4uMnAyDq#54 | 3225                  | 1562     | 1576     | {R,R,B,K,P,Q}               | 
| 53  | https://lichess.org/8ufl4Sok#42 | 3225                  | 1629     | 1683     | {R,R,Q,K,B,P}               | 
| 54  | https://lichess.org/cEx4i3wQ#44 | 3225                  | 1687     | 1664     | {R,K,Q,B,P,R}               | 
| 55  | https://lichess.org/sEOr6GHh#35 | 3225                  | 1952     | 1864     | {R,B,Q,P,R,K}               | 
| 56  | https://lichess.org/kJYxbt4r#51 | 3225                  | 1751     | 1782     | {B,R,Q,P,R,K}               | 
| 57  | https://lichess.org/vIJWJDxr#58 | 3225                  | 1760     | 1775     | {R,R,B,K,P,Q}               | 
| 58  | https://lichess.org/TUOk29Qe#59 | 3225                  | 1780     | 1733     | {Q,R,P,B,K,R}               | 
| 59  | https://lichess.org/ixvs8UYh#44 | 3225                  | 1771     | 1686     | {Q,R,R,K,P,B}               | 
| 60  | https://lichess.org/5Yao6q9U#51 | 3225                  | 1521     | 1539     | {B,P,Q,K,R,R}               | 
| 61  | https://lichess.org/WD8NvQf6#54 | 3225                  | 1648     | 1937     | {R,K,P,B,Q,R}               | 
| 62  | https://lichess.org/Nlv3FbFF#41 | 3225                  | 1735     | 1769     | {B,R,Q,P,R,K}               | 
| 63  | https://lichess.org/pUeTiuGW#56 | 3225                  | 1136     | 1104     | {R,K,P,Q,R,B}               | 
| 64  | https://lichess.org/DEtbzIWG#42 | 3225                  | 1507     | 1112     | {R,R,Q,K,P,B}               | 
| 65  | https://lichess.org/riLUhvef#49 | 3225                  | 1663     | 1363     | {R,P,Q,B,R,K}               | 
| 66  | https://lichess.org/7mzltxP5#44 | 3225                  | 1736     | 1729     | {K,R,B,P,Q,R}               | 
| 67  | https://lichess.org/Dn9w84SU#66 | 3225                  | 1468     | 1624     | {R,R,K,B,P,Q}               | 
| 68  | https://lichess.org/eoXwMV7C#49 | 3225                  | 1820     | 1523     | {P,R,B,Q,K,R}               | 
| 69  | https://lichess.org/V523rkih#57 | 3225                  | 1291     | 1097     | {R,P,K,Q,R,B}               | 
| 70  | https://lichess.org/zggerXiw#45 | 3225                  | 1876     | 1884     | {R,B,Q,P,R,K}               | 
| 71  | https://lichess.org/t8XMFtdl#37 | 3225                  | 1239     | 1240     | {Q,P,K,B,R,R}               | 
| 72  | https://lichess.org/ennSTC1H#45 | 3225                  | 1236     | 1273     | {R,P,B,Q,K,R}               | 
| 73  | https://lichess.org/UuUwrIg6#68 | 3225                  | 1088     | 1119     | {R,R,K,P,Q,B}               | 
| 74  | https://lichess.org/8rAr9TM6#66 | 3225                  | 1975     | 2166     | {Q,K,R,B,R,P}               | 
| 75  | https://lichess.org/4lsHycsA#48 | 3225                  | 1272     | 1229     | {R,K,R,P,Q,B}               | 
| 76  | https://lichess.org/lzIi3wsp#45 | 3225                  | 1782     | 1944     | {B,R,Q,P,R,K}               | 
| 77  | https://lichess.org/4C70hVKu#40 | 3225                  | 1615     | 1678     | {R,K,P,Q,R,B}               | 
| 78  | https://lichess.org/jxSNzibj#61 | 3225                  | 1923     | 1693     | {Q,P,B,R,R,K}               | 
| 79  | https://lichess.org/K5XHTuoO#43 | 3225                  | 1528     | 1672     | {R,B,P,Q,R,K}               | 
| 80  | https://lichess.org/pHYwFIWi#48 | 3225                  | 1954     | 1932     | {R,K,Q,P,R,B}               | 
| 81  | https://lichess.org/uxuL2gTm#48 | 3225                  | 2220     | 2236     | {R,K,P,R,Q,B}               | 
| 82  | https://lichess.org/qKYKd4ZK#54 | 3225                  | 1995     | 2013     | {R,R,K,B,P,Q}               | 
| 83  | https://lichess.org/FGMeuJne#39 | 3225                  | 1584     | 1584     | {R,B,Q,P,R,K}               | 
| 84  | https://lichess.org/rpXAcISX#45 | 3225                  | 1664     | 1676     | {P,B,Q,K,R,R}               | 
| 85  | https://lichess.org/NQGDWiQt#46 | 3225                  | 1693     | 1662     | {R,K,Q,B,P,R}               | 
| 86  | https://lichess.org/kZjc5ckb#55 | 3225                  | 1282     | 1297     | {Q,R,P,B,R,K}               | 
| 87  | https://lichess.org/n6nEJ3SJ#64 | 3225                  | 1665     | 1498     | {R,K,Q,R,B,P}               | 
| 88  | https://lichess.org/BIvHw1vF#45 | 3225                  | 1238     | 1189     | {B,Q,P,R,K,R}               | 
| 89  | https://lichess.org/LIhm4fGM#42 | 3225                  | 2190     | 2314     | {R,Q,P,R,K,B}               | 
| 90  | https://lichess.org/rNPs5RNT#48 | 3225                  | 2261     | 2219     | {R,K,B,P,R,Q}               | 
| 91  | https://lichess.org/z7u6cozg#68 | 3225                  | 1829     | 1827     | {R,K,B,P,Q,R}               | 
| 92  | https://lichess.org/TLvXXRqZ#47 | 3225                  | 1963     | 1751     | {B,Q,R,P,R,K}               | 
| 93  | https://lichess.org/43w2s2Za#59 | 3225                  | 1974     | 1843     | {R,P,Q,B,R,K}               | 
| 94  | https://lichess.org/eUt7D1HK#55 | 3225                  | 1911     | 1701     | {B,P,Q,R,R,K}               | 
| 95  | https://lichess.org/zPEJRMUf#70 | 3225                  | 2013     | 2299     | {R,Q,K,P,B,R}               | 
| 96  | https://lichess.org/cHgkxTfy#30 | 3225                  | 1652     | 1635     | {R,K,Q,P,R,B}               | 
| 97  | https://lichess.org/QURfrSxo#35 | 3225                  | 1905     | 1983     | {R,B,Q,P,R,K}               | 
| 98  | https://lichess.org/zB6bal2U#48 | 3225                  | 1556     | 1540     | {R,R,Q,K,P,B}               | 
| 99  | https://lichess.org/zS0fz0mH#58 | 3225                  | 1646     | 1644     | {R,R,K,B,P,Q}               | 
| 100 | https://lichess.org/EQrKe216#57 | 3225                  | 1963     | 1839     | {B,R,Q,P,R,K}               | 



