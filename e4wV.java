/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  0QZ8
 *  org.bukkit.event.Listener
 *  zNb\u015b
 *  \u017b\u017c\u0107m
 */
import java.lang.invoke.LambdaMetafactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.event.Listener;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public class e4wV
implements Listener {
    public static final ArrayList<String> CW\u0142D;
    public static Map<String, String> ilVD;
    private static long CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c = -2249142355274092727L;
    private static final LinkedHashMap<String, List<String>> wO\u0119g;
    private static Pattern qL\u017a2;
    private static final Map<String, String> Vajs;
    private static String[] CRACKME_BITCH;
    private static final LinkedHashMap<String, List<String>> EOfG;

    /*
     * Unable to fully structure code
     */
    static {
        e4wV.CRACKME_BITCH = new String[13];
        e4wV.CRACKME_BITCH[0] = "\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u28e0\u28e4\u28e4\u28e4\u28e4\u28e4\u28c4\u2840\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
        e4wV.CRACKME_BITCH[1] = "\u2800\u2800\u2800\u2800\u2800\u28b0\u287f\u280b\u2801\u2800\u2800\u2808\u2809\u2819\u283b\u28f7\u28c4\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
        e4wV.CRACKME_BITCH[2] = "\u2800\u2800\u2800\u2800\u2880\u28ff\u2807\u2800\u2880\u28f4\u28f6\u287e\u283f\u283f\u283f\u28bf\u28ff\u28e6\u2840\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
        e4wV.CRACKME_BITCH[3] = "\u2800\u2800\u28c0\u28c0\u28f8\u287f\u2800\u2800\u28b8\u28ff\u28c7\u2800\u2800\u2800\u2800\u2800\u2800\u2819\u28f7\u2840\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
        e4wV.CRACKME_BITCH[4] = "\u2800\u28fe\u285f\u281b\u28ff\u2847\u2800\u2800\u28b8\u28ff\u28ff\u28f7\u28e4\u28e4\u28e4\u28e4\u28f6\u28f6\u28ff\u2807\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u28c0\u2800\u2800";
        e4wV.CRACKME_BITCH[5] = "\u2880\u28ff\u2800\u2880\u28ff\u2847\u2800\u2800\u2800\u283b\u28bf\u28ff\u28ff\u28ff\u28ff\u28ff\u283f\u28ff\u284f\u2800\u2800\u2800\u2800\u28b4\u28f6\u28f6\u28ff\u28ff\u28ff\u28c6";
        e4wV.CRACKME_BITCH[6] = "\u28b8\u28ff\u2800\u28b8\u28ff\u2847\u2800\u2800\u2800\u2800\u2800\u2808\u2809\u2801\u2800\u2800\u2800\u28ff\u2847\u28c0\u28e0\u28f4\u28fe\u28ee\u28dd\u283f\u283f\u283f\u28fb\u285f";
        e4wV.CRACKME_BITCH[7] = "\u28b8\u28ff\u2800\u2818\u28ff\u2847\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u28e0\u28f6\u28fe\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u287f\u2801\u2809\u2800";
        e4wV.CRACKME_BITCH[8] = "\u2838\u28ff\u2800\u2800\u28ff\u2847\u2800\u2800\u2800\u2800\u2800\u28e0\u28fe\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u287f\u281f\u2809\u2800\u2800\u2800\u2800";
        e4wV.CRACKME_BITCH[9] = "\u2800\u283b\u28f7\u28f6\u28ff\u28c7\u2800\u2800\u2800\u28a0\u28fc\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28db\u28db\u28fb\u2809\u2801\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
        e4wV.CRACKME_BITCH[10] = "\u2800\u2800\u2800\u2800\u28b8\u28ff\u2800\u2800\u2800\u28b8\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u2847\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
        e4wV.CRACKME_BITCH[11] = "\u2800\u2800\u2800\u2800\u28b8\u28ff\u28c0\u28c0\u28c0\u28fc\u287f\u28bf\u28ff\u28ff\u28ff\u28ff\u28ff\u287f\u28ff\u28ff\u287f\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
        e4wV.CRACKME_BITCH[12] = "\u2800\u2800\u2800\u2800\u2800\u2819\u281b\u281b\u281b\u280b\u2801\u2800\u2819\u283b\u283f\u281f\u280b\u2811\u281b\u280b\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
        if (e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c == (8709137452752894969L ^ -5761409872515724893L)) {
            v0 = 561563796 ^ 561563797;
        } else {
            538787015 ^ 538787015;
        }
        v1 = new HashMap<String, String>();
        v2 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
        if (true) ** GOTO lbl29
        block26: while (true) {
            v2 = v3 / (-8772697581308730010L ^ 359066055977513791L);
lbl29:
            // 2 sources

            switch ((int)v2) {
                case -1750549743: {
                    v3 = -148887088086605829L ^ -429256757798405278L;
                    continue block26;
                }
                case -1063637175: {
                    break block26;
                }
                case -504351734: {
                    v3 = 8812400564279489203L ^ -5066719301778887578L;
                    continue block26;
                }
                case 193435313: {
                    v3 = -5599479199403681019L - -9095183395365331653L;
                    continue block26;
                }
            }
            break;
        }
        e4wV.Vajs = v1;
        1539257844 >>> "\u0000\u0000".length();
        while (true) {
            block39: {
                if ((v4 = (cfr_temp_0 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-3322463008301710876L >>> "\u0000\u0000".length())) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) {
                    continue;
                }
                if (v4 != -553576876 - -553576875) break block39;
                e4wV.CW\u0142D = new ArrayList<E>();
                v5 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                if (true) ** GOTO lbl63
            }
            v4 = 1336902364 ^ 408184741;
        }
        block28: while (true) {
            v5 = (-8542099188410584872L ^ -3625868705056291854L) / v6;
lbl63:
            // 2 sources

            switch ((int)v5) {
                case -1063637175: {
                    break block28;
                }
                case 1194463458: {
                    v6 = -7065166653634543430L ^ -613325200603953516L;
                    continue block28;
                }
            }
            break;
        }
        while (true) {
            block40: {
                if ((v7 = (cfr_temp_1 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-7968050988851705687L ^ 5190076366561126739L)) == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1)) == false) {
                    continue;
                }
                if (v7 != (-1895244650 ^ -1895244649)) break block40;
                v8 = new LinkedHashMap<K, V>();
                v9 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                if (true) ** GOTO lbl87
            }
            v7 = 1062165167 ^ 1099478066;
        }
        block30: while (true) {
            v10 = 3798978879558555084L ^ -8596657919945138412L;
            v9 = v11 / v10;
lbl87:
            // 2 sources

            switch ((int)v9) {
                case -1128285079: {
                    v11 = 3615021666093520070L ^ 8973391049745714093L;
                    continue block30;
                }
                case -1063637175: {
                    break block30;
                }
                case 964550998: {
                    v11 = -1332232098687668918L ^ 3138898635550095679L;
                    continue block30;
                }
                case 1785914649: {
                    v11 = 3324046735417316037L ^ -8165330910007496812L;
                    continue block30;
                }
            }
            break;
        }
        e4wV.EOfG = v8;
        -1545983320 - 1448321791;
        v12 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
        if (true) ** GOTO lbl108
        block31: while (true) {
            v12 = v13 / (-4422898042322865008L >>> "\u0000\u0000".length());
lbl108:
            // 2 sources

            switch ((int)v12) {
                case -1063637175: {
                    break block31;
                }
                case 1904177752: {
                    v13 = -4154419909563903747L ^ -5560984922847828296L;
                    continue block31;
                }
                case 2136913103: {
                    v13 = 5755167055083441320L >>> "\u0000\u0000".length();
                    continue block31;
                }
            }
            break;
        }
        v14 = new LinkedHashMap<K, V>();
        while (true) {
            block41: {
                if ((v15 = (cfr_temp_2 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-3824327946603461873L - 9204838964823983257L)) == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1)) == false) {
                    continue;
                }
                v16 = -1827348846 ^ 1827348845;
                if (v15 != v16) break block41;
                e4wV.wO\u0119g = v14;
                v17 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                if (true) ** GOTO lbl133
            }
            v15 = 1484457117 ^ -2095807764;
        }
        block33: while (true) {
            v18 = 2861281775768601235L ^ 3093022128810669095L;
            v17 = v19 / v18;
lbl133:
            // 2 sources

            switch ((int)v17) {
                case -1063637175: {
                    break block33;
                }
                case 215294032: {
                    v19 = -7381538986708127066L - 8579493634413419028L;
                    continue block33;
                }
                case 425576930: {
                    v19 = -4459597827102082390L ^ 728446154105472292L;
                    continue block33;
                }
            }
            break;
        }
        e4wV.Q5Klck-ESkM8cIdd();
    }

    private static /* synthetic */ List K7kB9-ZPcj7mLtVG(String string) {
        if (CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c == (5230625989973577861L == 5230625989973577862L ? -4143410991826434806L : 67055047578264272L - 3629402968955728306L)) {
            if (((-8282080288595576226L == -8282080288595576225L ? -240993817 : 600392665 - -1892182165) ^ (-1745045769341800659L == -1745045769341800658L ? -1636213234 : -1969301494 - 178182155)) != 0) {
                int n = -2263184416869381837L == -2263184416869381836L ? -1364304139 : 0xA3D9255C ^ 0xA3D9255D;
            }
        } else {
            int cfr_ignored_1 = 0x6551DC16 ^ 0x6551DC16;
        }
        while (true) {
            long l;
            long l2;
            if ((l2 = (l = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-6213434343427892339L == -6213434343427892338L ? 8065019665417727214L : -2196627124626631114L - -277773460266968065L)) == 0L ? 0 : (l < 0L ? -1 : 1)) == false) {
                continue;
            }
            if (l2 == (0x7E1C6E80 ^ 0x81E3917F)) {
                return new ArrayList();
            }
            l2 = 0x1A963E9A ^ 0xF497E96C;
        }
    }

    /*
     * Handled impossible loop by adding 'first' condition
     */
    public static CompletableFuture<Map<String, String>> u_c4ZvPaZs10lF3x() {
        if (CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c == (0x4EE7707489A6D55DL ^ 0xA8880FCF88668295L)) {
            int n = 0x29C45771 ^ 0x563BA88E;
            if ((0x805E9CE4 ^ 0x14FFDB5A ^ n) != 0) {
                int cfr_ignored_1 = 0x96317840 ^ 0x96317841;
            }
        } else {
            int n = 0x43ABA395 ^ 0x43ABA395;
        }
        long l = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
        boolean bl = true;
        block5: while (true) {
            long l2;
            if (!bl || (bl = false) || !true) {
                l = l2 / (0x8BC2598B2F91A5D7L ^ 0x67D6EA715AEAF49DL);
            }
            switch ((int)l) {
                case -1063637175: {
                    return CompletableFuture.supplyAsync(() -> {
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
                         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1050)
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
                    });
                }
                case -1055155273: {
                    l2 = 0x9783C2C6BE4ABC61L ^ 0x49E9042076C6660DL;
                    continue block5;
                }
                case 1387577385: {
                    l2 = -8741461437536152930L - 7435024578368022521L;
                    continue block5;
                }
            }
            break;
        }
        return CompletableFuture.supplyAsync(() -> {
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
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1050)
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
        });
    }

    /*
     * Unable to fully structure code
     */
    public static void Q5Klck-ESkM8cIdd() {
        block171: {
            block159: {
                block170: {
                    block158: {
                        block154: {
                            block169: {
                                block157: {
                                    block162: {
                                        block153: {
                                            block161: {
                                                block152: {
                                                    if (e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c == (-8023732929708984732L ^ 5834808081203160777L)) {
                                                        if ((-940863813 ^ 42931863 ^ 840383354 - -1307100293) != 0) {
                                                            390717824 ^ 390717825;
                                                        }
                                                    } else {
                                                        -835021977 ^ -835021977;
                                                    }
                                                    1916233072 - 1038967432;
                                                    while (true) {
                                                        block160: {
                                                            if ((v0 = (cfr_temp_0 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-7971693089275581083L - 6875745638744083235L)) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) continue;
                                                            if (v0 != (-6787964765628212536L == -6787964765628212535L ? -1010198436 : 1754148824 ^ -1754148825)) break block160;
                                                            v1 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                                            if (true) ** GOTO lbl24
                                                        }
                                                        v0 = -1397673886 - 884390205;
                                                    }
                                                    block81: while (true) {
                                                        v1 = v2 / (-6816196612399933736L - 8688537849464858138L);
lbl24:
                                                        // 2 sources

                                                        switch ((int)v1) {
                                                            case -1063637175: {
                                                                break block81;
                                                            }
                                                            case -866361081: {
                                                                if (-4769339287644010585L == -4769339287644010584L) {
                                                                    v2 = 6778462241172157575L;
                                                                    continue block81;
                                                                }
                                                                v2 = -8177992622318180309L - 954574359512288578L;
                                                                continue block81;
                                                            }
                                                            case 1740385575: {
                                                                v2 = -3919490063059870991L - -4297239185060962103L;
                                                                continue block81;
                                                            }
                                                            case 1995897202: {
                                                                v2 = -8375641488099367225L ^ 5728081008422720534L;
                                                                continue block81;
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    e4wV.Vajs.clear();
                                                    v3 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                                    if (true) ** GOTO lbl46
                                                    block82: while (true) {
                                                        v3 = v4 / (8317304399069658005L ^ 3717765922114114761L);
lbl46:
                                                        // 2 sources

                                                        switch ((int)v3) {
                                                            case -1063637175: {
                                                                break block82;
                                                            }
                                                            case -227081430: {
                                                                if (-775897536702002900L == -775897536702002899L) {
                                                                    v4 = -3863826053530563074L;
                                                                    continue block82;
                                                                }
                                                                v4 = -4612626149319558969L ^ -1397630380329161910L;
                                                                continue block82;
                                                            }
                                                            case 965164080: {
                                                                v4 = -8764033443546985380L >>> "\u0000\u0000".length();
                                                                continue block82;
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    v5 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                                    if (true) ** GOTO lbl62
                                                    block83: while (true) {
                                                        v5 = v6 / (-3904182607389309427L - -855525645009223606L);
lbl62:
                                                        // 2 sources

                                                        switch ((int)v5) {
                                                            case -1659253932: {
                                                                v6 = -190919470700052609L ^ 2169803121250011622L;
                                                                continue block83;
                                                            }
                                                            case -1063637175: {
                                                                break block83;
                                                            }
                                                            case -901106566: {
                                                                v6 = 8609328993199282708L >>> "\u0000\u0000".length();
                                                                continue block83;
                                                            }
                                                            case 1194969171: {
                                                                v6 = -2140049283719611487L - 6744165539696704307L;
                                                                continue block83;
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    e4wV.CW\u0142D.clear();
                                                    v7 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                                    if (true) ** GOTO lbl82
                                                    block84: while (true) {
                                                        v7 = v8 / (2198760284321154204L - 3097923112048716071L);
lbl82:
                                                        // 2 sources

                                                        switch ((int)v7) {
                                                            case -1063637175: {
                                                                break block84;
                                                            }
                                                            case -733651969: {
                                                                v8 = -804112571131171840L - 6024839511458626947L;
                                                                continue block84;
                                                            }
                                                            case 1732652755: {
                                                                v8 = 5124832218292658220L ^ -5484122676298838019L;
                                                                continue block84;
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    while (true) {
                                                        if ((v9 = (cfr_temp_1 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-6720890438150405404L ^ -248360236639264468L)) == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1)) == false) continue;
                                                        if (v9 == (-1832174898 ^ 1832174897)) break;
                                                        if (-1794141082383348621L == -1794141082383348620L) {
                                                            v9 = -1694639681;
                                                            continue;
                                                        }
                                                        v9 = -1751411408 >>> "\u0000\u0000".length();
                                                    }
                                                    e4wV.EOfG.clear();
                                                    while (true) {
                                                        if ((v10 = (cfr_temp_2 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (2844370884327159433L == 2844370884327159434L ? -8278508756787744092L : 3518466329304008825L ^ 4928465903328641076L)) == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1)) == false) continue;
                                                        if (v10 == (-744990534 ^ 744990533)) {
                                                            e4wV.wO\u0119g.clear();
                                                            var8 = new byte[-1605432532 ^ -1605432534];
                                                            if (1711167157542243515L == 1711167157542243516L) {
                                                                break;
                                                            }
                                                            break block152;
                                                        }
                                                        if (-1552201130246495808L == -1552201130246495807L) {
                                                            v10 = -72579200;
                                                            continue;
                                                        }
                                                        v10 = -39174477 - 288454578;
                                                    }
                                                    v11 = 1416703155;
                                                    break block161;
                                                }
                                                v11 = 1242637192 ^ 1242637294;
                                            }
                                            var8[397620422 ^ 397620420] = v11;
                                            var8[-631489690 ^ -631489690] = -1951712141 ^ -1951712236;
                                            var8[-1147713646 ^ -1147713647] = -900763518 ^ -900763402;
                                            var8[-503214361 ^ -503214362] = 4473607801747695740L == 4473607801747695741L ? -1870642727 : 496996192 ^ 496996105;
                                            var8[2031191600 ^ 2031191605] = 1149986845 ^ 1149986937;
                                            var8[1553431134 ^ 1553431130] = 7451613102552388755L == 7451613102552388756L ? 798281759 : -1967581864 ^ -1967581935;
                                            v12 = new String(var8, "UTF-8");
                                            while (true) {
                                                if ((v13 = (cfr_temp_3 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (4058528963714625241L ^ 6470974952465477789L)) == 0L ? 0 : (cfr_temp_3 < 0L ? -1 : 1)) == false) continue;
                                                if (v13 == 1844881526 - 1844881527) break;
                                                if (-7825819917877249188L == -7825819917877249187L) {
                                                    v13 = 152216749;
                                                    continue;
                                                }
                                                v13 = 1699646006 ^ -1733316151;
                                            }
                                            1796213724 >>> "\u0000\u0000".length();
                                            var0_1 = \u017b\u017c\u0107m.\u01060C6.getStringList(v12);
                                            while (true) {
                                                if ((v14 = (cfr_temp_4 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-3531114616579677178L ^ -7325131812521223284L)) == 0L ? 0 : (cfr_temp_4 < 0L ? -1 : 1)) == false) continue;
                                                if (v14 == (-1202092921 ^ 1202092920)) break;
                                                v14 = 1882585454 - 1626755771;
                                            }
                                            var8 = new byte[201574200 ^ 201574203];
                                            var8[-1495841787 ^ -1495841785] = -1777774207 ^ -1777774167;
                                            var8[-2089482876 ^ -2089482875] = 1235499203 ^ 1235499169;
                                            var8[1727999608 ^ 1727999608] = 1599902274 ^ 1599902238;
                                            v15 = new String(var8, "UTF-8");
                                            while (true) {
                                                if ((v16 = (cfr_temp_5 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (3372143128322277127L == 3372143128322277128L ? 624605777025024299L : -4617039994652397495L ^ -1060595061733716554L)) == 0L ? 0 : (cfr_temp_5 < 0L ? -1 : 1)) == false) continue;
                                                if (v16 == (-1902332160 ^ 1902332159)) {
                                                    CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e891e712ba = new StringBuilder(v15);
                                                    if (53263643600298478L == 53263643600298479L) {
                                                        break;
                                                    }
                                                    break block153;
                                                }
                                                if (5780645338989539966L == 5780645338989539967L) {
                                                    v16 = 216226251;
                                                    continue;
                                                }
                                                v16 = 522589049 ^ 516740377;
                                            }
                                            v17 = 1511604779;
                                            break block162;
                                        }
                                        v17 = -1428986407 ^ -136181590;
                                    }
                                    while (true) {
                                        if ((v18 = (cfr_temp_6 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-3282337042744268499L - 8696390840660666342L)) == 0L ? 0 : (cfr_temp_6 < 0L ? -1 : 1)) == false) continue;
                                        if (v18 == (5541884999437378576L == 5541884999437378577L ? -842708310 : -672140889 ^ 672140888)) break;
                                        v18 = 1890224051 - -1516442602;
                                    }
                                    var2_3 = CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e831561066.iterator();
                                    block91: while (true) {
                                        block167: {
                                            block156: {
                                                block165: {
                                                    block155: {
                                                        block163: {
                                                            v19 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                                            if (true) ** GOTO lbl189
                                                            block92: while (true) {
                                                                v19 = v20 / (6588951418405998349L - 8497118286784801919L);
lbl189:
                                                                // 2 sources

                                                                switch ((int)v19) {
                                                                    case -1870003957: {
                                                                        if (7430308619417309699L == 7430308619417309700L) {
                                                                            v20 = -2529831710514622646L;
                                                                            continue block92;
                                                                        }
                                                                        v20 = 5376524700419314448L >>> "\u0000\u0000".length();
                                                                        continue block92;
                                                                    }
                                                                    case -1097945802: {
                                                                        v20 = 1052419750398638055L ^ -4994060872533001502L;
                                                                        continue block92;
                                                                    }
                                                                    case -1063637175: {
                                                                        break block92;
                                                                    }
                                                                    case 778665479: {
                                                                        v20 = 8352168908271357662L - 8308699566222937370L;
                                                                        continue block92;
                                                                    }
                                                                }
                                                                break;
                                                            }
                                                            1058903744 ^ -1051467432;
                                                            if (!var2_3.hasNext()) break block163;
                                                            2119476639 - -197613805;
                                                            CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e8ac15246a = (String)var2_3.next();
                                                            var8 = new byte[1449761871 ^ 1449761869];
                                                            var8[-343582368 ^ -343582367] = 1382815197 ^ 1382815203;
                                                            var8[-2822842055405274244L == -2822842055405274243L ? 414725415 : 329759029 ^ 329759029] = 43500365 ^ 43500384;
                                                            v21 = new String(var8, "UTF-8");
                                                            v22 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                                            if (true) ** GOTO lbl231
                                                        }
                                                        if (CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e891e712ba.length() > (7343790256388368433L == 7343790256388368434L ? 627416104 : -943762127 ^ -943762126)) {
                                                            break;
                                                        }
                                                        break block154;
                                                        block93: while (true) {
                                                            v22 = v23 / (-5553006288453577722L == -5553006288453577721L ? 3627099628326530881L : -9189584793670349992L ^ -2109019135210259205L);
lbl231:
                                                            // 2 sources

                                                            switch ((int)v22) {
                                                                case -1063637175: {
                                                                    break block93;
                                                                }
                                                                case 1249917210: {
                                                                    if (-4691215647988574873L == -4691215647988574872L) {
                                                                        v23 = 746282066661367907L;
                                                                        continue block93;
                                                                    }
                                                                    v23 = -7061942664752621398L - 5371359600533338578L;
                                                                    continue block93;
                                                                }
                                                                case 1718886999: {
                                                                    v23 = 3913984162732233220L >>> "\u0000\u0000".length();
                                                                    continue block93;
                                                                }
                                                                case 1974981226: {
                                                                    v23 = -422170583190663496L ^ -1890543502365407093L;
                                                                    continue block93;
                                                                }
                                                            }
                                                            break;
                                                        }
                                                        CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e811d29b81 = CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e8ac15246a.indexOf(v21);
                                                        704269838 ^ -21291910;
                                                        if (CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e811d29b81 <= 0) continue;
                                                        v24 = CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e8ac15246a.substring(1607147857 ^ 1607147857, CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e811d29b81);
                                                        while (true) {
                                                            block164: {
                                                                if ((v25 = (cfr_temp_7 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (1503551813912925763L ^ -2584461509034448427L)) == 0L ? 0 : (cfr_temp_7 < 0L ? -1 : 1)) == false) continue;
                                                                if (v25 != (969884553 ^ 969884552)) break block164;
                                                                var5_6 = v24.trim();
                                                                -1375216436 - 97496392;
                                                                v26 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                                                if (true) ** GOTO lbl279
                                                            }
                                                            v25 = -1845475114 ^ -1240998852;
                                                        }
                                                        block95: while (true) {
                                                            v26 = v27 / (-7671303176652636261L == -7671303176652636260L ? 5943424822422492283L : -2336439490895724300L - 8722659703792820058L);
lbl279:
                                                            // 2 sources

                                                            switch ((int)v26) {
                                                                case -1063637175: {
                                                                    break block95;
                                                                }
                                                                case -579810864: {
                                                                    if (-2738392151280913639L == -2738392151280913638L) {
                                                                        v27 = 4446994418964140937L;
                                                                        continue block95;
                                                                    }
                                                                    v27 = 1631951061407591382L - -2817274414237688850L;
                                                                    continue block95;
                                                                }
                                                                case -519048118: {
                                                                    v27 = 575066312973322432L >>> "\u0000\u0000".length();
                                                                    continue block95;
                                                                }
                                                                case 1092311430: {
                                                                    v27 = -1260160416541832809L ^ 7712953714218072139L;
                                                                    continue block95;
                                                                }
                                                            }
                                                            break;
                                                        }
                                                        v28 = CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e8ac15246a.substring(CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e811d29b81 + (1096007697 ^ 1096007699));
                                                        v29 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                                        if (true) ** GOTO lbl300
                                                        block96: while (true) {
                                                            v29 = v30 / (2977227162064316275L ^ -4821164158097941442L);
lbl300:
                                                            // 2 sources

                                                            switch ((int)v29) {
                                                                case -1177249027: {
                                                                    if (3611413871616088443L == 3611413871616088444L) {
                                                                        v30 = 7192512389818486169L;
                                                                        continue block96;
                                                                    }
                                                                    v30 = 6056965981989618130L - -2965344662359648473L;
                                                                    continue block96;
                                                                }
                                                                case -1063637175: {
                                                                    break block96;
                                                                }
                                                                case 1004991191: {
                                                                    v30 = 4521603001412652945L ^ -8142071183357003892L;
                                                                    continue block96;
                                                                }
                                                            }
                                                            break;
                                                        }
                                                        v31 = v28.trim();
                                                        var8 = new byte[-1358283687 ^ -1358283688];
                                                        var8[525819620 ^ 525819620] = -1197139839 ^ -1197139805;
                                                        v32 = new String(var8, "UTF-8");
                                                        var8 = new byte[-2098153040 ^ -2098153040];
                                                        v33 = new String(var8, "UTF-8");
                                                        v34 = -7559560370351457360L == -7559560370351457359L ? 1870358922 : 1328717028 ^ -1960362212;
                                                        v35 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                                        if (true) ** GOTO lbl328
                                                        block97: while (true) {
                                                            v35 = v36 / (2330894485818506529L == 2330894485818506530L ? 3801485358535190924L : -7969075856480228818L ^ 6669926549565213015L);
lbl328:
                                                            // 2 sources

                                                            switch ((int)v35) {
                                                                case -1993143100: {
                                                                    v36 = 2783544646524830588L ^ 5381785534713868922L;
                                                                    continue block97;
                                                                }
                                                                case -1063637175: {
                                                                    break block97;
                                                                }
                                                                case -886184245: {
                                                                    if (-1331470840690026169L == -1331470840690026168L) {
                                                                        v36 = 2472180740837109388L;
                                                                        continue block97;
                                                                    }
                                                                    v36 = 799134336538577847L ^ -5132574221597465039L;
                                                                    continue block97;
                                                                }
                                                                case 1885078511: {
                                                                    if (-6735409734526385519L == -6735409734526385518L) {
                                                                        v36 = 1571359340047459787L;
                                                                        continue block97;
                                                                    }
                                                                    v36 = 6501134123032596069L ^ -8852077987821721059L;
                                                                    continue block97;
                                                                }
                                                            }
                                                            break;
                                                        }
                                                        CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e88a47ea2e = v31.replace(v32, v33);
                                                        v37 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                                        if (true) ** GOTO lbl351
                                                        block98: while (true) {
                                                            v37 = v38 / (-2230785390498659494L ^ 1368398494130195060L);
lbl351:
                                                            // 2 sources

                                                            switch ((int)v37) {
                                                                case -1063637175: {
                                                                    break block98;
                                                                }
                                                                case -707182147: {
                                                                    v38 = -5708734786413809184L ^ -3648727385780278738L;
                                                                    continue block98;
                                                                }
                                                                case 393532756: {
                                                                    v38 = 6618201652157709873L ^ 1072987777839239718L;
                                                                    continue block98;
                                                                }
                                                                case 1057039426: {
                                                                    v38 = -8480335748952452227L ^ 7820160926469053561L;
                                                                    continue block98;
                                                                }
                                                            }
                                                            break;
                                                        }
                                                        e4wV.Vajs.put((String)CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e82c60b550, CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e88a47ea2e);
                                                        v39 = -4869023225782961882L == -4869023225782961881L ? -787198400 : -1667672315 - -2097311722;
                                                        while (true) {
                                                            if ((v40 = (cfr_temp_8 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-5145191308531759856L - -1980028448556554118L)) == 0L ? 0 : (cfr_temp_8 < 0L ? -1 : 1)) == false) continue;
                                                            if (v40 == (1148706460726647193L == 1148706460726647194L ? 857878196 : 237176476 ^ 237176477)) {
                                                                if (190943886461595743L == 190943886461595744L) {
                                                                    break;
                                                                }
                                                                break block155;
                                                            }
                                                            if (-4437490428802915894L == -4437490428802915893L) {
                                                                v40 = -932721326;
                                                                continue;
                                                            }
                                                            v40 = 1410079106 ^ -1506841578;
                                                        }
                                                        v41 = 179136153;
                                                        break block165;
                                                    }
                                                    v41 = 1266049811 ^ 1487705462;
                                                }
                                                -59675956 >>> "\u0000\u0000".length();
                                                var8 = new byte[506078615333094095L == 506078615333094096L ? 1376386149 : -1643560348 ^ -1643560347];
                                                var8[-809981864 ^ -809981864] = 1708197660 ^ 1708197699;
                                                v42 = new String(var8, "UTF-8");
                                                var8 = new byte[2816407110172176349L == 2816407110172176350L ? 1896512378 : -298825602 ^ -298825601];
                                                var8[1447797845 ^ 1447797845] = -7931109034829152665L == -7931109034829152664L ? -1024301189 : 1980506248 ^ 1980506277;
                                                v43 = new String(var8, "UTF-8");
                                                1187962398 - -919615135;
                                                while (true) {
                                                    block166: {
                                                        if ((v44 = (cfr_temp_9 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (2205690964508180721L ^ 7513758014986887109L)) == 0L ? 0 : (cfr_temp_9 < 0L ? -1 : 1)) == false) continue;
                                                        if (v44 != (-2002677740 ^ 2002677739)) break block166;
                                                        v45 = CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e82c60b550.replace(v42, v43);
                                                        v46 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                                        if (true) ** GOTO lbl409
                                                    }
                                                    v44 = -1246480615 - -1322962443;
                                                }
                                                block101: while (true) {
                                                    v46 = v47 / (2190815583052812655L == 2190815583052812656L ? 7770923355383256805L : 9178372075375009426L ^ -9013082457999332815L);
lbl409:
                                                    // 2 sources

                                                    switch ((int)v46) {
                                                        case -1457377386: {
                                                            if (8554654145360423105L == 8554654145360423106L) {
                                                                v47 = -1180543991617202095L;
                                                                continue block101;
                                                            }
                                                            v47 = 2702121738522092458L ^ -30350723836139203L;
                                                            continue block101;
                                                        }
                                                        case -1063637175: {
                                                            break block101;
                                                        }
                                                        case 583911739: {
                                                            v47 = 3594862665105984973L ^ 7504810391692904800L;
                                                            continue block101;
                                                        }
                                                        case 800808699: {
                                                            v47 = -3568187347934109605L - -7818473448645009143L;
                                                            continue block101;
                                                        }
                                                    }
                                                    break;
                                                }
                                                v48 = v45.toUpperCase();
                                                v49 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                                if (true) ** GOTO lbl429
                                                block102: while (true) {
                                                    v49 = v50 / (1941675805245445567L - 4382648727388756882L);
lbl429:
                                                    // 2 sources

                                                    switch ((int)v49) {
                                                        case -1369321941: {
                                                            v50 = -3476918158866484334L - -2614019302237256171L;
                                                            continue block102;
                                                        }
                                                        case -1063637175: {
                                                            break block102;
                                                        }
                                                        case 1865325204: {
                                                            v50 = 7086129426637698933L - -1549724846419395534L;
                                                            continue block102;
                                                        }
                                                    }
                                                    break;
                                                }
                                                e4wV.CW\u0142D.add(v48);
                                                v51 = 2368486610354815760L == 2368486610354815761L ? -1064386548 : -2037467741 - 613744991;
                                                while (true) {
                                                    if ((v52 = (cfr_temp_10 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-8612496758630318016L ^ 5914516690870239231L)) == 0L ? 0 : (cfr_temp_10 < 0L ? -1 : 1)) == false) continue;
                                                    if (v52 == (-2042984499 ^ -2042984500)) {
                                                        if (-6055907655912357675L == -6055907655912357674L) {
                                                            break;
                                                        }
                                                        break block156;
                                                    }
                                                    if (-4510340962332990701L == -4510340962332990700L) {
                                                        v52 = 1878547532;
                                                        continue;
                                                    }
                                                    v52 = 950201376 >>> "\u0000\u0000".length();
                                                }
                                                v53 = 795505129;
                                                break block167;
                                            }
                                            v53 = -162600037 ^ 416396102;
                                        }
                                        v54 = (Function<String, List>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, HNQeByj5LZOP-z7M(java.lang.String ), (Ljava/lang/String;)Ljava/util/List;)();
                                        while (true) {
                                            block168: {
                                                if ((v55 = (cfr_temp_11 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (5875803274338581925L - -7253414949874562286L)) == 0L ? 0 : (cfr_temp_11 < 0L ? -1 : 1)) == false) continue;
                                                if (v55 != (-1424320056 ^ -1424320055)) break block168;
                                                e4wV.EOfG.computeIfAbsent((String)CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e82c60b550, v54).add(CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e88a47ea2e);
                                                -790243126 - -974430848;
                                                v56 = (Function<String, List>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, K7kB9-ZPcj7mLtVG(java.lang.String ), (Ljava/lang/String;)Ljava/util/List;)();
                                                592017931 ^ -754418128;
                                                v57 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                                if (true) ** GOTO lbl480
                                            }
                                            v55 = -160714997 ^ 2085902363;
                                        }
                                        block105: while (true) {
                                            v57 = v58 / (5893703262091588813L ^ -1470523292484797351L);
lbl480:
                                            // 2 sources

                                            switch ((int)v57) {
                                                case -1063637175: {
                                                    break block105;
                                                }
                                                case 1469579803: {
                                                    if (-8237139462826066633L == -8237139462826066632L) {
                                                        v58 = 784901946095647655L;
                                                        continue block105;
                                                    }
                                                    v58 = -7944460993409308640L >>> "\u0000\u0000".length();
                                                    continue block105;
                                                }
                                                case 1666756449: {
                                                    if (9192332062288223174L == 9192332062288223175L) {
                                                        v58 = 2703772474920635074L;
                                                        continue block105;
                                                    }
                                                    v58 = 4363525616113175232L >>> "\u0000\u0000".length();
                                                    continue block105;
                                                }
                                                case 1686745545: {
                                                    v58 = 2806635789270495685L - -8292966346066723026L;
                                                    continue block105;
                                                }
                                            }
                                            break;
                                        }
                                        while (true) {
                                            if ((v59 = (cfr_temp_12 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-2047004914653796477L ^ -5223308608178945105L)) == 0L ? 0 : (cfr_temp_12 < 0L ? -1 : 1)) == false) continue;
                                            if (v59 == 1175945511 - 1175945512) break;
                                            v59 = 891023548 - 718093936;
                                        }
                                        e4wV.wO\u0119g.computeIfAbsent(CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e88a47ea2e, v56).add(CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e82c60b550);
                                        while (true) {
                                            if ((v60 = (cfr_temp_13 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-1738951391070316158L ^ -3689091587569900902L)) == 0L ? 0 : (cfr_temp_13 < 0L ? -1 : 1)) == false) continue;
                                            if (v60 == (-9088274362090265431L == -9088274362090265430L ? 412261712 : 2136765072 - 2136765073)) break;
                                            v60 = 452983361 ^ 1258868133;
                                        }
                                        v61 = Pattern.quote((String)CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e82c60b550);
                                        while (true) {
                                            if ((v62 = (cfr_temp_14 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (1580527799392087948L ^ 4954465470912948281L)) == 0L ? 0 : (cfr_temp_14 < 0L ? -1 : 1)) == false) continue;
                                            if (v62 == (-1725713277 ^ 1725713276)) break;
                                            if (-735747458270926401L == -735747458270926400L) {
                                                v62 = 1983288301;
                                                continue;
                                            }
                                            v62 = 903913889 ^ 1589958607;
                                        }
                                        v63 = CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e891e712ba.append(v61);
                                        var8 = new byte[-59675995 ^ -59675996];
                                        var8[-1247719806 ^ -1247719806] = 317454618 ^ 317454694;
                                        v64 = new String(var8, "UTF-8");
                                        while (true) {
                                            if ((v65 = (cfr_temp_15 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-1063079373085206656L ^ 3579577816919109556L)) == 0L ? 0 : (cfr_temp_15 < 0L ? -1 : 1)) == false) continue;
                                            if (v65 == (-451748883 ^ -451748884)) {
                                                v63.append(v64);
                                                continue block91;
                                            }
                                            v65 = 1077682899 ^ -1889967901;
                                        }
                                        break;
                                    }
                                    462255262 - -1078609864;
                                    while (true) {
                                        if ((v66 = (cfr_temp_16 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-502764073973477048L >>> "\u0000\u0000".length())) == 0L ? 0 : (cfr_temp_16 < 0L ? -1 : 1)) == false) continue;
                                        if (v66 == (628822772 ^ -628822773)) {
                                            v67 = CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e891e712ba.length();
                                            if (-756156841423147561L == -756156841423147560L) {
                                                break;
                                            }
                                            break block157;
                                        }
                                        v66 = 1434389527 ^ -1103446655;
                                    }
                                    v68 = 883754766;
                                    break block169;
                                }
                                v68 = 2032175894 ^ -143806410;
                            }
                            CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e891e712ba.setLength(v67 - (127830727338426625L == 127830727338426626L ? 297340853 : 25136430 ^ 25136431));
                            var8 = new byte[-1382114588 ^ -1382114585];
                            var8[1095824021 ^ 1095824023] = 2001444116 ^ 2001444214;
                            var8[-85457906 ^ -85457906] = -2173405744282668733L == -2173405744282668732L ? 841763333 : 510844474 ^ 510844435;
                            var8[7248923119080717985L == 7248923119080717986L ? -1485611690 : 1873839005 ^ 1873839004] = 1387155865 ^ 1387155909;
                            v69 = new String(var8, "UTF-8");
                            v70 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                            if (true) ** GOTO lbl566
                            block111: while (true) {
                                v70 = v71 / (2777442661077306882L ^ -949064768595818406L);
lbl566:
                                // 2 sources

                                switch ((int)v70) {
                                    case -1931160998: {
                                        v71 = 7116642995188855426L ^ 365851686012131108L;
                                        continue block111;
                                    }
                                    case -1313483211: {
                                        v71 = 1778064478326008434L ^ -5918538681114650866L;
                                        continue block111;
                                    }
                                    case -1063637175: {
                                        break block111;
                                    }
                                    case -227676754: {
                                        v71 = -4893388925224173411L ^ -857025275887274580L;
                                        continue block111;
                                    }
                                }
                                break;
                            }
                            CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e891e712ba.append(v69);
                            e4wV.qL\u017a2 = Pattern.compile(CRACKME_fd0a7548_af3a_4f9a_bc57_e07932a5f5e891e712ba.toString());
                        }
                        while (true) {
                            if ((v72 = (cfr_temp_17 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (885140563290192454L ^ 2027408619976108915L)) == 0L ? 0 : (cfr_temp_17 < 0L ? -1 : 1)) == false) continue;
                            if (v72 == 1454136942 - 1454136943) {
                                1506081283 ^ -378202880;
                                v73 = new String[-1199854582 ^ -1199854580];
                                var8 = new byte[-1238966450 ^ -1238966451];
                                if (8505229314322725688L == 8505229314322725689L) {
                                    break;
                                }
                                break block158;
                            }
                            if (8906783264599573182L == 8906783264599573183L) {
                                v72 = 1859931205;
                                continue;
                            }
                            v72 = 179580248 ^ -11368521;
                        }
                        v74 = -1600760949;
                        break block170;
                    }
                    v74 = -156552782 ^ -156552781;
                }
                var8[v74] = -1967578709 ^ -1967578649;
                var8[31593967 ^ 31593967] = -254031133 ^ -254031198;
                var8[-1420158874 ^ -1420158876] = -1874665151 ^ -1874665203;
                v73[-740243392 ^ -740243392] = new String(var8, "UTF-8");
                var8 = new byte[1324336558 ^ 1324336552];
                var8[-1708230191 ^ -1708230187] = -609435146 ^ -609435207;
                var8[-5330809142564516517L == -5330809142564516516L ? -725368504 : 908399229 ^ 908399229] = 923060850 ^ 923060788;
                var8[1088921891 ^ 1088921889] = -2057698743 ^ -2057698811;
                var8[-312183141 ^ -312183144] = -6503849460931295694L == -6503849460931295693L ? -1950158216 : -688622236 ^ -688622296;
                var8[-420504461 ^ -420504458] = -3988806708170506539L == -3988806708170506538L ? 1500488558 : -1493828538 ^ -1493828591;
                var8[633519974401385334L == 633519974401385335L ? -88525473 : -2131154198 ^ -2131154197] = 5740921784874037117L == 5740921784874037118L ? 510024925 : 1375458513 ^ 1375458462;
                v73[-164809916 ^ -164809915] = new String(var8, "UTF-8");
                var8 = new byte[990328254 ^ 990328250];
                var8[-1711911286 ^ -1711911285] = -1113894963 ^ -1113895036;
                var8[8894510291110453363L == 8894510291110453364L ? -1154531447 : 250248934 ^ 250248933] = 167163464 ^ 167163405;
                var8[221375327030346240L == 221375327030346241L ? -934269042 : -1841732615 ^ -1841732613] = 1667700718 ^ 1667700645;
                var8[-1076036778 ^ -1076036778] = -335273699 ^ -335273647;
                v73[6984652802807018963L == 6984652802807018964L ? -515386692 : 2072068025 ^ 2072068027] = new String(var8, "UTF-8");
                var8 = new byte[533095896262036992L == 533095896262036993L ? 393157102 : 537143509 ^ 537143517];
                var8[4595605223183817858L == 4595605223183817859L ? 162475632 : -1506978216 ^ -1506978216] = -7873750775050781922L == -7873750775050781921L ? -1451023512 : 530934358 ^ 530934290;
                var8[288526623 ^ 288526618] = -6753579541027881033L == -6753579541027881032L ? 477649511 : 1281703704 ^ 1281703766;
                var8[-232613751 ^ -232613746] = -7099350263125590623L == -7099350263125590622L ? 1317200277 : -676264219 ^ -676264266;
                var8[290459938 ^ 290459939] = 125449665 ^ 125449608;
                var8[428990387 ^ 428990389] = 1052775390 ^ 1052775322;
                var8[367737739 ^ 367737743] = -845332647 ^ -845332714;
                var8[-63185548 ^ -63185546] = -525095161 ^ -525095098;
                var8[13209838 ^ 13209837] = -1924308048 ^ -1924307971;
                v73[-544277586 ^ -544277587] = new String(var8, "UTF-8");
                1218729644 ^ -1934556410;
                var8 = new byte[-1770236141 ^ -1770236139];
                var8[-839884837 ^ -839884840] = 5481662 ^ 5481710;
                var8[1655441713 ^ 1655441712] = 1605750524 ^ 1605750453;
                var8[1230594094 ^ 1230594094] = -111197104 ^ -111197180;
                var8[-954583616303452980L == -954583616303452979L ? -864123664 : -1773030179 ^ -1773030184] = -184100040 ^ -184099999;
                var8[-4046150219295872079L == -4046150219295872078L ? -1906709913 : 1439665110 ^ 1439665106] = -2146801525 ^ -2146801465;
                var8[-2008588964 ^ -2008588962] = -2129444250 ^ -2129444298;
                v73[1087386257 ^ 1087386261] = new String(var8, "UTF-8");
                var8 = new byte[1500617264 ^ 1500617269];
                var8[1522462569 ^ 1522462573] = -1165334342 ^ -1165334273;
                var8[3344686287185167792L == 3344686287185167793L ? 1637356395 : 1605002512 ^ 1605002513] = -473953983281449098L == -473953983281449097L ? 2146791576 : 1600858851 ^ 1600858795;
                var8[-2294501839624916221L == -2294501839624916220L ? -1365407009 : -802422723 ^ -802422721] = 864944857 ^ 864944792;
                var8[1036752077 ^ 1036752077] = -1811742963 ^ -1811742882;
                var8[-2107306818 ^ -2107306819] = -1445777156 ^ -1445777234;
                v73[795772828 ^ 795772825] = new String(var8, "UTF-8");
                while (true) {
                    if ((v75 = (cfr_temp_18 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-8617238500234606644L - 6358914834539095582L)) == 0L ? 0 : (cfr_temp_18 < 0L ? -1 : 1)) == false) continue;
                    if (v75 == 184597821 - 184597822) {
                        Collections.addAll(e4wV.CW\u0142D, v73);
                        if (-4067007269422933987L == -4067007269422933986L) {
                            break;
                        }
                        break block159;
                    }
                    v75 = -1693501452 >>> "\u0000\u0000".length();
                }
                v76 = -1611471005;
                break block171;
            }
            v76 = -455255990 ^ -1926439010;
        }
    }

    /*
     * Unable to fully structure code
     */
    public static String Uy7sUspOzIGLHfBi(String var0) {
        if (e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c == 7201958907999730956L >>> "\u0000\u0000".length()) {
            if ((1575736862 ^ -617323921 ^ (501955880 ^ 1645527767)) != 0) {
                1542849152 ^ 1542849153;
            }
        } else {
            1085738238 ^ 1085738238;
        }
        var9_1 = new byte[-382211007 ^ -382211008];
        var9_1[-628196726 ^ -628196726] = -290965273 ^ -290965302;
        v0 = new String(var9_1, "UTF-8");
        var9_1 = new byte[388811155 ^ 388811154];
        var9_1[-551342948 ^ -551342948] = 6252574776725765237L == 6252574776725765238L ? 1532648111 : 10199750 ^ 10199705;
        v1 = new String(var9_1, "UTF-8");
        while (true) {
            if ((v2 = (cfr_temp_0 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-5195766104282452596L == -5195766104282452595L ? -3110772518716578287L : -7615581398868275641L - -2620778642458120689L)) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) continue;
            if (v2 == (-2062299325 ^ -2062299326)) break;
            v2 = -335246660 ^ -267908576;
        }
        CRACKME_bb63377d_c0c4_41b3_ac82_15c33e6048b7b9627fad = CRACKME_bb63377d_c0c4_41b3_ac82_15c33e6048b7b9627fad.replace(v0, v1);
        -783443088 ^ 1744994061;
        -159808208 - 221057750;
        v3 = e4wV.EOfG.entrySet();
        while (true) {
            if ((v4 = (cfr_temp_1 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-2530798328900608054L == -2530798328900608053L ? 5057086303257266854L : 8677835405120009647L ^ 7126158465268937752L)) == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1)) == false) continue;
            if (v4 == (-1293627508 ^ 1293627507)) break;
            v4 = 1748864448 >>> "\u0000\u0000".length();
        }
        var1_2 = v3.iterator();
        block29: while (true) {
            block66: {
                block64: {
                    block65: {
                        block63: {
                            if ((v5 = (cfr_temp_2 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-7596794632431445179L == -7596794632431445178L ? 3068247101012888385L : -5338609394265788697L ^ 3756305382982662162L)) == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1)) == false) continue;
                            if (v5 == (462547687 ^ 462547686)) {
                                if (!var1_2.hasNext()) {
                                    -1299989651 - 1844144189;
                                    return CRACKME_bb63377d_c0c4_41b3_ac82_15c33e6048b7b9627fad;
                                }
                            } else {
                                v5 = 1386889015 - -997810943;
                                continue;
                            }
                            720105667 - -1573884700;
                            v6 = -1167530146295685780L == -1167530146295685779L ? -1322673532 : -820036347 ^ 2125764321;
                            var2_3 = var1_2.next();
                            while (true) {
                                if ((v7 = (cfr_temp_3 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-8167884810480540106L ^ -3678074859122646872L)) == 0L ? 0 : (cfr_temp_3 < 0L ? -1 : 1)) == false) continue;
                                if (v7 == (466204471 ^ -466204472)) break;
                                v7 = -100691082 ^ 762430510;
                            }
                            CRACKME_bb63377d_c0c4_41b3_ac82_15c33e6048b7567e91bd = (String)CRACKME_bb63377d_c0c4_41b3_ac82_15c33e6048b7b3d70d05.getKey();
                            while (true) {
                                if ((v8 = (cfr_temp_4 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-1233549887183131864L ^ 4682809120144304779L)) == 0L ? 0 : (cfr_temp_4 < 0L ? -1 : 1)) == false) continue;
                                if (v8 == (377854989 ^ 377854988)) {
                                    v9 = (List)CRACKME_bb63377d_c0c4_41b3_ac82_15c33e6048b7b3d70d05.getValue();
                                    if (6152171737377601183L == 6152171737377601184L) {
                                        break;
                                    }
                                    break block63;
                                }
                                if (-8495242809803175366L == -8495242809803175365L) {
                                    v8 = -127911337;
                                    continue;
                                }
                                v8 = 968971289 ^ 1625025233;
                            }
                            v10 = -522344399;
                            break block65;
                        }
                        v10 = -728724590 ^ -728724590;
                    }
                    675960029 ^ 162209103;
                    while (true) {
                        if ((v11 = (cfr_temp_5 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-1910563678162297519L ^ 7028435166450419823L)) == 0L ? 0 : (cfr_temp_5 < 0L ? -1 : 1)) == false) continue;
                        if (v11 == (-1512689512 ^ -1512689511)) {
                            CRACKME_bb63377d_c0c4_41b3_ac82_15c33e6048b7dcaf6f11 = (String)v9.get(v10);
                            if (-7865152923468819233L == -7865152923468819232L) {
                                break;
                            }
                            break block64;
                        }
                        if (-2765996464297436180L == -2765996464297436179L) {
                            v11 = 619100392;
                            continue;
                        }
                        v11 = 143842118 ^ 1650443583;
                    }
                    v12 = 1396601367;
                    break block66;
                }
                v12 = 555996462 ^ -638435493;
            }
            v13 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
            if (true) ** GOTO lbl122
            block33: while (true) {
                v13 = v14 / (8371657505391315747L - -7828714637613282424L);
lbl122:
                // 2 sources

                switch ((int)v13) {
                    case -1973110563: {
                        if (1395582879709461691L == 1395582879709461692L) {
                            v14 = -2146495512513256048L;
                            continue block33;
                        }
                        v14 = -5991455916808566600L >>> "\u0000\u0000".length();
                        continue block33;
                    }
                    case -1063637175: {
                        break block33;
                    }
                    case -917556144: {
                        if (-7898515331211715384L == -7898515331211715383L) {
                            v14 = -7347137982677826473L;
                            continue block33;
                        }
                        v14 = 2104558674196450773L ^ 2560186477182835053L;
                        continue block33;
                    }
                    case 1508392979: {
                        v14 = 1369726790680929442L - 73909997416133830L;
                        continue block33;
                    }
                }
                break;
            }
            v15 = 4862319044528141020L == 4862319044528141021L ? 1041688455 : -279231589 - 2085029691;
            v16 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
            block34: while (true) {
                switch ((int)v16) {
                    case -1580292140: {
                        v16 = (-3034212543835260543L ^ 1181734963445831775L) / (1123324555836343120L >>> "\u0000\u0000".length());
                        continue block34;
                    }
                    case -1063637175: {
                        break block34;
                    }
                }
                break;
            }
            var9_1 = new byte[-197695974 ^ -197695971];
            var9_1[3060845942941691685L == 3060845942941691686L ? 1342493724 : 316946463 ^ 316946461] = -8703449135121787518L == -8703449135121787517L ? -1431525755 : 1916909762 ^ 1916909822;
            var9_1[2274237 ^ 2274232] = 394590065 ^ 394589958;
            var9_1[289505143 ^ 289505140] = -7440833705768642685L == -7440833705768642684L ? 22176615 : 304444379 ^ 304444410;
            var9_1[-1112344539351068056L == -1112344539351068055L ? 616033829 : 1538206836 ^ 1538206837] = 311538196051294409L == 311538196051294410L ? 1476606461 : 200811075 ^ 200811132;
            var9_1[1485100247 ^ 1485100247] = 905391393 ^ 905391369;
            var9_1[1440830440 ^ 1440830444] = -1672469940 ^ -1672470000;
            var9_1[1506253403 ^ 1506253405] = -5915680475457251369L == -5915680475457251368L ? 1241641473 : 143566153 ^ 143566176;
            v17 = new StringBuilder().append(new String(var9_1, "UTF-8"));
            while (true) {
                block67: {
                    if ((v18 = (cfr_temp_6 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-4748074961829742349L == -4748074961829742348L ? 1749766871635616175L : 1621270006547541929L ^ -6587990158613159248L)) == 0L ? 0 : (cfr_temp_6 < 0L ? -1 : 1)) == false) continue;
                    if (v18 != (-565670163 ^ -565670164)) break block67;
                    v19 = Pattern.quote(CRACKME_bb63377d_c0c4_41b3_ac82_15c33e6048b7567e91bd);
                    v20 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                    if (true) ** GOTO lbl180
                }
                v18 = 1831376587 - -1310354331;
            }
            block36: while (true) {
                v20 = v21 / (-8846387653272965366L - -8658228723564163175L);
lbl180:
                // 2 sources

                switch ((int)v20) {
                    case -1063637175: {
                        break block36;
                    }
                    case 956834488: {
                        if (-3530499276770447092L == -3530499276770447091L) {
                            v21 = 2441265482344912046L;
                            continue block36;
                        }
                        v21 = 1996017455483239944L ^ 4916949477536768107L;
                        continue block36;
                    }
                    case 1703211374: {
                        v21 = -646882637418914096L ^ 8318756991615605999L;
                        continue block36;
                    }
                    case 1851663869: {
                        v21 = -53974515747217655L - -6237814611622036001L;
                        continue block36;
                    }
                }
                break;
            }
            v22 = v17.append(v19);
            var9_1 = new byte[-1917999744 ^ -1917999738];
            var9_1[-917824476041804414L == -917824476041804413L ? -159647756 : 861020324 ^ 861020326] = -3052429341669644619L == -3052429341669644618L ? -1266496041 : 1719211564 ^ 1719211533;
            var9_1[1825726099 ^ 1825726102] = 639525725 ^ 639525748;
            var9_1[-741888368 ^ -741888365] = 7351716336597731165L == 7351716336597731166L ? 739502115 : 1231779308 ^ 1231779248;
            var9_1[-2418851528492638177L == -2418851528492638176L ? 1320088033 : 2142590333 ^ 2142590329] = -1880213064 ^ -1880213041;
            var9_1[-332586017 ^ -332586017] = -625518884 ^ -625518860;
            var9_1[1415374623 ^ 1415374622] = 3802824999424356282L == 3802824999424356283L ? -1884608698 : -2042587496 ^ -2042587481;
            v23 = new String(var9_1, "UTF-8");
            while (true) {
                if ((v24 = (cfr_temp_7 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (3364149970570838628L >>> "\u0000\u0000".length())) == 0L ? 0 : (cfr_temp_7 < 0L ? -1 : 1)) == false) continue;
                if (v24 == (1447951511 ^ -1447951512)) break;
                v24 = 1737157564 ^ 478572023;
            }
            v25 = v22.append(v23);
            while (true) {
                block68: {
                    if ((v26 = (cfr_temp_8 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-4871858301878377189L ^ 1002147497245137836L)) == 0L ? 0 : (cfr_temp_8 < 0L ? -1 : 1)) == false) continue;
                    if (v26 != (2757792436069296838L == 2757792436069296839L ? 1195009923 : -798054391 ^ -798054392)) break block68;
                    var5_6 = v25.toString();
                    v27 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                    if (true) ** GOTO lbl231
                }
                if (-7906297624617134182L == -7906297624617134181L) {
                    v26 = 1819397611;
                    continue;
                }
                v26 = 1971008459 - -1106696122;
            }
            block39: while (true) {
                v27 = v28 / (-4348717029057993696L == -4348717029057993695L ? 9159268228924197520L : 4930623418376082318L - -8438438172827732015L);
lbl231:
                // 2 sources

                switch ((int)v27) {
                    case -1063637175: {
                        break block39;
                    }
                    case -1047932625: {
                        v28 = -6942795076078553652L ^ 8509271043416637395L;
                        continue block39;
                    }
                    case -900156682: {
                        v28 = -7826666976533833052L ^ 5979972795193784726L;
                        continue block39;
                    }
                    case 1159035929: {
                        v28 = -2277296915737267608L ^ -310758725493600433L;
                        continue block39;
                    }
                }
                break;
            }
            var6_7 = Pattern.compile((String)CRACKME_bb63377d_c0c4_41b3_ac82_15c33e6048b729ea7136);
            -738886858 - 947699567;
            v29 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
            if (true) ** GOTO lbl252
            block40: while (true) {
                v29 = v30 / (8599055834377056500L >>> "\u0000\u0000".length());
lbl252:
                // 2 sources

                switch ((int)v29) {
                    case -1944798092: {
                        v30 = -8805000066477738279L - 1767789626027228890L;
                        continue block40;
                    }
                    case -1063637175: {
                        break block40;
                    }
                    case 315232090: {
                        v30 = -7934696294042232540L ^ -8781701575192763285L;
                        continue block40;
                    }
                }
                break;
            }
            var7_8 = CRACKME_bb63377d_c0c4_41b3_ac82_15c33e6048b74864559e.matcher(CRACKME_bb63377d_c0c4_41b3_ac82_15c33e6048b7b9627fad);
            v31 = -2268937609039075631L == -2268937609039075630L ? 1531181810 : 833361180 - 1046156691;
            while (true) {
                if ((v32 = (cfr_temp_9 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-1692241340945098924L ^ 7988413218378101495L)) == 0L ? 0 : (cfr_temp_9 < 0L ? -1 : 1)) == false) continue;
                if (v32 == (3804903263491530756L == 3804903263491530757L ? -1473431551 : -1453937543 ^ -1453937544)) {
                    if (!CRACKME_bb63377d_c0c4_41b3_ac82_15c33e6048b73eac9d17.find()) ** break;
                    break block29;
                }
                v32 = 1989117870 - -1086495540;
            }
            break;
        }
        while (true) {
            if ((v33 = (cfr_temp_10 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (977944441618050461L ^ -6497170763499349707L)) == 0L ? 0 : (cfr_temp_10 < 0L ? -1 : 1)) == false) continue;
            if (v33 == (-4420205023738288630L == -4420205023738288629L ? 1516688363 : -1079971361 ^ -1079971362)) break;
            v33 = -1397002332 ^ 495412406;
        }
        v34 = Matcher.quoteReplacement(CRACKME_bb63377d_c0c4_41b3_ac82_15c33e6048b7dcaf6f11);
        while (true) {
            if ((v35 = (cfr_temp_11 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (8323370838152485558L - -8813024660704644796L)) == 0L ? 0 : (cfr_temp_11 < 0L ? -1 : 1)) == false) continue;
            if (v35 == (819185989 ^ -819185990)) {
                return CRACKME_bb63377d_c0c4_41b3_ac82_15c33e6048b73eac9d17.replaceFirst(v34);
            }
            if (5409577604696004415L == 5409577604696004416L) {
                v35 = 1614459103;
                continue;
            }
            v35 = 749609748 ^ -1479371577;
        }
    }

    /*
     * Unable to fully structure code
     */
    public static String i26U-7u6v6ccWsho(String var0) {
        block158: {
            block154: {
                if (e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c == (7751720381112567328L ^ -357917901541245514L)) {
                    if ((-553903600 ^ -1543503462 ^ 589317714 - -1558165933) != 0) {
                        -563887965 ^ -563887966;
                    }
                } else {
                    v0 = 4824439723403712620L == 4824439723403712621L ? -858098140 : -1274426743 ^ -1274426743;
                }
                while (true) {
                    block157: {
                        if ((v1 = (cfr_temp_0 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-7985833371726641352L ^ -4212945163844277360L)) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) continue;
                        if (v1 != (1839948068156397270L == 1839948068156397271L ? -1012757912 : -1518346398 - -1518346397)) break block157;
                        v2 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                        if (true) ** GOTO lbl18
                    }
                    v1 = 23045527 ^ -365915764;
                }
                block95: while (true) {
                    v2 = v3 / (-5189543935752950509L == -5189543935752950508L ? -3658160677508963884L : 1573108741593811898L ^ -6889173226029748201L);
lbl18:
                    // 2 sources

                    switch ((int)v2) {
                        case -1063637175: {
                            break block95;
                        }
                        case 997901315: {
                            if (3976224554070506326L == 3976224554070506327L) {
                                v3 = -7848044532596513551L;
                                continue block95;
                            }
                            v3 = -6843318707331539188L ^ 5886936523827276419L;
                            continue block95;
                        }
                        case 1040739272: {
                            v3 = -7604343966517210442L ^ -1168372020485050727L;
                            continue block95;
                        }
                        case 1450358258: {
                            if (1169047364228643955L == 1169047364228643956L) {
                                v3 = -5349815063412164184L;
                                continue block95;
                            }
                            v3 = 165632113416440772L >>> "\u0000\u0000".length();
                            continue block95;
                        }
                    }
                    break;
                }
                CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bfd3eb29fc = zNb\u015b.Y\u017btq.getConfig();
                while (true) {
                    if ((v4 = (cfr_temp_1 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (5507974455304057600L == 5507974455304057601L ? 6107221185266040796L : 8646458343805128787L ^ -8789513739485039946L)) == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1)) == false) continue;
                    if (v4 == (-1646850975553886194L == -1646850975553886193L ? 630234553 : 972286867 ^ -972286868)) {
                        var2_2 = new HashMap<K, V>();
                        -671066996 ^ 1008975117;
                        1431697026 ^ 796376673;
                        var14_3 = new byte[379111212 ^ 379111205];
                        if (-210855576995541251L == -210855576995541250L) {
                            break;
                        }
                        break block154;
                    }
                    if (-1785180006150400482L == -1785180006150400481L) {
                        v4 = -958278038;
                        continue;
                    }
                    v4 = 1465149315 ^ 1455421667;
                }
                v5 = -1947143935;
                break block158;
            }
            v5 = -1809397304 ^ -1809397331;
        }
        var14_3[1229094893 ^ 1229094890] = v5;
        var14_3[-2048630940216562142L == -2048630940216562141L ? 1823936501 : -1731220653 ^ -1731220654] = -1327538717 ^ -1327538806;
        var14_3[-8295765 ^ -8295762] = 777586017924515466L == 777586017924515467L ? 795963051 : 715824479 ^ 715824446;
        var14_3[207713733 ^ 207713735] = 1635612399 ^ 1635612297;
        var14_3[772460562 ^ 772460562] = -7132555411856092339L == -7132555411856092338L ? -1926758951 : 282327973 ^ 282328002;
        var14_3[655936590 ^ 655936582] = -857517948 ^ -857517833;
        var14_3[4632005370365558558L == 4632005370365558559L ? 1979405250 : 1431982493 ^ 1431982494] = 4039023870236952114L == 4039023870236952115L ? -14718690 : 686950611 ^ 686950567;
        var14_3[-149408240 ^ -149408234] = -1827009042 ^ -1827009149;
        var14_3[-1537091356 ^ -1537091360] = -275507902 ^ -275507956;
        v6 = new String(var14_3, "UTF-8");
        while (true) {
            if ((v7 = (cfr_temp_2 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-4678546684285188140L ^ 2054618900525909229L)) == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1)) == false) continue;
            if (v7 == (5426550918260136556L == 5426550918260136557L ? -524694390 : -744046746 ^ -744046745)) break;
            v7 = -417789883 ^ 2086805685;
        }
        CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bfd4ea2cd0 = CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bfd3eb29fc.getStringList(v6);
        while (true) {
            if ((v8 = (cfr_temp_3 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-4523077043520324385L ^ 7837348551815458471L)) == 0L ? 0 : (cfr_temp_3 < 0L ? -1 : 1)) == false) continue;
            if (v8 == (-1803677724 ^ -1803677723)) break;
            v8 = -1686689792 - -476937734;
        }
        var4_5 = CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bfd4ea2cd0.iterator();
        block99: while (true) {
            block159: {
                703186776 ^ -1112184122;
                if (var4_5.hasNext()) break block159;
                var14_3 = new byte[426917313977030827L == 426917313977030828L ? -2072091314 : -1523505170 ^ -1523505169];
                var14_3[-1048535308 ^ -1048535308] = 1659582669 ^ 1659582701;
                CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf698b3e00 = CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf0be09e0d.split(new String(var14_3, "UTF-8"));
                -2091871795 - -1822268552;
                v9 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                if (true) ** GOTO lbl230
            }
            v10 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
            block100: while (true) {
                switch ((int)v10) {
                    case -1063637175: {
                        break block100;
                    }
                    case -271406315: {
                        v10 = (-6073093303400166169L - 2307455340301977306L) / (7426831957409929650L - -4798071126204979300L);
                        continue block100;
                    }
                }
                break;
            }
            var5_6 = (String)var4_5.next();
            var14_3 = new byte[-140497928 ^ -140497926];
            var14_3[-86271944 ^ -86271944] = -105577451 ^ -105577416;
            var14_3[3406825860087224910L == 3406825860087224911L ? -443899311 : -1911204525 ^ -1911204526] = -154015457 ^ -154015455;
            v11 = new String(var14_3, "UTF-8");
            while (true) {
                if ((v12 = (cfr_temp_4 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (2134597544962956026L == 2134597544962956027L ? -8869842751081549396L : -8517166074542545628L - -6846626036951279009L)) == 0L ? 0 : (cfr_temp_4 < 0L ? -1 : 1)) == false) continue;
                if (v12 == (-5418519731606210831L == -5418519731606210830L ? 1815036398 : 432701656 - 432701657)) {
                    var6_7 = CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf4f4e7f92.split(v11);
                    if (((void)CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bfd9ccb2a6).length != (1949222584 ^ 1949222586)) continue block99;
                    break;
                }
                v12 = -911503804 - -309813388;
            }
            var7_8 = CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bfd9ccb2a6[383087153 ^ 383087153].trim();
            v13 = CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bfd9ccb2a6[-3101452 ^ -3101451].trim();
            339851880 ^ -688938635;
            var14_3 = new byte[-371163075 ^ -371163076];
            var14_3[2040339357 ^ 2040339357] = -1012329054 ^ -1012329088;
            v14 = new String(var14_3, "UTF-8");
            var14_3 = new byte[242936583 ^ 242936583];
            v15 = new String(var14_3, "UTF-8");
            v16 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
            if (true) ** GOTO lbl171
            block102: while (true) {
                v16 = v17 / (1565806080811238660L == 1565806080811238661L ? 5551586004605988137L : 4979330403204441720L - -4038134653812090079L);
lbl171:
                // 2 sources

                switch ((int)v16) {
                    case -1063637175: {
                        break block102;
                    }
                    case 885264493: {
                        v17 = 5864752617460695600L ^ -7800469049757440355L;
                        continue block102;
                    }
                    case 1356189092: {
                        v17 = -5242246288830791467L ^ 4997038114945213252L;
                        continue block102;
                    }
                }
                break;
            }
            var8_10 = v13.replaceAll(v14, v15);
            1169127991 ^ 983949395;
            166479444 - 827560107;
            v18 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
            if (true) ** GOTO lbl201
            block103: while (true) {
                v18 = v19 / (9095317014998378161L - 5510961614411676435L);
lbl201:
                // 2 sources

                switch ((int)v18) {
                    case -1063637175: {
                        break block103;
                    }
                    case -1050170979: {
                        v19 = 9093993931948537495L ^ 2928645863965533788L;
                        continue block103;
                    }
                    case -430043758: {
                        v19 = 1304517092649864496L - 4903520539504566165L;
                        continue block103;
                    }
                    case 710052271: {
                        if (-9051481748176469700L == -9051481748176469699L) {
                            v19 = 6920502111794879105L;
                            continue block103;
                        }
                        v19 = 8744578232723645909L ^ 1627331084801447429L;
                        continue block103;
                    }
                }
                break;
            }
            v20 = CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf148e23fa.toUpperCase();
            while (true) {
                if ((v21 = (cfr_temp_5 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (5557007084478578441L - -9181690806534143405L)) == 0L ? 0 : (cfr_temp_5 < 0L ? -1 : 1)) == false) continue;
                if (v21 == (-1985725296 ^ -1985725295)) {
                    CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf568d540d.put(v20, CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bffa824385);
                    continue block99;
                }
                v21 = -874298572 ^ -599085947;
            }
            break;
        }
        block105: while (true) {
            v9 = v22 / (-3727012019571407310L ^ 6770711513997102969L);
lbl230:
            // 2 sources

            switch ((int)v9) {
                case -1147197235: {
                    v22 = 2526795981257881076L - -6204179000581197454L;
                    continue block105;
                }
                case -1063637175: {
                    break block105;
                }
                case 1037389720: {
                    if (-8148095260207483865L == -8148095260207483864L) {
                        v22 = 8755141635134083289L;
                        continue block105;
                    }
                    v22 = -6908511860451613283L - -6171796250547007886L;
                    continue block105;
                }
                case 1655554382: {
                    v22 = -511883107539202773L ^ -6227396424305862070L;
                    continue block105;
                }
            }
            break;
        }
        v23 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
        if (true) ** GOTO lbl249
        block106: while (true) {
            v23 = v24 / (-68013384337943040L - 4139459507827121210L);
lbl249:
            // 2 sources

            switch ((int)v23) {
                case -1296961142: {
                    v24 = 5809790658257873021L ^ 5711870602774497510L;
                    continue block106;
                }
                case -1063637175: {
                    break block106;
                }
                case -839179454: {
                    v24 = 296246934168801386L ^ 7279733224070187117L;
                    continue block106;
                }
            }
            break;
        }
        CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf4f4e7f92 = new StringBuilder();
        var6_7 = CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf698b3e00;
        var7_9 = var6_7.length;
        var8_11 = 99892622 ^ 99892622;
        while (true) {
            block160: {
                block161: {
                    block156: {
                        block155: {
                            1212671196 >>> "\u0000\u0000".length();
                            111908198 ^ -1957190629;
                            if (var8_11 >= var7_9) break;
                            CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bfbcbfff22 = var6_7[var8_11];
                            CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf97b9c286 = 555861762 ^ 555861762;
                            while (true) {
                                if ((v25 = (cfr_temp_6 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-2381948469073167343L ^ -7280268901446157209L)) == 0L ? 0 : (cfr_temp_6 < 0L ? -1 : 1)) == false) continue;
                                if (v25 == (-1524759252 ^ 1524759251)) break;
                                v25 = 1226248528 ^ -1329439584;
                            }
                            v26 = CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf568d540d.keySet();
                            1107909275 - -522713591;
                            v27 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                            block109: while (true) {
                                switch ((int)v27) {
                                    case -1063637175: {
                                        break block109;
                                    }
                                    case -315499661: {
                                        v27 = (312807526288890020L ^ 2236152103209317328L) / (7816523048589107141L - -7256957610719414836L);
                                        continue block109;
                                    }
                                }
                                break;
                            }
                            var11_14 = v26.iterator();
                            block110: while (true) {
                                v28 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                if (true) ** GOTO lbl310
                                block111: while (true) {
                                    v28 = v29 / (6768213571937233681L ^ -655056321267416733L);
lbl310:
                                    // 2 sources

                                    switch ((int)v28) {
                                        case -1063637175: {
                                            break block111;
                                        }
                                        case 788076765: {
                                            v29 = 6583300888924804908L ^ -1008494474909889943L;
                                            continue block111;
                                        }
                                        case 976077541: {
                                            v29 = -3892115574577935345L ^ 8293885982263578665L;
                                            continue block111;
                                        }
                                        case 1919457021: {
                                            if (1930474895622057714L == 1930474895622057715L) {
                                                v29 = 80947802268504838L;
                                                continue block111;
                                            }
                                            v29 = -28094842403264653L ^ 3926520379749505365L;
                                            continue block111;
                                        }
                                    }
                                    break;
                                }
                                if (!var11_14.hasNext()) break block155;
                                v30 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                if (true) ** GOTO lbl331
                                block112: while (true) {
                                    v30 = v31 / (5753946175432777270L == 5753946175432777271L ? -6276963547327030749L : -4324147109269157010L ^ -7898487995478544895L);
lbl331:
                                    // 2 sources

                                    switch ((int)v30) {
                                        case -1555186453: {
                                            v31 = 2346562460009382328L ^ -4749167349789920879L;
                                            continue block112;
                                        }
                                        case -1063637175: {
                                            break block112;
                                        }
                                        case -1000524890: {
                                            v31 = 194357123534955034L ^ -1982736337751372227L;
                                            continue block112;
                                        }
                                        case 1950460413: {
                                            v31 = -577835972221435858L - -1405955074982562290L;
                                            continue block112;
                                        }
                                    }
                                    break;
                                }
                                CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf6214fbdd = (String)var11_14.next();
                                v32 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                if (true) ** GOTO lbl350
                                block113: while (true) {
                                    v32 = v33 / (-2880646984622677807L - 3612888077098251437L);
lbl350:
                                    // 2 sources

                                    switch ((int)v32) {
                                        case -1063637175: {
                                            break block113;
                                        }
                                        case 1441625197: {
                                            if (-4343279342474174348L == -4343279342474174347L) {
                                                v33 = -3301613493990018097L;
                                                continue block113;
                                            }
                                            v33 = -1082452969472179756L ^ 8435438539182250937L;
                                            continue block113;
                                        }
                                        case 1661250053: {
                                            v33 = 786947133967174647L - -7008457805019562866L;
                                            continue block113;
                                        }
                                    }
                                    break;
                                }
                                v34 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                if (true) ** GOTO lbl366
                                block114: while (true) {
                                    v34 = v35 / (-6750958759836497429L ^ -760757098456881606L);
lbl366:
                                    // 2 sources

                                    switch ((int)v34) {
                                        case -1063637175: {
                                            break block114;
                                        }
                                        case -349221293: {
                                            if (5831295363659435473L == 5831295363659435474L) {
                                                v35 = 367497510638341005L;
                                                continue block114;
                                            }
                                            v35 = 3638970411640151144L ^ 1250174048003201882L;
                                            continue block114;
                                        }
                                        case 360271924: {
                                            v35 = -4568715340038281553L ^ -7380790350523229909L;
                                            continue block114;
                                        }
                                        case 894348302: {
                                            v35 = -6008708276340763211L - -6755040662982532748L;
                                            continue block114;
                                        }
                                    }
                                    break;
                                }
                                v36 = new StringBuilder();
                                var14_3 = new byte[-3702229815859585039L == -3702229815859585038L ? 2134398235 : 2081353814 ^ 2081353812];
                                var14_3[-1102291136 ^ -1102291136] = -635169827 ^ -635169919;
                                var14_3[4802175983739323152L == 4802175983739323153L ? 85827370 : -47776639 ^ -47776640] = -1685661319 ^ -1685661413;
                                v37 = new String(var14_3, "UTF-8");
                                v38 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                if (true) ** GOTO lbl393
                                block115: while (true) {
                                    v38 = v39 / (5200146749446238211L == 5200146749446238212L ? 1571914672913279087L : 1917966461755027034L ^ -1610704212135647771L);
lbl393:
                                    // 2 sources

                                    switch ((int)v38) {
                                        case -1730338134: {
                                            if (-4809952822706090169L == -4809952822706090168L) {
                                                v39 = -4862867105870470467L;
                                                continue block115;
                                            }
                                            v39 = 6488225885592888683L ^ -3298050438089434476L;
                                            continue block115;
                                        }
                                        case -1063637175: {
                                            break block115;
                                        }
                                    }
                                    break;
                                }
                                var14_3 = new byte[-7280350570682182864L == -7280350570682182863L ? -399838176 : -1272917082 ^ -1272917084];
                                var14_3[8207971375791955134L == 8207971375791955135L ? -1021686170 : -1788691885 ^ -1788691886] = -150766256 ^ -150766286;
                                var14_3[1923492251 ^ 1923492251] = 624782815 ^ 624782723;
                                v40 = v36.append(v37).append(CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf6214fbdd).append(new String(var14_3, "UTF-8"));
                                -366143965 - 1335166311;
                                v41 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                if (true) ** GOTO lbl416
                                block116: while (true) {
                                    v41 = v42 / (-7592709682865661385L == -7592709682865661384L ? 4170211870141757223L : -6906890835087352667L ^ 2465278322103509976L);
lbl416:
                                    // 2 sources

                                    switch ((int)v41) {
                                        case -1574427082: {
                                            if (-1981406342050137238L == -1981406342050137237L) {
                                                v42 = -1662995320726017212L;
                                                continue block116;
                                            }
                                            v42 = 5163007026267739771L ^ -9144799258720915838L;
                                            continue block116;
                                        }
                                        case -1063637175: {
                                            break block116;
                                        }
                                        case 553412439: {
                                            v42 = 4984754330403052896L ^ -7778828627630064910L;
                                            continue block116;
                                        }
                                        case 1729861373: {
                                            if (-323882994462732525L == -323882994462732524L) {
                                                v42 = -2584458198801594062L;
                                                continue block116;
                                            }
                                            v42 = -5830912199979216885L ^ -6062047670718748999L;
                                            continue block116;
                                        }
                                    }
                                    break;
                                }
                                v43 = v40.toString();
                                while (true) {
                                    if ((v44 = (cfr_temp_7 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-3113148946390468210L - -8979070614440882771L)) == 0L ? 0 : (cfr_temp_7 < 0L ? -1 : 1)) == false) continue;
                                    if (v44 == -788400381 - -788400380) {
                                        if (!CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bfbcbfff22.matches(v43)) continue block110;
                                        break block110;
                                    }
                                    v44 = -380398506 - -951307394;
                                }
                                break;
                            }
                            v45 = CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bfd861a860.append((String)CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf568d540d.get(CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf6214fbdd));
                            var14_3 = new byte[386984946 ^ 386984947];
                            var14_3[-6969350673982317421L == -6969350673982317420L ? -1504836784 : -1203630485 ^ -1203630485] = -6908066134948026406L == -6908066134948026405L ? -1711117274 : 301875537 ^ 301875569;
                            v46 = new String(var14_3, "UTF-8");
                            v47 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                            if (true) ** GOTO lbl451
                            block118: while (true) {
                                v47 = v48 / (4591535718040434531L - 5937412809237434809L);
lbl451:
                                // 2 sources

                                switch ((int)v47) {
                                    case -1085099363: {
                                        if (360585140273189698L == 360585140273189699L) {
                                            v48 = -8950408024142842235L;
                                            continue block118;
                                        }
                                        v48 = 3082117810706328629L ^ -7731246049129929062L;
                                        continue block118;
                                    }
                                    case -1063637175: {
                                        break block118;
                                    }
                                    case 854941888: {
                                        v48 = 4404542359740905266L ^ 2081464246434640563L;
                                        continue block118;
                                    }
                                    case 1274693425: {
                                        v48 = 4475518274291776844L ^ 1064073641055900534L;
                                        continue block118;
                                    }
                                }
                                break;
                            }
                            v45.append(v46);
                            CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf97b9c286 = 913207804 ^ 913207805;
                            -753762753 ^ 1956927560;
                        }
                        if (CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bf97b9c286 != 0) break block160;
                        770653060 - 983209406;
                        while (true) {
                            if ((v49 = (cfr_temp_8 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (5460777809884860683L ^ 3020470654022550622L)) == 0L ? 0 : (cfr_temp_8 < 0L ? -1 : 1)) == false) continue;
                            if (v49 == (6602676674668060686L == 6602676674668060687L ? 471929172 : -1336989653 ^ 1336989652)) {
                                v50 = CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bfd861a860.append(CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bfbcbfff22);
                                54675952 - 1666504275;
                                var14_3 = new byte[2056560877 ^ 2056560876];
                                if (-1079981461104291436L == -1079981461104291435L) {
                                    break;
                                }
                                break block156;
                            }
                            if (1523162302935152696L == 1523162302935152697L) {
                                v49 = 89637895;
                                continue;
                            }
                            v49 = 1567024435 - -1549695340;
                        }
                        v51 = -771012635;
                        break block161;
                    }
                    v51 = -151928016 ^ -151928016;
                }
                var14_3[v51] = 8746795302057977957L == 8746795302057977958L ? -1995411293 : -681593037 ^ -681593069;
                v52 = new String(var14_3, "UTF-8");
                v53 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                if (true) ** GOTO lbl511
                block120: while (true) {
                    v53 = v54 / (-8405350858626332454L ^ -8043982826796736334L);
lbl511:
                    // 2 sources

                    switch ((int)v53) {
                        case -1377297356: {
                            if (-8574191159393002353L == -8574191159393002352L) {
                                v54 = -8198144412393922252L;
                                continue block120;
                            }
                            v54 = 8574118419534230028L >>> "\u0000\u0000".length();
                            continue block120;
                        }
                        case -1063637175: {
                            break block120;
                        }
                        case -166257340: {
                            v54 = -1967267672447859855L ^ 8545886125637459081L;
                            continue block120;
                        }
                    }
                    break;
                }
                v50.append(v52);
            }
            1903567167 - 1262793388;
            ++var8_11;
            if (-5548904065341027263L == -5548904065341027262L) {
                v55 = 963159090;
                continue;
            }
            v55 = -1207644649 - -2032361485;
        }
        v56 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
        block121: while (true) {
            switch ((int)v56) {
                case -1106790639: {
                    v56 = (-868124023391290854L - -5624427843195312793L) / (5788530920231261931L ^ -781123881722998198L);
                    continue block121;
                }
                case -1063637175: {
                    break block121;
                }
            }
            break;
        }
        v57 = CRACKME_915564e7_fa9c_414e_8163_2d1fcda9c5bfd861a860.toString();
        v58 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
        if (true) ** GOTO lbl555
        block122: while (true) {
            v58 = v59 / (1449937179113219358L ^ -8962803210248847109L);
lbl555:
            // 2 sources

            switch ((int)v58) {
                case -1063637175: {
                    break block122;
                }
                case 296277619: {
                    if (-5511797814216090282L == -5511797814216090281L) {
                        v59 = -7706832849694287691L;
                        continue block122;
                    }
                    v59 = 1140267953167690022L - -8795046511492838611L;
                    continue block122;
                }
                case 1612333272: {
                    v59 = 3318080750643122247L ^ 6739755137982439362L;
                    continue block122;
                }
            }
            break;
        }
        v60 = v57.trim();
        var14_3 = new byte[1347677153 ^ 1347677152];
        var14_3[1506930459 ^ 1506930459] = 289874399 ^ 289874418;
        v61 = new String(var14_3, "UTF-8");
        var14_3 = new byte[1802357827 ^ 1802357826];
        var14_3[-1003125254 ^ -1003125254] = -1866249971 ^ -1866249939;
        v62 = new String(var14_3, "UTF-8");
        while (true) {
            block162: {
                if ((v63 = (cfr_temp_9 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-8985149059688596533L ^ 8113796072778831304L)) == 0L ? 0 : (cfr_temp_9 < 0L ? -1 : 1)) == false) continue;
                if (v63 != (-6484383882276918203L == -6484383882276918202L ? -911424161 : 14558718 ^ -14558719)) break block162;
                v64 = v60.replace(v61, v62);
                v65 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                if (true) ** GOTO lbl589
            }
            if (-8908179462521058419L == -8908179462521058418L) {
                v63 = 1267129837;
                continue;
            }
            v63 = 1854615936 - 211737244;
        }
        block124: while (true) {
            v65 = v66 / (6797251796649244840L >>> "\u0000\u0000".length());
lbl589:
            // 2 sources

            switch ((int)v65) {
                case -1063637175: {
                    break block124;
                }
                case -497872032: {
                    if (4093154228260255586L == 4093154228260255587L) {
                        v66 = 8286943560471977204L;
                        continue block124;
                    }
                    v66 = -4999270462990197929L - -5264351154248901282L;
                    continue block124;
                }
                case 488670786: {
                    if (6807950185039868365L == 6807950185039868366L) {
                        v66 = 3165839713796258258L;
                        continue block124;
                    }
                    v66 = -8427069165384553641L ^ 6044486381718631197L;
                    continue block124;
                }
            }
            break;
        }
        return 0QZ8.1d9kfLKTTP4TVZYm((String)v64);
    }

    /*
     * Handled impossible loop by adding 'first' condition
     */
    public static String 2al_94WjzQTnIzkU(String string) {
        String CRACKME_a40783d1_8e42_477e_b28a_cccd959eeb53753d2868;
        byte[] byArray;
        block65: {
            block64: {
                String CRACKME_a40783d1_8e42_477e_b28a_cccd959eeb53cc570941;
                long l = 0xBA701A021EE76C3FL ^ 0xDACC24D1EE0076E0L;
                if (CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c == l) {
                    int n = 0xD81A8B7C ^ 0x3F0ADFC5;
                    if ((n ^ -1027258586 - 1120225063) != 0) {
                        int n2 = 0x8CF4E7C1 ^ 0x8CF4E7C0;
                    }
                } else {
                    int cfr_ignored_12 = 0x2C2F47EB ^ 0x2C2F47EB;
                }
                int n = 0xF35EBABF ^ 0x6ADB708D;
                int cfr_ignored_13 = 0x10E9EDDF ^ 0x70DFD030;
                byArray = new byte[0xB05D6990 ^ 0xB05D6991];
                byArray[0xAF641CAF ^ 0xAF641CAF] = 0x6411AB23 ^ 0x6411AB0E;
                String string2 = new String(byArray, "UTF-8");
                byArray = new byte[0xF44CB64B ^ 0xF44CB64A];
                byArray[0x6FF4370C ^ 0x6FF4370C] = 0x3137E52D ^ 0x3137E572;
                String string3 = new String(byArray, "UTF-8");
                long l2 = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                boolean bl = true;
                block37: while (true) {
                    long l3;
                    if (!bl || (bl = false) || !true) {
                        l2 = l3 / (4167670908021526764L >>> "\u0000\u0000".length());
                    }
                    switch ((int)l2) {
                        case -1063637175: {
                            break block37;
                        }
                        case 161817196: {
                            l3 = 0x7BC5467E1DF3AC7EL ^ 0xB301415E2429E4EL;
                            continue block37;
                        }
                    }
                    break;
                }
                String string4 = CRACKME_a40783d1_8e42_477e_b28a_cccd959eeb53cc570941.replace(string2, string3);
                byArray = new byte[0x8EAFBA2F ^ 0x8EAFBA2E];
                int n3 = 0x2F6E9908 ^ 0x2F6E9908;
                byArray[n3] = 0x4E5992F8 ^ 0x4E5992D8;
                String string5 = new String(byArray, "UTF-8");
                byArray = new byte[0x5368A08D ^ 0x5368A08C];
                byArray[0x4D6F424F ^ 0x4D6F424F] = 0xFB36ED5A ^ 0xFB36ED05;
                String string6 = new String(byArray, "UTF-8");
                long l4 = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                boolean bl2 = true;
                block38: while (true) {
                    long l5;
                    if (!bl2 || (bl2 = false) || !true) {
                        l4 = l5 / (0xA438CD99AEE9BFE4L ^ 0x3FB6297CEA1F687FL);
                    }
                    switch ((int)l4) {
                        case -1063637175: {
                            break block38;
                        }
                        case -252496982: {
                            l5 = 3284674103287263066L - -2666377496207192381L;
                            continue block38;
                        }
                        case 606540986: {
                            l5 = 1269135380012219188L - -6166853075188635437L;
                            continue block38;
                        }
                        case 893141434: {
                            l5 = 0xC97C49045404A81DL ^ 0x259D70C6EC9F0B2DL;
                            continue block38;
                        }
                    }
                    break;
                }
                String string7 = string4.replace(string5, string6);
                byArray = new byte[0x40DF4EB9 ^ 0x40DF4EB8];
                int n4 = 0xB2844E35 ^ 0xB2844E35;
                int n5 = 0xC66370F5 ^ 0xC66370D2;
                byArray[n4] = n5;
                String string8 = new String(byArray, "UTF-8");
                byArray = new byte[0xABF1394F ^ 0xABF1394F];
                String string9 = new String(byArray, "UTF-8");
                long l6 = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                block39: while (true) {
                    switch ((int)l6) {
                        case -1251733103: {
                            l6 = (3184126374237855303L - 9155162920271519227L) / (2133053084860833668L >>> "\u0000\u0000".length());
                            continue block39;
                        }
                        case -1063637175: {
                            break block39;
                        }
                    }
                    break;
                }
                String string10 = string7.replace(string8, string9);
                byArray = new byte[0xB6D87AE3 ^ 0xB6D87AE0];
                int n6 = 0xB5FB4A98 ^ 0xB5FB4A98;
                byArray[n6] = 1027614608 - 1027614638;
                byArray[0x205F14B2 ^ 0x205F14B0] = 2102903418 - 2102903521;
                int n7 = 921146234 - 921146362;
                byArray[0x3602238D ^ 0x3602238C] = n7;
                String string11 = new String(byArray, "UTF-8");
                byArray = new byte[0xD2B0AC4F ^ 0xD2B0AC4F];
                String string12 = new String(byArray, "UTF-8");
                long l7 = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                boolean bl3 = true;
                block40: while (true) {
                    long l8;
                    if (!bl3 || (bl3 = false) || !true) {
                        long l9 = -7406870271850756829L - 2161552843253274278L;
                        l7 = l8 / l9;
                    }
                    switch ((int)l7) {
                        case -1063637175: {
                            break block40;
                        }
                        case -778184042: {
                            l8 = 0x68C4B75A878FF563L ^ 0xC2160A6F21EB7559L;
                            continue block40;
                        }
                        case 1343214460: {
                            l8 = 0x88E1736914FD96C3L ^ 0x4E493A67AB909E45L;
                            continue block40;
                        }
                        case 1742338265: {
                            l8 = 0x5E986D3E6CC7E514L ^ 0xC5FD2F3656700CB9L;
                            continue block40;
                        }
                    }
                    break;
                }
                String string13 = string10.replace(string11, string12);
                while (true) {
                    long l10;
                    long l11;
                    if ((l11 = (l10 = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (0xD1D93C3B6BC09460L ^ 0x633015494B776D14L)) == 0L ? 0 : (l10 < 0L ? -1 : 1)) == false) continue;
                    if (l11 == (0xF2CC9A05 ^ 0xF2CC9A04)) break;
                    l11 = 0x3EC00BAC ^ 0x8AF5BED1;
                }
                int n8 = 360900975 - 1347642801;
                CRACKME_a40783d1_8e42_477e_b28a_cccd959eeb53753d2868 = e4wV.CS370SZfE_Wkcb11(string13.toLowerCase());
                byArray = new byte[0x1F99D6F9 ^ 0x1F99D6F8];
                int n9 = 0xE228DF14 ^ 0xE228DF24;
                byArray[0x6A89B83 ^ 0x6A89B83] = n9;
                String string14 = new String(byArray, "UTF-8");
                while (true) {
                    long l12 = 3926336659212332389L - -1644348594586261438L;
                    long l13 = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - l12;
                    long l14 = l13 == 0L ? 0 : (l13 < 0L ? -1 : 1);
                    if (l14 == false) continue;
                    if (l14 == (0x635C1139 ^ 0x9CA3EEC6)) {
                        if (CRACKME_a40783d1_8e42_477e_b28a_cccd959eeb53753d2868.equals(string14)) {
                            break;
                        }
                        break block64;
                    }
                    l14 = 0x821F4FD ^ 0x712A57E5;
                }
                byArray = new byte[0xC4E8ABC8 ^ 0xC4E8ABC8];
                return new String(byArray, "UTF-8");
            }
            if (ilVD != null) {
                long l = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                boolean bl = true;
                block43: while (true) {
                    long l15;
                    if (!bl || (bl = false) || !true) {
                        long l16 = 7832442345787922364L >>> "\u0000\u0000".length();
                        l = l15 / l16;
                    }
                    switch ((int)l) {
                        case -1063637175: {
                            break block43;
                        }
                        case -262969860: {
                            l15 = 59332178208845624L >>> "\u0000\u0000".length();
                            continue block43;
                        }
                    }
                    break;
                }
                while (true) {
                    long l17;
                    long l18;
                    if ((l18 = (l17 = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-9088948054723717353L - 3861264805264338413L)) == 0L ? 0 : (l17 < 0L ? -1 : 1)) == false) continue;
                    if (l18 == (0x8D881208 ^ 0x7277EDF7)) {
                        if (!ilVD.containsKey(CRACKME_a40783d1_8e42_477e_b28a_cccd959eeb53753d2868)) break;
                        break block65;
                    }
                    l18 = -2117616479 - -754462595;
                }
            }
            byArray = new byte[0x9FF05BE0 ^ 0x9FF05BE0];
            return new String(byArray, "UTF-8");
        }
        while (true) {
            long l = -1013101288285184271L - -3891679593759216817L;
            long l19 = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - l;
            long l20 = l19 == 0L ? 0 : (l19 < 0L ? -1 : 1);
            if (l20 == false) continue;
            if (l20 == (0x958C6180 ^ 0x6A739E7F)) break;
            l20 = -1394724965 - 333236551;
        }
        long l = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
        block46: while (true) {
            switch ((int)l) {
                case -1063637175: {
                    break block46;
                }
                case -305569780: {
                    l = (-7749558540348337207L - 6371579679631796926L) / (0x1C3E923FF01D2E7BL ^ 0xADDFECFAAD54D35EL);
                    continue block46;
                }
            }
            break;
        }
        byArray = new byte[0x3244563 ^ 0x3244561];
        byArray[0x5FDD98FD ^ 0x5FDD98FD] = 0x2DF2BE32 ^ 0x2DF2BE14;
        int n = 0x605CFD4 ^ 0x605CFD5;
        byArray[n] = 0x6D7C4984 ^ 0x6D7C49F6;
        int cfr_ignored_14 = 0x73CE31FB ^ 0xED0F662A;
        StringBuilder stringBuilder = new StringBuilder().append(new String(byArray, "UTF-8"));
        long l21 = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
        boolean bl = true;
        block47: while (true) {
            long l22;
            if (!bl || (bl = false) || !true) {
                l21 = l22 / (8296428827375940608L >>> "\u0000\u0000".length());
            }
            switch ((int)l21) {
                case -1063637175: {
                    break block47;
                }
                case 112011128: {
                    l22 = 0x734443F8395ACED2L ^ 0xDD7325235ABD68B9L;
                    continue block47;
                }
                case 292056920: {
                    l22 = 0xCD1004966DC59CA6L ^ 0xFDAE77F0738496BDL;
                    continue block47;
                }
            }
            break;
        }
        while (true) {
            long l23;
            long l24;
            if ((l24 = (l23 = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (0x6B4808B6E770F422L ^ 0xF1E250600C0F5DBL)) == 0L ? 0 : (l23 < 0L ? -1 : 1)) == false) continue;
            if (l24 == 584555959 - 584555960) break;
            l24 = 0x3FC03E3D ^ 0x7C916CDE;
        }
        while (true) {
            long l25 = 0x3780AD1DC82542ADL ^ 0xBFC7F408F893632CL;
            long l26 = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - l25;
            long l27 = l26 == 0L ? 0 : (l26 < 0L ? -1 : 1);
            if (l27 == false) continue;
            if (l27 == (0xC503F4EC ^ 0xC503F4ED)) break;
            l27 = -1846202671 - 1021004082;
        }
        StringBuilder stringBuilder2 = stringBuilder.append(ilVD.get(CRACKME_a40783d1_8e42_477e_b28a_cccd959eeb53753d2868));
        while (true) {
            long l28 = 0x3F34D89EB1ED8E15L ^ 0x62615FDE3B2D5CBFL;
            long l29 = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - l28;
            long l30 = l29 == 0L ? 0 : (l29 < 0L ? -1 : 1);
            if (l30 == false) continue;
            if (l30 == -279621050 - -279621049) break;
            l30 = -781213497 - 1172545517;
        }
        String string15 = stringBuilder2.toString();
        long l31 = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
        block51: while (true) {
            switch ((int)l31) {
                case -1063637175: {
                    return 0QZ8.1d9kfLKTTP4TVZYm((String)string15);
                }
                case 309596137: {
                    l31 = (0x666B387AE082101FL ^ 0x27243BEF7ADFE93L) / (0x48E8BC8AEF113CA7L ^ 0x3EBBA4D7EB7299B8L);
                    continue block51;
                }
            }
            break;
        }
        return 0QZ8.1d9kfLKTTP4TVZYm((String)string15);
    }

    /*
     * Unable to fully structure code
     */
    public void loadGifts() {
        if (e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c == 2022591311967144303L - -8679353479125689881L) {
            if (((-4291422948544622899L == -4291422948544622898L ? -1275631150 : 1703932315 - -295877732) ^ (622804664 ^ 1524678983)) != 0) {
                428382316 ^ 428382317;
            }
        } else {
            649048563 ^ 649048563;
        }
        v0 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
        block9: while (true) {
            switch ((int)v0) {
                case -2080862577: {
                    v0 = (4273979248788550588L - -4179272277064628355L) / (-1071033204118061918L ^ 6439194844615838758L);
                    continue block9;
                }
                case -1063637175: {
                    break block9;
                }
            }
            break;
        }
        v1 = e4wV.u_c4ZvPaZs10lF3x();
        while (true) {
            block16: {
                if ((v2 = (cfr_temp_0 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-6845935285252668454L ^ -2765138845766648976L)) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) {
                    continue;
                }
                if (v2 != (-3800556049049206672L == -3800556049049206671L ? -1730982496 : -994791358 - -994791357)) break block16;
                v3 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                if (true) ** GOTO lbl32
            }
            v2 = 413317340 >>> "\u0000\u0000".length();
        }
        block11: while (true) {
            v3 = v4 / (-6697131282174508637L ^ 6908873065474376768L);
lbl32:
            // 2 sources

            switch ((int)v3) {
                case -1063637175: {
                    break block11;
                }
                case 907189885: {
                    v4 = -8885090614932645299L ^ -1771720390197294495L;
                    continue block11;
                }
                case 1544202201: {
                    v4 = 4059097987268328717L - 7333692694878208936L;
                    continue block11;
                }
            }
            break;
        }
        e4wV.ilVD = v1.join();
    }

    private static /* synthetic */ List HNQeByj5LZOP-z7M(String string) {
        if (CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c == (0x9B1141C925B63183L ^ 0xF3AB78E8D59E4614L)) {
            if ((0x4E290B18 ^ 0xFDEA8794 ^ (-5678416018677349072L == -5678416018677349071L ? 1140265203 : 0x5D03BA44 ^ 0x22FC45BB)) == 0) return new ArrayList();
            int n = -7452306043481515069L == -7452306043481515068L ? -2068211735 : 0x28574CF6 ^ 0x28574CF7;
        } else {
            int cfr_ignored_0 = 0x6C58132C ^ 0x6C58132C;
        }
        return new ArrayList();
    }

    /*
     * Unable to fully structure code
     */
    public static String llvwH6rv7G2c7CPi(String var0) {
        block65: {
            if (e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c == (-7484600357220271878L == -7484600357220271877L ? -4178460873155714589L : -7065941864960412908L >>> "\u0000\u0000".length())) {
                if ((-642322663 - 2098290631 ^ (1580876971 ^ 566606676)) != 0) {
                    -1402395843 ^ -1402395844;
                }
            } else {
                645147809 ^ 645147809;
            }
            1732075441 - -59570016;
            while (true) {
                if ((v0 = (cfr_temp_0 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-6722077034834841897L - -5697182079278485995L)) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) continue;
                if (v0 == 146693513 - 146693514) break;
                v0 = 1499554347 - 808641057;
            }
            var1_1 = e4wV.wO\u0119g.entrySet().iterator();
            do {
                block69: {
                    block67: {
                        block68: {
                            block66: {
                                v1 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                block43: while (true) {
                                    switch ((int)v1) {
                                        case -1063637175: {
                                            break block43;
                                        }
                                        case 374897065: {
                                            v1 = (5313789415286361575L - 1637738979394035016L) / (5511623750262404441L - -1942525868096258888L);
                                            continue block43;
                                        }
                                    }
                                    break;
                                }
                                if (!var1_1.hasNext()) break block65;
                                var2_2 = var1_1.next();
                                -1715673324 >>> "\u0000\u0000".length();
                                v2 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                if (true) ** GOTO lbl64
                                block44: while (true) {
                                    v2 = v3 / (8778065264472656278L - 8749825830951897960L);
lbl64:
                                    // 2 sources

                                    switch ((int)v2) {
                                        case -1063637175: {
                                            break block44;
                                        }
                                        case -544423812: {
                                            v3 = 646637817014881696L >>> "\u0000\u0000".length();
                                            continue block44;
                                        }
                                        case 947166108: {
                                            if (6114016443342144435L == 6114016443342144436L) {
                                                v3 = 6591662599238724866L;
                                                continue block44;
                                            }
                                            v3 = -5212320962936419380L >>> "\u0000\u0000".length();
                                            continue block44;
                                        }
                                        case 1645140729: {
                                            v3 = -7839958343536531771L ^ -5983922189152062025L;
                                            continue block44;
                                        }
                                    }
                                    break;
                                }
                                CRACKME_0620b679_639b_4c04_970f_c15e0f6b0736f63db017 = (String)CRACKME_0620b679_639b_4c04_970f_c15e0f6b07369d1aa6f7.getKey();
                                v4 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                if (true) ** GOTO lbl88
                                block45: while (true) {
                                    v4 = v5 / (6029787200061367045L ^ 2222500816127328801L);
lbl88:
                                    // 2 sources

                                    switch ((int)v4) {
                                        case -1706842319: {
                                            v5 = 5932780942005837911L ^ 954568465439501749L;
                                            continue block45;
                                        }
                                        case -1063637175: {
                                            break block45;
                                        }
                                        case 45212932: {
                                            v5 = 2953200034674706031L - -19952083333212385L;
                                            continue block45;
                                        }
                                    }
                                    break;
                                }
                                CRACKME_0620b679_639b_4c04_970f_c15e0f6b073672194399 = (String)((List)CRACKME_0620b679_639b_4c04_970f_c15e0f6b07369d1aa6f7.getValue()).get(-744580404 ^ -744580404);
                                -871218100 >>> "\u0000\u0000".length();
                                v6 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                if (true) ** GOTO lbl117
                                block46: while (true) {
                                    v6 = v7 / (-5030623123148445343L ^ 5480543120566744081L);
lbl117:
                                    // 2 sources

                                    switch ((int)v6) {
                                        case -1063637175: {
                                            break block46;
                                        }
                                        case 2075355964: {
                                            if (6019648278227025139L == 6019648278227025140L) {
                                                v7 = 8362070160870526796L;
                                                continue block46;
                                            }
                                            v7 = -9103744912635203204L - 6639995960123768515L;
                                            continue block46;
                                        }
                                    }
                                    break;
                                }
                                v8 = new StringBuilder();
                                var9_8 = new byte[-519779474 ^ -519779479];
                                var9_8[893004590 ^ 893004587] = -1787974304 ^ -1787974377;
                                var9_8[1586736388 ^ 1586736388] = 450874394 ^ 450874418;
                                var9_8[-784975480 ^ -784975477] = 1406202177 ^ 1406202208;
                                var9_8[-1909040476 ^ -1909040474] = 6974728409902276350L == 6974728409902276351L ? -1790074078 : -326047455 ^ -326047459;
                                var9_8[6143862221515420784L == 6143862221515420785L ? -1356285011 : -1990559979 ^ -1990559983] = 489591580 ^ 489591616;
                                var9_8[-2526513984916666786L == -2526513984916666785L ? 1600464506 : 1997155130 ^ 1997155131] = -83454430 ^ -83454435;
                                var9_8[940134135 ^ 940134129] = 4551202056586795540L == 4551202056586795541L ? 500882597 : 1796217748 ^ 1796217789;
                                v9 = new String(var9_8, "UTF-8");
                                -935862458 ^ -2059019754;
                                while (true) {
                                    if ((v10 = (cfr_temp_1 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (4239295827180141627L - -1086390019325498462L)) == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1)) == false) continue;
                                    if (v10 == (-392569748 ^ 392569747)) {
                                        v11 = v8.append(v9);
                                        v12 = Pattern.quote(CRACKME_0620b679_639b_4c04_970f_c15e0f6b0736f63db017);
                                        if (-3766909848221697436L == -3766909848221697435L) {
                                            break;
                                        }
                                        break block66;
                                    }
                                    if (8672754977769813436L == 8672754977769813437L) {
                                        v10 = -1141221823;
                                        continue;
                                    }
                                    v10 = -222180337 ^ 2042669049;
                                }
                                v13 = 1325451411;
                                break block68;
                            }
                            v13 = 325772760 >>> "\u0000\u0000".length();
                        }
                        while (true) {
                            if ((v14 = (cfr_temp_2 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (9081217916170960410L - 5520680033031503919L)) == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1)) == false) continue;
                            if (v14 == -860994932 - -860994931) {
                                v15 = v11.append(v12);
                                2055452122 ^ 114546417;
                                if (-8311855659565047111L == -8311855659565047110L) {
                                    break;
                                }
                                break block67;
                            }
                            v14 = 1005853341 - -852327227;
                        }
                        v16 = -149687425;
                        break block69;
                    }
                    v16 = -1412151756 ^ -1412151758;
                }
                var9_8 = new byte[v16];
                var9_8[93388372 ^ 93388374] = 1922517511 ^ 1922517542;
                var9_8[363763857 ^ 363763858] = -99500935 ^ -99501019;
                var9_8[-1721322175 ^ -1721322175] = -837698597 ^ -837698573;
                var9_8[-867687844 ^ -867687843] = -867894545 ^ -867894576;
                var9_8[-5457397 ^ -5457394] = -481540120 ^ -481540159;
                var9_8[-2144127698 ^ -2144127702] = 854591215 ^ 854591128;
                -1062154793 ^ 770086888;
                var5_5 = v15.append(new String(var9_8, "UTF-8")).toString();
                var6_6 = Pattern.compile((String)CRACKME_0620b679_639b_4c04_970f_c15e0f6b073606f7e9bc);
                v17 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                if (true) ** GOTO lbl208
                block49: while (true) {
                    v17 = v18 / (2601388785827799668L - 484488567431199906L);
lbl208:
                    // 2 sources

                    switch ((int)v17) {
                        case -2089004043: {
                            v18 = 856097052685236233L - -6742681115384565299L;
                            continue block49;
                        }
                        case -1151977050: {
                            v18 = -478305041815963026L - -2644329215214032752L;
                            continue block49;
                        }
                        case -1063637175: {
                            break block49;
                        }
                        case 2130896584: {
                            v18 = -7291886608591665910L ^ 4866441762390349600L;
                            continue block49;
                        }
                    }
                    break;
                }
                CRACKME_0620b679_639b_4c04_970f_c15e0f6b0736fdeda506 = CRACKME_0620b679_639b_4c04_970f_c15e0f6b0736535d48f9.matcher(CRACKME_0620b679_639b_4c04_970f_c15e0f6b073687444eda);
                -1903136834 ^ -470740011;
                v19 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                if (true) ** GOTO lbl233
                block50: while (true) {
                    v19 = v20 / (-3507000720894241508L - -1009368060109310915L);
lbl233:
                    // 2 sources

                    switch ((int)v19) {
                        case -1063637175: {
                            break block50;
                        }
                        case -401949858: {
                            v20 = -1325324775355853019L ^ 7522425586276563194L;
                            continue block50;
                        }
                        case 1846282049: {
                            v20 = 7844045993597641243L - 7613904218516510537L;
                            continue block50;
                        }
                    }
                    break;
                }
            } while (!CRACKME_0620b679_639b_4c04_970f_c15e0f6b0736fdeda506.find());
            v21 = Matcher.quoteReplacement(CRACKME_0620b679_639b_4c04_970f_c15e0f6b073672194399);
            v22 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
            if (true) ** GOTO lbl252
            block51: while (true) {
                v22 = v23 / (-1463714886350814359L == -1463714886350814358L ? 6937904918368925284L : 4168184964449742601L - 5647169324385635889L);
lbl252:
                // 2 sources

                switch ((int)v22) {
                    case -1527935076: {
                        if (-6124851004734784126L == -6124851004734784125L) {
                            v23 = -2367363465767081685L;
                            continue block51;
                        }
                        v23 = -7412464197772922390L - 7760728414988372291L;
                        continue block51;
                    }
                    case -1492967824: {
                        v23 = 4624934481046595222L - -8290309664138826704L;
                        continue block51;
                    }
                    case -1063637175: {
                        break block51;
                    }
                    case -251021826: {
                        if (262967462429032534L == 262967462429032535L) {
                            v23 = 6839143475621130835L;
                            continue block51;
                        }
                        v23 = 1980887008309476889L - 4118633850092012263L;
                        continue block51;
                    }
                }
                break;
            }
            CRACKME_0620b679_639b_4c04_970f_c15e0f6b073687444eda = CRACKME_0620b679_639b_4c04_970f_c15e0f6b0736fdeda506.replaceFirst(v21);
        }
        252738495 ^ 1129209965;
        var9_8 = new byte[1130173745 ^ 1130173744];
        var9_8[-1877442711 ^ -1877442711] = -1707970894 ^ -1707970835;
        v24 = new String(var9_8, "UTF-8");
        var9_8 = new byte[-1566622336 ^ -1566622335];
        var9_8[1786469352 ^ 1786469352] = -1817066926 ^ -1817066881;
        v25 = new String(var9_8, "UTF-8");
        v26 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
        if (true) ** GOTO lbl288
        block52: while (true) {
            v26 = v27 / (6384434753558870805L - -4073016785575978132L);
lbl288:
            // 2 sources

            switch ((int)v26) {
                case -1791378382: {
                    v27 = 8716521069844062509L - -2515480680601708191L;
                    continue block52;
                }
                case -1630865962: {
                    v27 = -586605629556020662L ^ 4815883707094475959L;
                    continue block52;
                }
                case -1063637175: {
                    break block52;
                }
            }
            break;
        }
        return CRACKME_0620b679_639b_4c04_970f_c15e0f6b073687444eda.replace(v24, v25);
    }

    /*
     * Unable to fully structure code
     */
    public static String CS370SZfE_Wkcb11(String var0) {
        block48: {
            if (e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c == 1219035184960077430L - 4281185583556340049L) {
                if (((2125830877182102045L == 2125830877182102046L ? -571292022 : 374468869 - 925626546) ^ -697590172 - 1449893477) != 0) {
                    -1897297424 ^ -1897297423;
                }
            } else {
                -1792962602 ^ -1792962602;
            }
            while (true) {
                if ((v0 = (cfr_temp_0 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-4366634869925543579L ^ 6196502155519779197L)) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) continue;
                if (v0 == (-2058870187 ^ -2058870188)) {
                    if (e4wV.qL\u017a2 == null) {
                        break;
                    }
                    break block48;
                }
                v0 = -1282920796 - -1923212224;
            }
            return CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b6cce4d7a;
        }
        CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b48de32ae = e4wV.qL\u017a2.matcher(CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b6cce4d7a);
        v1 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
        if (true) ** GOTO lbl30
        block31: while (true) {
            v1 = v2 / (-603949201114972615L ^ -7788896690080229247L);
lbl30:
            // 2 sources

            switch ((int)v1) {
                case -1813565610: {
                    v2 = 3633477498074219365L - -7267817309314913430L;
                    continue block31;
                }
                case -1063637175: {
                    break block31;
                }
                case -660190658: {
                    v2 = 4074111102599044483L - -3842571841850438274L;
                    continue block31;
                }
                case 1320313788: {
                    v2 = -4183301315699464056L - 8458370724723416312L;
                    continue block31;
                }
            }
            break;
        }
        v3 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
        if (true) ** GOTO lbl46
        block32: while (true) {
            v3 = v4 / (-3715524371945027481L == -3715524371945027480L ? 1343649969154466579L : 2619047399298288944L ^ -6582825596630735444L);
lbl46:
            // 2 sources

            switch ((int)v3) {
                case -1063637175: {
                    break block32;
                }
                case 757884155: {
                    v4 = -7506017004478101535L ^ 3296278709131237921L;
                    continue block32;
                }
                case 845785571: {
                    if (8721888256463456707L == 8721888256463456708L) {
                        v4 = 1622714354224013577L;
                        continue block32;
                    }
                    v4 = 6418090578806439764L ^ -4756428253287654890L;
                    continue block32;
                }
            }
            break;
        }
        CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b8b1eac32 = new StringBuilder();
        -1418676438 - 62968786;
        400010461 ^ 1125907131;
        CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b5398d035 = 1863532584 ^ 1863532584;
        while (true) {
            block50: {
                block49: {
                    v5 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                    if (true) ** GOTO lbl75
                    block34: while (true) {
                        v5 = v6 / (3439148469579309424L == 3439148469579309425L ? 3410507704056018127L : -5023723865037606891L ^ -210185651895912971L);
lbl75:
                        // 2 sources

                        switch ((int)v5) {
                            case -1063637175: {
                                break block34;
                            }
                            case -214669648: {
                                v6 = -1753475091638196952L ^ 46390537021061366L;
                                continue block34;
                            }
                            case 494832871: {
                                v6 = -5113884529642974929L - 7685399113712057978L;
                                continue block34;
                            }
                            case 1662453472: {
                                v6 = -8665260523597075205L - 6732477128101095790L;
                                continue block34;
                            }
                        }
                        break;
                    }
                    if (!CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b48de32ae.find()) break block49;
                    v7 = -8854313609330647266L == -8854313609330647265L ? 1473851573 : -936665874 - -560273977;
                    v8 = 775638009150250653L == 775638009150250654L ? -719749097 : -329443238 ^ 1639976850;
                    break block50;
                }
                v9 = 4173637779296678470L == 4173637779296678471L ? 1058953947 : -422424794 ^ 1023644624;
                v10 = -1453037425352082941L == -1453037425352082940L ? 663268987 : -1688937673 - 1922571404;
                CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b8b1eac32.append(CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b6cce4d7a.substring(CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b5398d035));
                v11 = CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b8b1eac32.toString();
                var6_5 = new byte[-1665824265 ^ -1665824266];
                var6_5[2019581099 ^ 2019581099] = 929857960 ^ 929857925;
                v12 = new String(var6_5, "UTF-8");
                var6_5 = new byte[-4193464511439459498L == -4193464511439459497L ? 759943548 : 1447829834 ^ 1447829835];
                var6_5[-2136323044024197609L == -2136323044024197608L ? 1079099595 : -1011534664 ^ -1011534664] = -2102318573 ^ -2102318541;
                v13 = new String(var6_5, "UTF-8");
                v14 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                if (true) ** GOTO lbl195
            }
            2013079650 ^ -217025656;
            while (true) {
                if ((v15 = (cfr_temp_1 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (821411006673445836L ^ 8545032210637753076L)) == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1)) == false) continue;
                if (v15 == (-9054707053489018996L == -9054707053489018995L ? -1089773622 : -2059977225 - -2059977224)) break;
                v15 = -637160509 ^ 143259966;
            }
            v16 = CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b48de32ae.start();
            v17 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
            block36: while (true) {
                switch ((int)v17) {
                    case -1854086755: {
                        v17 = (-7179065815907096098L ^ 5335939161017967981L) / (1264072711121813020L ^ -7329447228491329203L);
                        continue block36;
                    }
                    case -1063637175: {
                        break block36;
                    }
                }
                break;
            }
            CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b8b1eac32.append(CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b6cce4d7a, CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b5398d035, v16);
            -252664877 - 1723931637;
            var4_4 = CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b48de32ae.group();
            -1650046254 ^ -1440154583;
            while (true) {
                if ((v18 = (cfr_temp_2 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (6208704165952317584L ^ 8978542926520156942L)) == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1)) == false) continue;
                if (v18 == -887059626 - -887059625) break;
                v18 = 1010415621 ^ -345637873;
            }
            609547018 ^ 530996883;
            CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b8b1eac32.append(e4wV.Vajs.getOrDefault(CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b3f1d309c, (String)CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b3f1d309c));
            150862739 ^ -1228481696;
            140159887 - 281023722;
            v19 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
            block38: while (true) {
                switch ((int)v19) {
                    case -1965861651: {
                        v19 = (3636164073225361371L ^ -5554672997077383057L) / (-8448625777742291621L - -3070855048824585700L);
                        continue block38;
                    }
                    case -1063637175: {
                        break block38;
                    }
                }
                break;
            }
            CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b5398d035 = CRACKME_7f11b6eb_887c_46b0_a564_4bf87f72c85b48de32ae.end();
            349735078 ^ -1189976853;
        }
        block39: while (true) {
            v14 = v20 / (2961002701667900000L - -4059427519533753080L);
lbl195:
            // 2 sources

            switch ((int)v14) {
                case -1063637175: {
                    break block39;
                }
                case -916361897: {
                    v20 = -6610521279855332029L ^ 9092339434101952848L;
                    continue block39;
                }
                case -541134699: {
                    v20 = 5994710421109120277L ^ -4387839220976515863L;
                    continue block39;
                }
            }
            break;
        }
        v21 = v11.replace(v12, v13);
        while (true) {
            if ((v22 = (cfr_temp_3 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-4310955206243028867L == -4310955206243028866L ? 4878135496679250664L : -7098730526733613684L - -8083280623369469771L)) == 0L ? 0 : (cfr_temp_3 < 0L ? -1 : 1)) == false) continue;
            if (v22 == (-636066622109161559L == -636066622109161558L ? 828093376 : -607472430 ^ 607472429)) {
                return 0QZ8.1d9kfLKTTP4TVZYm((String)v21);
            }
            v22 = -443348611 ^ 1114916159;
        }
    }

    /*
     * Unable to fully structure code
     */
    public static String YXwhAXryZbC3UCh0(String var0) {
        block130: {
            block131: {
                block126: {
                    block125: {
                        block124: {
                            if (e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c == (2734360033861439828L ^ -3495940437779543677L)) {
                                564381681 ^ 564381680;
                            } else {
                                1618040408 ^ 1618040408;
                            }
                            var4_1 = new byte[-1075276348 ^ -1075276347];
                            var4_1[1054808469 ^ 1054808469] = 1852059975 ^ 1852060010;
                            v0 = new String(var4_1, "UTF-8");
                            var4_1 = new byte[-1639234776 ^ -1639234775];
                            var4_1[1827970641 ^ 1827970641] = 1534916563 ^ 1534916492;
                            v1 = new String(var4_1, "UTF-8");
                            -1402850245 - -179632337;
                            while (true) {
                                v2 = 3677678162826098188L ^ -6285495426623103347L;
                                cfr_temp_0 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - v2;
                                v3 = cfr_temp_0 == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                                if (v3 == false) continue;
                                if (v3 == (1967167613 ^ 1967167612)) break;
                                v3 = 1526736096 ^ 1425205342;
                            }
                            v4 = -1772722849 ^ -1772722850;
                            var4_1 = new byte[v4];
                            var4_1[2062847795 ^ 2062847795] = 1053237422 ^ 1053237390;
                            v5 = new String(var4_1, "UTF-8");
                            var4_1 = new byte[-517204228 ^ -517204227];
                            var4_1[833485269 ^ 833485269] = 418966256 ^ 418966191;
                            v6 = CRACKME_2ce959f7_09a9_4b7e_8113_2b4209fdf3ca47a8e998.replace(v0, v1).replace(v5, new String(var4_1, "UTF-8"));
                            var4_1 = new byte[-1822408556 ^ -1822408555];
                            var4_1[1166718318 ^ 1166718318] = 101794213 ^ 101794178;
                            v7 = new String(var4_1, "UTF-8");
                            var4_1 = new byte[-1895173845 ^ -1895173845];
                            v8 = new String(var4_1, "UTF-8");
                            while (true) {
                                block127: {
                                    if ((v9 = (cfr_temp_1 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-4940659761796378340L - 6695865948839693142L)) == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1)) == false) continue;
                                    if (v9 != (-1824907346 ^ 1824907345)) break block127;
                                    var4_1 = new byte[180113363 ^ 180113360];
                                    var4_1[-663174991 ^ -663174991] = 1487664171 - 1487664201;
                                    var4_1[-522816677 ^ -522816678] = -141555180 ^ 141555092;
                                    v10 = 663259555 ^ 663259553;
                                    var4_1[v10] = -1707343295 - -1707343192;
                                    v11 = new String(var4_1, "UTF-8");
                                    var4_1 = new byte[-1914226356 ^ -1914226356];
                                    v12 = v6.replace(v7, v8).replace(v11, new String(var4_1, "UTF-8")).toLowerCase();
                                    v13 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                    if (true) ** GOTO lbl70
                                }
                                v9 = 255591353 - -946549752;
                            }
                            block84: while (true) {
                                v13 = v14 / (-8818905347281992244L >>> "\u0000\u0000".length());
lbl70:
                                // 2 sources

                                switch ((int)v13) {
                                    case -1063637175: {
                                        break block84;
                                    }
                                    case -454942259: {
                                        v14 = 6848719430423211428L >>> "\u0000\u0000".length();
                                        continue block84;
                                    }
                                    case 1139522040: {
                                        v14 = -5075119816502509060L ^ -3778934498995828639L;
                                        continue block84;
                                    }
                                }
                                break;
                            }
                            var1_2 = e4wV.CS370SZfE_Wkcb11(v12);
                            v15 = 196189503 ^ 196189502;
                            var4_1 = new byte[v15];
                            var4_1[184405632 ^ 184405632] = -300476266 ^ -300476250;
                            v16 = new String(var4_1, "UTF-8");
                            while (true) {
                                if ((v17 = (cfr_temp_2 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (4981004866937097690L ^ -2443810019848403680L)) == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1)) == false) continue;
                                if (v17 == (681634042 ^ 681634043)) {
                                    if (CRACKME_2ce959f7_09a9_4b7e_8113_2b4209fdf3ca5cde6d86.equals(v16)) {
                                        break;
                                    }
                                    break block124;
                                }
                                v17 = -1048869913 ^ -13134127;
                            }
                            var4_1 = new byte[-966605696 ^ -966605696];
                            return new String(var4_1, "UTF-8");
                        }
                        while (true) {
                            block128: {
                                if ((v18 = (cfr_temp_3 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (6839389850688549460L >>> "\u0000\u0000".length())) == 0L ? 0 : (cfr_temp_3 < 0L ? -1 : 1)) == false) continue;
                                if (v18 != (-1501401848 ^ 1501401847)) break block128;
                                v19 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                                if (true) ** GOTO lbl115
                            }
                            v18 = 1264164484 - 461303382;
                        }
                        block87: while (true) {
                            v20 = -3892324766968313652L ^ 5265533959100925650L;
                            v19 = v21 / v20;
lbl115:
                            // 2 sources

                            switch ((int)v19) {
                                case -1265158728: {
                                    v21 = 5647374946804235325L ^ 1354931079351302742L;
                                    continue block87;
                                }
                                case -1063637175: {
                                    break block87;
                                }
                                case -361454080: {
                                    v21 = 1515668402175814135L ^ 8719053548674891068L;
                                    continue block87;
                                }
                            }
                            break;
                        }
                        if (!e4wV.EOfG.containsKey(CRACKME_2ce959f7_09a9_4b7e_8113_2b4209fdf3ca5cde6d86)) break block126;
                        -1986765248 ^ -816778093;
                        v22 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                        if (true) ** GOTO lbl132
                        block88: while (true) {
                            v22 = v23 / (-4986805175729136209L ^ 5838952153717418435L);
lbl132:
                            // 2 sources

                            switch ((int)v22) {
                                case -2127276271: {
                                    v23 = -3225792116662307015L - -2376892303935217456L;
                                    continue block88;
                                }
                                case -1063637175: {
                                    break block88;
                                }
                                case 785584066: {
                                    v23 = 3576987034453381249L - -5350638910649426055L;
                                    continue block88;
                                }
                            }
                            break;
                        }
                        CRACKME_2ce959f7_09a9_4b7e_8113_2b4209fdf3ca290071d2 = e4wV.EOfG.get(CRACKME_2ce959f7_09a9_4b7e_8113_2b4209fdf3ca5cde6d86).get(1058040623 ^ 1058040623);
                        v24 = 1831222749 ^ 1600203424;
                        while (true) {
                            if ((v25 = (cfr_temp_4 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (725395956188931166L ^ -4203640900789897312L)) == 0L ? 0 : (cfr_temp_4 < 0L ? -1 : 1)) == false) continue;
                            v26 = 1902562502 ^ 1902562503;
                            if (v25 == v26) {
                                if (e4wV.ilVD != null) {
                                    break;
                                }
                                break block125;
                            }
                            v25 = -1575074857 - -522938654;
                        }
                        v27 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                        if (true) ** GOTO lbl169
                        block90: while (true) {
                            v27 = (6804945487873702952L ^ -1800473074063788668L) / v28;
lbl169:
                            // 2 sources

                            switch ((int)v27) {
                                case -1063637175: {
                                    break block90;
                                }
                                case 188227077: {
                                    v28 = 2001812320702337337L ^ -5170117342728738178L;
                                    continue block90;
                                }
                            }
                            break;
                        }
                        while (true) {
                            v29 = 8277926754121655745L - 1560659706406190190L;
                            cfr_temp_5 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - v29;
                            v30 = cfr_temp_5 == 0L ? 0 : (cfr_temp_5 < 0L ? -1 : 1);
                            if (v30 == false) continue;
                            v31 = -105452981 ^ 105452980;
                            if (v30 == v31) {
                                if (e4wV.ilVD.containsKey(CRACKME_2ce959f7_09a9_4b7e_8113_2b4209fdf3ca290071d2)) {
                                    break;
                                }
                                break block125;
                            }
                            v30 = 653531251 ^ 602661932;
                        }
                        713368077 ^ 243552463;
                        v32 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                        if (true) ** GOTO lbl210
                    }
                    v33 = -761314955 ^ 214732081;
                    -306739114 - 2565083;
                    if (e4wV.ilVD == null) break block126;
                    v34 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                    if (true) ** GOTO lbl301
                    block92: while (true) {
                        v32 = v35 / (6898760467095307567L - -1548631849337614842L);
lbl210:
                        // 2 sources

                        switch ((int)v32) {
                            case -1668538336: {
                                v35 = 1419887077242997595L - -7934424389034458318L;
                                continue block92;
                            }
                            case -1063637175: {
                                break block92;
                            }
                            case 2100224442: {
                                v35 = -6436381758442066989L ^ -948057468325232319L;
                                continue block92;
                            }
                        }
                        break;
                    }
                    v36 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                    if (true) ** GOTO lbl223
                    block93: while (true) {
                        v36 = (4416007585422411049L - 6283580083570546805L) / v37;
lbl223:
                        // 2 sources

                        switch ((int)v36) {
                            case -1703595510: {
                                v37 = 7909586124188824208L ^ -4531745164679773606L;
                                continue block93;
                            }
                            case -1063637175: {
                                break block93;
                            }
                        }
                        break;
                    }
                    v38 = new StringBuilder();
                    v39 = 17587274 ^ 17587272;
                    var4_1 = new byte[v39];
                    v40 = -793850383 ^ -793850383;
                    var4_1[v40] = -911765222 ^ -911765188;
                    var4_1[-1591441750 ^ -1591441749] = -1389857150 ^ -1389857040;
                    v41 = new String(var4_1, "UTF-8");
                    v42 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                    if (true) ** GOTO lbl243
                    block94: while (true) {
                        v43 = 4658058365750117135L - -4399537094580188877L;
                        v42 = v44 / v43;
lbl243:
                        // 2 sources

                        switch ((int)v42) {
                            case -1241289992: {
                                v44 = -6783948593901768079L ^ -678587230175965059L;
                                continue block94;
                            }
                            case -1063637175: {
                                break block94;
                            }
                            case -284945904: {
                                v44 = 977110438708396437L ^ -6255646042686456947L;
                                continue block94;
                            }
                            case 359135380: {
                                v44 = -5723120955284704695L ^ 6475026632210537485L;
                                continue block94;
                            }
                        }
                        break;
                    }
                    v45 = v38.append(v41);
                    while (true) {
                        if ((v46 = (cfr_temp_6 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (3526744572155118889L ^ -7482981315394187705L)) == 0L ? 0 : (cfr_temp_6 < 0L ? -1 : 1)) == false) continue;
                        v47 = 321389826 ^ 321389827;
                        if (v46 == v47) break;
                        v46 = -521178227 ^ 375684743;
                    }
                    while (true) {
                        block129: {
                            if ((v48 = (cfr_temp_7 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-3597958040274896770L ^ -8969172172475263273L)) == 0L ? 0 : (cfr_temp_7 < 0L ? -1 : 1)) == false) continue;
                            v49 = -442392528 - -442392527;
                            if (v48 != v49) break block129;
                            v50 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                            if (true) ** GOTO lbl276
                        }
                        v48 = 156676771 - -1308727097;
                    }
                    block97: while (true) {
                        v50 = v51 / (-131979374534637863L ^ 5291364032133767720L);
lbl276:
                        // 2 sources

                        switch ((int)v50) {
                            case -1445208915: {
                                v51 = 6162127852122087116L - -2742647699469658258L;
                                continue block97;
                            }
                            case -1063637175: {
                                break block97;
                            }
                            case 116386993: {
                                v51 = 8415823408719457431L ^ -3417086639163232120L;
                                continue block97;
                            }
                            case 770862024: {
                                v51 = 1366811177121990939L ^ 5251233534815825597L;
                                continue block97;
                            }
                        }
                        break;
                    }
                    v52 = v45.append(e4wV.ilVD.get(CRACKME_2ce959f7_09a9_4b7e_8113_2b4209fdf3ca290071d2));
                    v53 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                    block98: while (true) {
                        switch ((int)v53) {
                            case -1063637175: {
                                return 0QZ8.1d9kfLKTTP4TVZYm((String)v52.toString());
                            }
                            case -969919381: {
                                v53 = (3828455702936986004L >>> "\u0000\u0000".length()) / (5152031683626421942L - 8722918244852782188L);
                                continue block98;
                            }
                        }
                        break;
                    }
                    return 0QZ8.1d9kfLKTTP4TVZYm((String)v52.toString());
                    block99: while (true) {
                        v34 = v54 / (-2375112932085418549L ^ -906643029485362766L);
lbl301:
                        // 2 sources

                        switch ((int)v34) {
                            case -1923338823: {
                                v54 = 561623382361006323L - -7529334287683477672L;
                                continue block99;
                            }
                            case -1063637175: {
                                break block99;
                            }
                            case -1007429481: {
                                v54 = -2074927300348833733L - 7839259402077778611L;
                                continue block99;
                            }
                            case -520725323: {
                                v54 = 8873920892512784072L >>> "\u0000\u0000".length();
                                continue block99;
                            }
                        }
                        break;
                    }
                    while (true) {
                        if ((v55 = (cfr_temp_8 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-588187663301427272L - -7198742338725605213L)) == 0L ? 0 : (cfr_temp_8 < 0L ? -1 : 1)) == false) continue;
                        v56 = 1727786349 ^ -1727786350;
                        if (v55 == v56) {
                            if (e4wV.ilVD.containsKey(CRACKME_2ce959f7_09a9_4b7e_8113_2b4209fdf3ca5cde6d86)) {
                                break;
                            }
                            break block126;
                        }
                        v55 = -102222283 ^ -2056666106;
                    }
                    v57 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                    if (true) ** GOTO lbl332
                }
                29216820 ^ -454105300;
                if (e4wV.ilVD == null) break block130;
                break block131;
                block101: while (true) {
                    v57 = v58 / (7585658544275950320L >>> "\u0000\u0000".length());
lbl332:
                    // 2 sources

                    switch ((int)v57) {
                        case -1063637175: {
                            break block101;
                        }
                        case 1116278549: {
                            v58 = -331405591355845510L ^ -6074299164720532115L;
                            continue block101;
                        }
                    }
                    break;
                }
                v59 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                if (true) ** GOTO lbl345
                block102: while (true) {
                    v59 = (-3153828016194527832L ^ -5522246120682251192L) / v60;
lbl345:
                    // 2 sources

                    switch ((int)v59) {
                        case -1783484660: {
                            v60 = 3179394235819506575L ^ -5420294067644796865L;
                            continue block102;
                        }
                        case -1063637175: {
                            break block102;
                        }
                    }
                    break;
                }
                v61 = new StringBuilder();
                var4_1 = new byte[-854077596 ^ -854077594];
                v62 = -1413971854 ^ -1413971884;
                var4_1[210153672 ^ 210153672] = v62;
                v63 = 780860978 ^ 780860979;
                var4_1[v63] = -1895841575 ^ -1895841621;
                v64 = new String(var4_1, "UTF-8");
                v65 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                if (true) ** GOTO lbl364
                block103: while (true) {
                    v65 = v66 / (-6658032399517817986L - 4335958265346361065L);
lbl364:
                    // 2 sources

                    switch ((int)v65) {
                        case -2139082360: {
                            v66 = 2819661560363071714L ^ -5061450594552165917L;
                            continue block103;
                        }
                        case -1239995565: {
                            v66 = -9048497755781537719L ^ 8490240775157195182L;
                            continue block103;
                        }
                        case -1063637175: {
                            break block103;
                        }
                    }
                    break;
                }
                v67 = v61.append(v64);
                while (true) {
                    if ((v68 = (cfr_temp_9 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (8682422996231567556L >>> "\u0000\u0000".length())) == 0L ? 0 : (cfr_temp_9 < 0L ? -1 : 1)) == false) continue;
                    v69 = 1012436961 ^ -1012436962;
                    if (v68 == v69) break;
                    v68 = 2072450419 ^ -1549981103;
                }
                while (true) {
                    if ((v70 = (cfr_temp_10 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (1810756506235446700L >>> "\u0000\u0000".length())) == 0L ? 0 : (cfr_temp_10 < 0L ? -1 : 1)) == false) continue;
                    v71 = 1472830466 ^ -1472830467;
                    if (v70 == v71) break;
                    v70 = -387250583 - 614702819;
                }
                while (true) {
                    block132: {
                        if ((v72 = (cfr_temp_11 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (-464583356262512769L ^ 4868892848052030251L)) == 0L ? 0 : (cfr_temp_11 < 0L ? -1 : 1)) == false) continue;
                        if (v72 != (-1244104061 ^ -1244104062)) break block132;
                        v73 = v67.append(e4wV.ilVD.get(CRACKME_2ce959f7_09a9_4b7e_8113_2b4209fdf3ca5cde6d86)).toString();
                        v74 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                        if (true) ** GOTO lbl398
                    }
                    v72 = -1861299131 ^ -649981790;
                }
                block107: while (true) {
                    v74 = v75 / (-2221258433821809937L ^ 6806353486812964323L);
lbl398:
                    // 2 sources

                    switch ((int)v74) {
                        case -1814098007: {
                            v75 = -4526625536823975205L ^ -7652100968170692266L;
                            continue block107;
                        }
                        case -1063637175: {
                            return 0QZ8.1d9kfLKTTP4TVZYm((String)v73);
                        }
                        case 126224668: {
                            v75 = 1962831605633870884L ^ 6701036508637453215L;
                            continue block107;
                        }
                        case 229335995: {
                            v75 = -2119231715086670471L ^ 1579118943699535775L;
                            continue block107;
                        }
                    }
                    break;
                }
                return 0QZ8.1d9kfLKTTP4TVZYm((String)v73);
            }
            v76 = -1815281237 ^ -155972827;
            v77 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
            block108: while (true) {
                switch ((int)v77) {
                    case -1063637175: {
                        break block108;
                    }
                    case -342345585: {
                        v77 = (6113835325689625904L >>> "\u0000\u0000".length()) / (-9182072692975746858L ^ -4194308225409387077L);
                        continue block108;
                    }
                }
                break;
            }
            if (!e4wV.ilVD.containsKey(CRACKME_2ce959f7_09a9_4b7e_8113_2b4209fdf3ca5cde6d86)) break block130;
            v78 = new StringBuilder();
            var4_1 = new byte[714345464 ^ 714345466];
            var4_1[856410511 ^ 856410510] = 904493024 ^ 904492946;
            var4_1[-1634484173 ^ -1634484173] = 1284047487 ^ 1284047449;
            v79 = new String(var4_1, "UTF-8");
            v80 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
            if (true) ** GOTO lbl451
        }
        var4_1 = new byte[506047626 ^ 506047626];
        return new String(var4_1, "UTF-8");
        block109: while (true) {
            v81 = 8196500274881308720L ^ -437247377739465028L;
            v80 = v82 / v81;
lbl451:
            // 2 sources

            switch ((int)v80) {
                case -1103912328: {
                    v82 = 7439963762077753818L - 1926146794069033230L;
                    continue block109;
                }
                case -1063637175: {
                    break block109;
                }
                case 1194664614: {
                    v82 = 7148553762782627864L ^ 3254516219843124982L;
                    continue block109;
                }
            }
            break;
        }
        v83 = v78.append(v79);
        -1883039729 ^ 1944554148;
        while (true) {
            block133: {
                if ((v84 = (cfr_temp_12 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (374088874852119878L - -8359249550613091108L)) == 0L ? 0 : (cfr_temp_12 < 0L ? -1 : 1)) == false) continue;
                if (v84 != -700896122 - -700896121) break block133;
                2131299884 ^ -490184054;
                v85 = v83.append(e4wV.ilVD.get(CRACKME_2ce959f7_09a9_4b7e_8113_2b4209fdf3ca5cde6d86));
                v86 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c;
                if (true) ** GOTO lbl479
            }
            v84 = 1680396773 - 1398625709;
        }
        block111: while (true) {
            v86 = (-7924325122199884352L >>> "\u0000\u0000".length()) / v87;
lbl479:
            // 2 sources

            switch ((int)v86) {
                case -1063637175: {
                    break block111;
                }
                case -624978322: {
                    v87 = -4858877666310548520L ^ 7412797279032575620L;
                    continue block111;
                }
            }
            break;
        }
        v88 = v85.toString();
        while (true) {
            if ((v89 = (cfr_temp_13 = e4wV.CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (1911803098457443682L ^ 7526006656898908885L)) == 0L ? 0 : (cfr_temp_13 < 0L ? -1 : 1)) == false) continue;
            if (v89 == (1332353910 ^ -1332353911)) {
                -1942816170 - -1786113710;
                return 0QZ8.1d9kfLKTTP4TVZYm((String)v88);
            }
            v89 = 1952136791 - -1052473391;
        }
    }

    public e4wV() {
        if (CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c == (0xAACC4CC56E94C663L ^ 0x847019E1E87AD67BL)) {
            if (((457144457846162090L == 457144457846162091L ? 919093496 : 212536783 - -1457506937) ^ -1966873868 - 180609781) != 0) {
                int cfr_ignored_1 = 0x405EE1BD ^ 0x405EE1BC;
            }
        } else {
            int n = 6189243188958689188L == 6189243188958689189L ? -1316466684 : 0xE21775C8 ^ 0xE21775C8;
        }
        int cfr_ignored_2 = 1677077141 - -48480051;
        while (true) {
            long l;
            long l2;
            if ((l2 = (l = CRACKME_05fe67c3_47b2_40ed_9af1_af365f7f0291_0eacf59c - (0xC1EF8B24AE0EE6E4L ^ 0xE80924579659218L)) == 0L ? 0 : (l < 0L ? -1 : 1)) == false) {
                continue;
            }
            if (l2 == (5682715947834570514L == 5682715947834570515L ? 275823164 : 0x94EACD4E ^ 0x94EACD4F)) {
                e4wV CRACKME_532da973_ff64_48e6_ac05_6a4e9aa53ffe099dbabb;
                int cfr_ignored_3 = 0x4651695 ^ 0xE2A9B60D;
                int cfr_ignored_4 = 0x204CE4CF ^ 0xAC6229C3;
                return;
            }
            l2 = 1002070405 - 1666965415;
        }
    }
}
