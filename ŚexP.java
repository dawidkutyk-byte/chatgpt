/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 */
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class \u015aexP
implements CommandExecutor {
    public static boolean \u015b\u0106\u0179G;
    private static long CRACKME_f222f0d8_b203_41ca_a6d0_f478eb1a1c33_1c77ff4b = 8761457704929272520L;
    private static String[] CRACKME_BITCH;

    /*
     * Handled impossible loop by adding 'first' condition
     */
    static {
        CRACKME_BITCH = new String[14];
        \u015aexP.CRACKME_BITCH[0] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u283f\u283f\u28bf\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff";
        \u015aexP.CRACKME_BITCH[1] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u281b\u28a9\u28f4\u28f6\u28f6\u28f6\u28cc\u2819\u282b\u281b\u288b\u28ed\u28e4\u28e4\u28e4\u28e4\u2859\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff";
        \u015aexP.CRACKME_BITCH[2] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u285f\u28a1\u28fe\u28ff\u283f\u28db\u28db\u28db\u28db\u28db\u2873\u2806\u28bb\u28ff\u28ff\u28ff\u283f\u283f\u2837\u284c\u283b\u28ff\u28ff\u28ff\u28ff";
        \u015aexP.CRACKME_BITCH[3] = "\u28ff\u28ff\u28ff\u28ff\u280f\u28f0\u28ff\u28ff\u28f4\u28ff\u28ff\u28ff\u287f\u281f\u281b\u281b\u2812\u2804\u28b6\u28f6\u28f6\u28fe\u287f\u2836\u2812\u2832\u280c\u28bb\u28ff\u28ff";
        \u015aexP.CRACKME_BITCH[4] = "\u28ff\u28ff\u280f\u28e1\u28a8\u28dd\u287b\u283f\u28ff\u289b\u28e9\u2875\u281e\u286b\u282d\u282d\u28ed\u282d\u2824\u2808\u282d\u2812\u28d2\u2829\u282d\u282d\u28cd\u2812\u2808\u281b";
        \u015aexP.CRACKME_BITCH[5] = "\u287f\u2881\u28fe\u28ff\u28f8\u28ff\u28ff\u28f7\u28ec\u2849\u2801\u2804\u2801\u2804\u2804\u2804\u2804\u2804\u2804\u28f6\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2880";
        \u015aexP.CRACKME_BITCH[6] = "\u28a1\u28fe\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28e7\u2840\u2804\u2804\u2804\u2804\u2804\u2804\u2880\u28e0\u28ff\u28e6\u28e4\u28c0\u28c0\u28c0\u28c0\u2804\u28e4\u28fe";
        \u015aexP.CRACKME_BITCH[7] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28f7\u28f6\u28f6\u2876\u2887\u28f0\u28ff\u28ff\u28df\u283f\u283f\u283f\u283f\u283f\u283f\u28fe\u28ff\u28ff";
        \u015aexP.CRACKME_BITCH[8] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u285f\u289b\u285b\u283f\u283f\u28ff\u28e7\u28f6\u28f6\u28ff\u28ff\u28ff\u28ff\u28ff\u28f7\u28fc\u28ff\u28ff\u28ff\u28e7\u2838\u28ff\u28ff";
        \u015aexP.CRACKME_BITCH[9] = "\u2818\u28bf\u28ff\u28ff\u28ff\u28ff\u28ff\u2847\u28bf\u287f\u283f\u2826\u28e4\u28c8\u28d9\u285b\u283f\u283f\u283f\u28ff\u28ff\u28ff\u28ff\u283f\u283f\u281f\u281b\u2840\u28bb\u28ff";
        \u015aexP.CRACKME_BITCH[10] = "\u2804\u2804\u2809\u283b\u28bf\u28ff\u28ff\u28f7\u28ec\u28d9\u2833\u2836\u28b6\u28e4\u28cd\u28d9\u285b\u2813\u2812\u2836\u2836\u2836\u2836\u2816\u2892\u28db\u28db\u2801\u28fe\u28ff";
        \u015aexP.CRACKME_BITCH[11] = "\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2808\u2809\u2809\u28fb\u28ff\u28ff\u28ff\u28ff\u287f\u283f\u281b\u2803\u2804\u2819\u281b\u283f\u28bf\u28ff";
        \u015aexP.CRACKME_BITCH[12] = "\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u28ac\u28ed\u28ed\u2876\u2816\u28e2\u28e6\u28c0\u2804\u2804\u2804\u2804\u2880\u28e4\u28fe\u28ff";
        \u015aexP.CRACKME_BITCH[13] = "\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2880\u28c0\u28c0\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804";
        if (CRACKME_f222f0d8_b203_41ca_a6d0_f478eb1a1c33_1c77ff4b == 5593987582764400583L - 6510615967804542196L) {
            int n = 1302022934 - -845460713;
            if ((-498625240 >>> "\u0000\u0000".length() ^ n) != 0) {
                int cfr_ignored_1 = 0x35CD0263 ^ 0x35CD0262;
            }
        } else {
            int cfr_ignored_2 = 0x913DA1F4 ^ 0x913DA1F4;
        }
        long l = CRACKME_f222f0d8_b203_41ca_a6d0_f478eb1a1c33_1c77ff4b;
        boolean bl = true;
        block5: while (true) {
            long l2;
            if (!bl || (bl = false) || !true) {
                l = l2 / (0x9796B74AA8883DDEL ^ 0x2804FF96173CB741L);
            }
            switch ((int)l) {
                case -980839504: {
                    l2 = 0xFD77F3F877EA8296L ^ 0x8FD35F84EC549C03L;
                    continue block5;
                }
                case -924114232: {
                    break block5;
                }
                case 1333938811: {
                    l2 = 0x4C882ADF7A6D67BCL ^ 0x5B5E5623E9B532B8L;
                    continue block5;
                }
            }
            break;
        }
        \u015b\u0106\u0179G = 0x3C947C1 ^ 0x3C947C1;
    }

    /*
     * Unable to fully structure code
     */
    public static String P94faJi978mhyrF7(String var0) {
        if (\u015aexP.CRACKME_f222f0d8_b203_41ca_a6d0_f478eb1a1c33_1c77ff4b == (-207312226618509282L ^ 3703692347958316434L)) {
            if ((-1110857388 ^ 570009062 ^ (-771075929 ^ -1376407720)) != 0) {
                v0 = -7929223137163735434L == -7929223137163735433L ? 1052004754 : 1582833438 ^ 1582833439;
            }
        } else {
            -1264540178 ^ -1264540178;
        }
        while (true) {
            block31: {
                if ((v1 = (cfr_temp_0 = \u015aexP.CRACKME_f222f0d8_b203_41ca_a6d0_f478eb1a1c33_1c77ff4b - (-8522745412412679892L - -739435988400771322L)) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) continue;
                if (v1 != (5921047073185606395L == 5921047073185606396L ? 353625906 : 677093632 ^ 677093633)) break block31;
                v2 = \u015aexP.CRACKME_f222f0d8_b203_41ca_a6d0_f478eb1a1c33_1c77ff4b;
                if (true) ** GOTO lbl25
            }
            if (-5115889818758578465L == -5115889818758578464L) {
                v1 = -1334573926;
                continue;
            }
            v1 = 469196178 ^ 973873438;
        }
        block10: while (true) {
            v2 = v3 / (961732683428840506L ^ -7312730850786535356L);
lbl25:
            // 2 sources

            switch ((int)v2) {
                case -924114232: {
                    break block10;
                }
                case 101656854: {
                    if (-6251551740477519151L == -6251551740477519150L) {
                        v3 = -8789377769299533143L;
                        continue block10;
                    }
                    v3 = 6501193284546048416L >>> "\u0000\u0000".length();
                    continue block10;
                }
            }
            break;
        }
        CRACKME_68c3c853_810d_4d42_86c1_0cf47f88fc3921a617fd = new StringBuilder();
        2130355060 >>> "\u0000\u0000".length();
        CRACKME_68c3c853_810d_4d42_86c1_0cf47f88fc398c7ef2d1 = -227319289 ^ -227319289;
        while (true) {
            block30: {
                block32: {
                    block29: {
                        v4 = \u015aexP.CRACKME_f222f0d8_b203_41ca_a6d0_f478eb1a1c33_1c77ff4b;
                        if (true) ** GOTO lbl56
                        block12: while (true) {
                            v4 = v5 / (8273968058894281305L == 8273968058894281306L ? -3399536116050027009L : 8969838589924576133L - -5951454511320878081L);
lbl56:
                            // 2 sources

                            switch ((int)v4) {
                                case -2126307834: {
                                    v5 = 6956760643611364344L - -3746157653906697187L;
                                    continue block12;
                                }
                                case -1531000304: {
                                    if (-7885104337258621504L == -7885104337258621503L) {
                                        v5 = 8845262616486210828L;
                                        continue block12;
                                    }
                                    v5 = -5660957283581658493L ^ -5952942354881445662L;
                                    continue block12;
                                }
                                case -924114232: {
                                    break block12;
                                }
                            }
                            break;
                        }
                        1253813976 - -228290815;
                        if (CRACKME_68c3c853_810d_4d42_86c1_0cf47f88fc398c7ef2d1 >= CRACKME_68c3c853_810d_4d42_86c1_0cf47f88fc39e69d3d49.length()) return CRACKME_68c3c853_810d_4d42_86c1_0cf47f88fc3921a617fd.toString();
                        v6 = -3270669316411337548L == -3270669316411337547L ? 1985222197 : -1815853647 - -1472858101;
                        while (true) {
                            if ((v7 = (cfr_temp_1 = \u015aexP.CRACKME_f222f0d8_b203_41ca_a6d0_f478eb1a1c33_1c77ff4b - (-3724989882806058053L - -7778860617545873111L)) == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1)) == false) continue;
                            if (v7 == (-5555440454826792938L == -5555440454826792937L ? -1681516578 : 641682898 ^ 641682899)) break;
                            if (-1915510623587499658L == -1915510623587499657L) {
                                v7 = -1052775956;
                                continue;
                            }
                            v7 = 124026262 ^ -1555238718;
                        }
                        CRACKME_68c3c853_810d_4d42_86c1_0cf47f88fc39a8d25d4a = CRACKME_68c3c853_810d_4d42_86c1_0cf47f88fc39e69d3d49.charAt(CRACKME_68c3c853_810d_4d42_86c1_0cf47f88fc398c7ef2d1);
                        while (true) {
                            if ((v8 = (cfr_temp_2 = \u015aexP.CRACKME_f222f0d8_b203_41ca_a6d0_f478eb1a1c33_1c77ff4b - (3379223516306769502L - 8867609228964318488L)) == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1)) == false) continue;
                            if (v8 == (-6844492720344909408L == -6844492720344909407L ? 1199940469 : 1586852763 ^ 1586852762)) {
                                v9 = Character.toString(CRACKME_68c3c853_810d_4d42_86c1_0cf47f88fc39a8d25d4a);
                                var5_4 = new byte[-877871902 ^ -877871889];
                                var5_4[1381474685 ^ 1381474685] = -952219095 ^ -952219022;
                                var5_4[-1752837867 ^ -1752837868] = 175117091 ^ 175117122;
                                if (2867290106787568675L == 2867290106787568676L) {
                                    break;
                                }
                                break block29;
                            }
                            v8 = 288834045 ^ -2116733289;
                        }
                        v10 = -1166343834;
                        break block32;
                    }
                    v10 = -1561983266 ^ -1561983245;
                }
                var5_4[2122087698 ^ 2122087703] = v10;
                var5_4[1158935374 ^ 1158935370] = -732690566 ^ -732690629;
                var5_4[-2112149 ^ -2112148] = 2034351416 ^ 2034351368;
                var5_4[-3494875530787853856L == -3494875530787853855L ? 593400416 : 2061843521 ^ 2061843523] = -866842690 ^ -866842733;
                var5_4[-5322106350535099459L == -5322106350535099458L ? -2007021966 : -1557915296 ^ -1557915287] = 1610108201 ^ 1610108176;
                var5_4[-1042078871 ^ -1042078875] = 1325842673 ^ 1325842604;
                var5_4[157507964 ^ 157507958] = -506478306 ^ -506478288;
                var5_4[-1961279335 ^ -1961279334] = -1223591186 ^ -1223591276;
                var5_4[-1335418825 ^ -1335418817] = 867248564 ^ 867248537;
                var5_4[150195120985754878L == 150195120985754879L ? -29407917 : 1089518370 ^ 1089518372] = -7322250032230384346L == -7322250032230384345L ? 42822511 : -1800408163 ^ -1800408121;
                var5_4[369054905 ^ 369054898] = -850088803 ^ -850088766;
                v11 = new String(var5_4, "UTF-8");
                while (true) {
                    if ((v12 = (cfr_temp_3 = \u015aexP.CRACKME_f222f0d8_b203_41ca_a6d0_f478eb1a1c33_1c77ff4b - (-1478316786289913187L - -511107370641307630L)) == 0L ? 0 : (cfr_temp_3 < 0L ? -1 : 1)) == false) continue;
                    if (v12 == (1156960559 ^ 1156960558)) {
                        if (!v9.matches(v11)) {
                            break;
                        }
                        break block30;
                    }
                    v12 = -60489965 - -2068682982;
                }
                536353651 ^ 1075481741;
                while (true) {
                    if ((v13 = (cfr_temp_4 = \u015aexP.CRACKME_f222f0d8_b203_41ca_a6d0_f478eb1a1c33_1c77ff4b - (4906089691254535756L >>> "\u0000\u0000".length())) == 0L ? 0 : (cfr_temp_4 < 0L ? -1 : 1)) == false) continue;
                    if (v13 == (-656120444487269920L == -656120444487269919L ? 701474843 : -2029088497 ^ -2029088498)) {
                        CRACKME_68c3c853_810d_4d42_86c1_0cf47f88fc3921a617fd.append(CRACKME_68c3c853_810d_4d42_86c1_0cf47f88fc39a8d25d4a);
                        break;
                    }
                    v13 = -658370354 ^ 98173839;
                }
            }
            v14 = 2192625290290654023L == 2192625290290654024L ? -348364805 : 338929388 - -927808758;
            -709106900 - 455044731;
            -817605111 ^ -1823165648;
            ++CRACKME_68c3c853_810d_4d42_86c1_0cf47f88fc398c7ef2d1;
        }
    }

    public \u015aexP() {
        \u015aexP CRACKME_bced4b60_8763_47d5_ba9f_d77b038a983bb573efd7;
        if (CRACKME_f222f0d8_b203_41ca_a6d0_f478eb1a1c33_1c77ff4b == (0xADAB2E0877216034L ^ 0xA52AAD0956377470L)) {
            if ((-1514495140 >>> "\u0000\u0000".length() ^ (0xCA381575 ^ 0xB5C7EA8A)) != 0) {
                int cfr_ignored_0 = 0x870367D4 ^ 0x870367D5;
            }
        } else {
            int cfr_ignored_1 = 0x38BC0DD6 ^ 0x38BC0DD6;
        }
    }

    /*
     * Exception decompiling
     */
    public boolean onCommand(@NotNull CommandSender var1_1, @NotNull Command var2_2, @NotNull String var3_3, @NotNull String[] var4_4) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Extractable last case doesn't follow previous, and can't clone.
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:611)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.rebuildSwitches(SwitchReplacer.java:406)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:601)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doClass(Driver.java:84)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:78)
         *     at the.bytecode.club.bytecodeviewer.decompilers.impl.CFRDecompiler.decompile(CFRDecompiler.java:89)
         *     at the.bytecode.club.bytecodeviewer.decompilers.impl.CFRDecompiler.decompileToZip(CFRDecompiler.java:133)
         *     at the.bytecode.club.bytecodeviewer.resources.ResourceDecompiling.decompileSaveAll(ResourceDecompiling.java:261)
         *     at the.bytecode.club.bytecodeviewer.resources.ResourceDecompiling.lambda$decompileSaveAll$0(ResourceDecompiling.java:111)
         *     at java.base/java.lang.Thread.run(Thread.java:1575)
         */
        throw new IllegalStateException("Decompilation failed");
    }
}
