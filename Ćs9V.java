/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  8\u0105ja
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.command.TabCompleter
 *  org.bukkit.util.StringUtil
 *  zNb\u015b
 *  \u00d3vR\u017a
 *  \u017b\u017c\u0107m
 */
import java.io.File;
import java.lang.invoke.LambdaMetafactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public class \u0106s9V
implements TabCompleter,
CommandExecutor {
    private static long CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 = -4717594544114520499L;
    private static String[] CRACKME_BITCH = new String[15];
    private static final Pattern Z1X8;

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

    /*
     * Handled impossible loop by adding 'first' condition
     */
    static {
        \u0106s9V.CRACKME_BITCH[0] = "\u2282_\u30fd";
        \u0106s9V.CRACKME_BITCH[1] = "\u3000 \uff3c\uff3c\u3000\u3000\u3000";
        \u0106s9V.CRACKME_BITCH[2] = "\u3000\u3000 \uff3c( \u0361\u00b0 \u035c\u0296 \u0361\u00b0)\u3000\u3000\u3000";
        \u0106s9V.CRACKME_BITCH[3] = "\u3000\u3000\u3000 >\u3000\u2312\u30fd\u3000\u3000\u3000";
        \u0106s9V.CRACKME_BITCH[4] = "\u3000\u3000\u3000/ \u3000 \u3078\uff3c\u3000\u3000\u3000";
        \u0106s9V.CRACKME_BITCH[5] = "\u3000\u3000 /\u3000\u3000/\u3000\uff3c\uff3c\u3000\u3000\u3000";
        \u0106s9V.CRACKME_BITCH[6] = "\u3000\u3000 \uff9a\u3000\u30ce\u3000\u3000 \u30fd_\u3064\u3000\u3000\u3000";
        \u0106s9V.CRACKME_BITCH[7] = "\u3000\u3000/\u3000/\u3000\u3000\u3000";
        \u0106s9V.CRACKME_BITCH[8] = "\u3000 /\u3000/|\u3000\u3000\u3000";
        \u0106s9V.CRACKME_BITCH[9] = "\u3000(\u3000(\u30fd\u3000\u3000\u3000";
        \u0106s9V.CRACKME_BITCH[10] = "\u3000|\u3000|\u3001\uff3c\u3000\u3000\u3000";
        \u0106s9V.CRACKME_BITCH[11] = "\u3000| \u4e3f \uff3c \u2312)\u3000\u3000\u3000";
        \u0106s9V.CRACKME_BITCH[12] = "\u3000| |\u3000\u3000) /\u3000\u3000\u3000";
        \u0106s9V.CRACKME_BITCH[13] = "\u30ce )\u3000\u3000L\uff89\u3000\u3000\u3000";
        \u0106s9V.CRACKME_BITCH[14] = "(_\uff0f\u3000\u3000\u3000\u3000\u3000\u3000";
        if (CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == (0xA955CC222F66E187L ^ 0x6DEAB5C05BB3F7A8L)) {
            int cfr_ignored_2 = 0x1DC2AF5B ^ 0x1DC2AF5A;
        } else {
            int n = 0xB62FF2A8 ^ 0xB62FF2A8;
        }
        int cfr_ignored_3 = 0x2FBBF1BC ^ 0x880A025A;
        int n = 0xCBC3CBB3 ^ 0xCBC3CBA5;
        byte[] byArray = new byte[n];
        int n2 = 0x8BEBE704 ^ 0x8BEBE729;
        byArray[0x9A2888F6 ^ 0x9A2888F0] = n2;
        byArray[0xC1E42F40 ^ 0xC1E42F4E] = 0x6787BECA ^ 0x6787BE96;
        byArray[0xD0B7B500 ^ 0xD0B7B510] = 0xEEB969F2 ^ 0xEEB969AE;
        byArray[0xDD606D39 ^ 0xDD606D2B] = 0xE2114C5B ^ 0xE2114C20;
        byArray[0xF7BFDB74 ^ 0xF7BFDB61] = 0x2FF6E411 ^ 0x2FF6E44C;
        int n3 = 0x9715E5F ^ 0x9715E4B;
        byArray[n3] = 0x4D513ADA ^ 0x4D513AA7;
        byArray[0x8CDAA8E8 ^ 0x8CDAA8EF] = 0x2C95E2B7 ^ 0x2C95E2ED;
        byArray[0xDCE9A769 ^ 0xDCE9A763] = 0xE115A331 ^ 0xE115A308;
        byArray[0x2ECA654B ^ 0x2ECA6546] = 0x9D76412C ^ 0x9D764101;
        byArray[0x72E28EFB ^ 0x72E28EF7] = 0x147FFF3F ^ 0x147FFF63;
        byArray[0x2F77F5D8 ^ 0x2F77F5D1] = 0xE0842033 ^ 0xE084201E;
        int n4 = 0xDF213987 ^ 0xDF2139FD;
        byArray[0x56ECBAB9 ^ 0x56ECBABD] = n4;
        int n5 = 0xAFDA61F1 ^ 0xAFDA61C1;
        byArray[0xFF6072EE ^ 0xFF6072E6] = n5;
        byArray[0xC60C45B5 ^ 0xC60C45BA] = 0x7DE9D809 ^ 0x7DE9D827;
        int n6 = 0x6E9FAF07 ^ 0x6E9FAF06;
        byArray[n6] = 0x990967BB ^ 0x990967E5;
        byArray[0x1B21AEEE ^ 0x1B21AEE5] = 0x9592EDA8 ^ 0x9592ED88;
        byArray[0x5454DD16 ^ 0x5454DD16] = 0x81083069 ^ 0x81083032;
        byArray[0x76700AB8 ^ 0x76700ABD] = 0x9D726F24 ^ 0x9D726F65;
        int n7 = 0x45ED0F3C ^ 0x45ED0F2D;
        byArray[n7] = 0x388790EB ^ 0x3887909B;
        int n8 = 0x9246AAC4 ^ 0x9246AA88;
        byArray[0xB273BCA0 ^ 0xB273BCB3] = n8;
        byArray[0x1A49DA06 ^ 0x1A49DA04] = 0xAB30A2B1 ^ 0xAB30A2D0;
        byArray[0x1E797409 ^ 0x1E79740A] = 0x99A42C19 ^ 0x99A42C34;
        String string = new String(byArray, "UTF-8");
        long l = CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
        boolean bl = true;
        block5: while (true) {
            long l2;
            if (!bl || (bl = false) || !true) {
                l = l2 / (0xCCAB60C18B28F107L ^ 0x349587938ECFCD8BL);
            }
            switch ((int)l) {
                case -876892595: {
                    break block5;
                }
                case 1729086305: {
                    l2 = 0x368DF75BA35A2E99L ^ 0x5AC0DE7BF2819333L;
                    continue block5;
                }
                case 2086335663: {
                    l2 = 0x98C8B17C272A67FAL ^ 0xC315C021AC4B060L;
                    continue block5;
                }
            }
            break;
        }
        Pattern pattern = Pattern.compile(string);
        while (true) {
            long l3;
            long l4;
            if ((l4 = (l3 = CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (0x93942D3B5B046820L ^ 0x19FD0984BD424169L)) == 0L ? 0 : (l3 < 0L ? -1 : 1)) == false) {
                continue;
            }
            int n9 = 0xEBEBB595 ^ 0xEBEBB594;
            if (l4 == n9) {
                Z1X8 = pattern;
                int cfr_ignored_4 = 0x2921E406 ^ 0x5BF763EB;
                int cfr_ignored_5 = -342597280 >>> "\u0000\u0000".length();
                return;
            }
            l4 = 0xDB192AAF ^ 0xF8549ED7;
        }
    }

    /*
     * Unable to fully structure code
     */
    private static /* synthetic */ String G1Lj9qnx4DwJxCuH(String var0) {
        block26: {
            block28: {
                block25: {
                    block24: {
                        if (\u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == (-5546496827427851704L == -5546496827427851703L ? -3009902944473840046L : 2507973486360880747L - 7808814207799423549L)) {
                            if ((-1380409127 ^ -434130760 ^ (-1241447953863402057L == -1241447953863402056L ? -1476431824 : 2125812018 ^ 21671629)) != 0) {
                                -223872828 ^ -223872827;
                            }
                        } else {
                            2044544649 ^ 2044544649;
                        }
                        var2_1 = new byte[1550717410 ^ 1550717411];
                        var2_1[-1582668240 ^ -1582668240] = -994104962 ^ -994104994;
                        v0 = new String(var2_1, "UTF-8");
                        while (true) {
                            if ((v1 = (cfr_temp_0 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-5519671321842420508L ^ -1387594539153513102L)) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) {
                                continue;
                            }
                            if (v1 == -2079092945 - -2079092944) {
                                if (!CRACKME_aff93d50_6085_4e90_9112_785ad203ec9bc9b8366e.contains(v0)) break;
                                break block24;
                            }
                            v1 = -272175678 ^ 345529771;
                        }
                        v2 = CRACKME_aff93d50_6085_4e90_9112_785ad203ec9bc9b8366e;
                        break block26;
                    }
                    while (true) {
                        if ((v3 = (cfr_temp_1 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-1951707357570660145L - -4579317710524719178L)) == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1)) == false) {
                            continue;
                        }
                        if (v3 == (-1620654264 ^ 1620654263)) break;
                        if (8988574641350431939L == 8988574641350431940L) {
                            v3 = 1080294365;
                            continue;
                        }
                        v3 = -1592457868 ^ -83158060;
                    }
                    while (true) {
                        block27: {
                            if ((v4 = (cfr_temp_2 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-3013259409123472245L ^ -5542966515075403478L)) == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1)) == false) {
                                continue;
                            }
                            if (v4 != (1111324952601770185L == 1111324952601770186L ? 702614169 : 8576003 ^ -8576004)) break block27;
                            v5 = new StringBuilder();
                            var2_1 = new byte[2105142560 ^ 2105142561];
                            var2_1[-550444752 ^ -550444752] = 1995179625 ^ 1995179595;
                            v6 = new String(var2_1, "UTF-8");
                            v7 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                            if (true) ** GOTO lbl59
                        }
                        v4 = -1250338166 ^ 1802883725;
                    }
                    block7: while (true) {
                        v7 = v8 / (3374481124649518215L == 3374481124649518216L ? 9182487823411076733L : -2430106157017199678L ^ -5341744868568419134L);
lbl59:
                        // 2 sources

                        switch ((int)v7) {
                            case -876892595: {
                                break block7;
                            }
                            case 1118108145: {
                                if (-8862112551745920511L == -8862112551745920510L) {
                                    v8 = -4921954612782772256L;
                                    continue block7;
                                }
                                v8 = 1680150405337727816L - 7851781458737911441L;
                                continue block7;
                            }
                        }
                        break;
                    }
                    v9 = v5.append(v6);
                    while (true) {
                        if ((v10 = (cfr_temp_3 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (5794184089582750896L - -7706808351293830859L)) == 0L ? 0 : (cfr_temp_3 < 0L ? -1 : 1)) == false) {
                            continue;
                        }
                        if (v10 == (381303123 ^ 381303122)) {
                            v11 = v9.append(CRACKME_aff93d50_6085_4e90_9112_785ad203ec9bc9b8366e);
                            if (4280933694889217237L == 4280933694889217238L) {
                                break;
                            }
                            break block25;
                        }
                        v10 = -170861814 ^ -1238950465;
                    }
                    v12 = 872196013;
                    break block28;
                }
                v12 = 1646644441 ^ 1646644440;
            }
            var2_1 = new byte[v12];
            var2_1[540847332 ^ 540847332] = -1471942230 ^ -1471942264;
            v13 = v11.append(new String(var2_1, "UTF-8"));
            while (true) {
                if ((v14 = (cfr_temp_4 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-3610432125453908412L - 985912669567563740L)) == 0L ? 0 : (cfr_temp_4 < 0L ? -1 : 1)) == false) {
                    continue;
                }
                if (v14 == (418724213 ^ -418724214)) {
                    v2 = v13.toString();
                    break;
                }
                v14 = -503458614 ^ 65166001;
            }
        }
        return v2;
    }

    /*
     * Handled impossible loop by adding 'first' condition
     */
    private static /* synthetic */ String PRryBTP2sBIwodDV(String string) {
        String string2;
        String CRACKME_d938f399_f95e_4e9e_ab4e_9cec8e5b45b07eda3290;
        if (CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == (0x591DB0274950D2BCL ^ 0x394E50E00FF458F6L)) {
            int cfr_ignored_2 = 0x952D124E ^ 0x952D124F;
        } else {
            int cfr_ignored_3 = 0x65694CC5 ^ 0x65694CC5;
        }
        byte[] byArray = new byte[0x423F321A ^ 0x423F321B];
        int n = 0xC861C57E ^ 0xC861C55E;
        byArray[0x1825E1B4 ^ 0x1825E1B4] = n;
        String string3 = new String(byArray, "UTF-8");
        long l = CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
        boolean bl = true;
        block10: while (true) {
            long l2;
            if (!bl || (bl = false) || !true) {
                l = l2 / (0x52267F758EFCAA6EL ^ 0x98A4D250F2DB45E0L);
            }
            switch ((int)l) {
                case -876892595: {
                    break block10;
                }
                case -514742393: {
                    l2 = 0x6E3B8B18EB31A4DAL ^ 0x7527A5DDA1230F1DL;
                    continue block10;
                }
                case 854940832: {
                    l2 = 0xA59F5283D3236ECAL ^ 0xC73C1D432A7A9309L;
                    continue block10;
                }
                case 1282101929: {
                    l2 = -2775835436181102250L - -3510356262182332295L;
                    continue block10;
                }
            }
            break;
        }
        if (!CRACKME_d938f399_f95e_4e9e_ab4e_9cec8e5b45b07eda3290.contains(string3)) {
            string2 = CRACKME_d938f399_f95e_4e9e_ab4e_9cec8e5b45b07eda3290;
            return string2;
        }
        long l3 = CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
        boolean bl2 = true;
        block11: while (true) {
            long l4;
            if (!bl2 || (bl2 = false) || !true) {
                l3 = l4 / (0xB7C86108863137D3L ^ 0x7CC7A91E5CA715A6L);
            }
            switch ((int)l3) {
                case -876892595: {
                    break block11;
                }
                case 1808204281: {
                    l4 = 0x22D85DD1C23089AAL ^ 0xC4D47B4C150164EDL;
                    continue block11;
                }
            }
            break;
        }
        byArray = new byte[0x8BB4110D ^ 0x8BB4110C];
        byArray[0xC06BF14 ^ 0xC06BF14] = 0x8CEC7657 ^ 0x8CEC7675;
        String string4 = new String(byArray, "UTF-8");
        byArray = new byte[0x5B1F24E2 ^ 0x5B1F24E3];
        byArray[0x9805D6C1 ^ 0x9805D6C1] = 0xC873ADC6 ^ 0xC873ADE4;
        string2 = string4 + CRACKME_d938f399_f95e_4e9e_ab4e_9cec8e5b45b07eda3290 + new String(byArray, "UTF-8");
        return string2;
    }

    /*
     * Unable to fully structure code
     */
    private static String aod8LSCXf7hspjjh(String var0) {
        block48: {
            if (\u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == (-5893352850519981007L ^ 7346419998773727822L)) {
                if ((-1903543627 ^ -844222650 ^ (1132922678 ^ 1014560969)) != 0) {
                    -1580976092 ^ -1580976091;
                }
            } else {
                927276905 ^ 927276905;
            }
            CRACKME_cfbaed8e_fdb4_4702_9d6d_4d7efb71a3d0a6ffb062 = CRACKME_cfbaed8e_fdb4_4702_9d6d_4d7efb71a3d0f9f7fecf;
            var3_2 = new byte[1544881886 ^ 1544881887];
            var3_2[1537701888 ^ 1537701888] = -305607965 ^ -305607999;
            v0 = new String(var3_2, "UTF-8");
            while (true) {
                if ((v1 = (cfr_temp_0 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-3671744092810477396L >>> "\u0000\u0000".length())) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) {
                    continue;
                }
                if (v1 == 69544740 - 69544741) {
                    if (CRACKME_cfbaed8e_fdb4_4702_9d6d_4d7efb71a3d0f9f7fecf.startsWith(v0)) {
                        break;
                    }
                    break block48;
                }
                v1 = -1532091481 ^ 1576894744;
            }
            var3_2 = new byte[-7914145522082352204L == -7914145522082352203L ? 215815538 : 442313524 ^ 442313525];
            var3_2[2671564737792516486L == 2671564737792516487L ? -79437543 : -380306515 ^ -380306515] = -292317657 ^ -292317691;
            v2 = new String(var3_2, "UTF-8");
            while (true) {
                if ((v3 = (cfr_temp_1 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-8226233239572613811L ^ 6963246038512353162L)) == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1)) == false) {
                    continue;
                }
                if (v3 == 1347744641 - 1347744642) {
                    if (CRACKME_cfbaed8e_fdb4_4702_9d6d_4d7efb71a3d0f9f7fecf.endsWith(v2)) {
                        break;
                    }
                    break block48;
                }
                v3 = 800811055 ^ -939584271;
            }
            v4 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
            block26: while (true) {
                switch ((int)v4) {
                    case -876892595: {
                        break block26;
                    }
                    case 1182321133: {
                        v4 = (4237291997192101987L ^ -4940794036253328487L) / (-1133987639734610866L ^ 6726074789640879820L);
                        continue block26;
                    }
                }
                break;
            }
            if (CRACKME_cfbaed8e_fdb4_4702_9d6d_4d7efb71a3d0f9f7fecf.length() <= (-856144468 ^ -856144467)) break block48;
            -471083370 ^ -1143903672;
            v5 = -4671050399051827069L == -4671050399051827068L ? -341495702 : 1237819099 - -675243405;
            v6 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
            if (true) ** GOTO lbl68
            block27: while (true) {
                v6 = v7 / (-7501137958039486046L ^ -8576744816883394707L);
lbl68:
                // 2 sources

                switch ((int)v6) {
                    case -1203839054: {
                        if (6185860590156963513L == 6185860590156963514L) {
                            v7 = -5178229004265401032L;
                            continue block27;
                        }
                        v7 = 3239214157466747716L ^ -6602477847186729998L;
                        continue block27;
                    }
                    case -876892595: {
                        break block27;
                    }
                    case -36546359: {
                        v7 = 3342847880478917959L ^ -2436931236166173607L;
                        continue block27;
                    }
                }
                break;
            }
            CRACKME_cfbaed8e_fdb4_4702_9d6d_4d7efb71a3d0a6ffb062 = CRACKME_cfbaed8e_fdb4_4702_9d6d_4d7efb71a3d0f9f7fecf.substring(158741260 ^ 158741261, CRACKME_cfbaed8e_fdb4_4702_9d6d_4d7efb71a3d0f9f7fecf.length() - (136359967 ^ 136359966));
        }
        var3_2 = new byte[-3945837181437164728L == -3945837181437164727L ? 1899966423 : 1029497524 ^ 1029497525];
        var3_2[-7473303263663762L == -7473303263663761L ? 1580807272 : -2025145453 ^ -2025145453] = -1814310859 ^ -1814310885;
        v8 = new String(var3_2, "UTF-8");
        558739529 - -1365784899;
        -1103537832 >>> "\u0000\u0000".length();
        var3_2 = new byte[-831170485 ^ -831170488];
        var3_2[-16673515 ^ -16673513] = 8790875914277437594L == 8790875914277437595L ? 1703458230 : 710340941 ^ 710340915;
        var3_2[14217467 ^ 14217466] = -2001240458 ^ -2001240507;
        var3_2[1460271225 ^ 1460271225] = 2143256375 ^ 2143256393;
        v9 = new String(var3_2, "UTF-8");
        while (true) {
            block49: {
                if ((v10 = (cfr_temp_2 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-6889280101093700400L == -6889280101093700399L ? 5728683450118857645L : -5878553834997644613L ^ -5750838990776998507L)) == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1)) == false) {
                    continue;
                }
                if (v10 != 1523575484 - 1523575485) break block49;
                CRACKME_cfbaed8e_fdb4_4702_9d6d_4d7efb71a3d0a6ffb062 = CRACKME_cfbaed8e_fdb4_4702_9d6d_4d7efb71a3d0a6ffb062.replace(v8, v9);
                v11 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                if (true) ** GOTO lbl122
            }
            if (3918914280138953066L == 3918914280138953067L) {
                v10 = -2067178878;
                continue;
            }
            v10 = 412844348 >>> "\u0000\u0000".length();
        }
        block29: while (true) {
            v11 = v12 / (-6031848691519262619L ^ 3966698319259051245L);
lbl122:
            // 2 sources

            switch ((int)v11) {
                case -1844370155: {
                    if (-615785823055049503L == -615785823055049502L) {
                        v12 = 8964421214812842610L;
                        continue block29;
                    }
                    v12 = 717682404947560277L - 4785133259888886820L;
                    continue block29;
                }
                case -876892595: {
                    break block29;
                }
                case 2112701977: {
                    v12 = -1825490192551805870L ^ -4052755136825774607L;
                    continue block29;
                }
            }
            break;
        }
        v13 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
        if (true) ** GOTO lbl139
        block30: while (true) {
            v13 = v14 / (7495374679920550513L - -1714563720856542210L);
lbl139:
            // 2 sources

            switch ((int)v13) {
                case -876892595: {
                    break block30;
                }
                case 23418684: {
                    if (-8966734817853495967L == -8966734817853495966L) {
                        v14 = 2560274813838558271L;
                        continue block30;
                    }
                    v14 = 1632258588242465687L - -4627438226457139665L;
                    continue block30;
                }
                case 1879982021: {
                    if (7144564845849999170L == 7144564845849999171L) {
                        v14 = -2744767967680619491L;
                        continue block30;
                    }
                    v14 = -4755315963040479728L - -5010988832263156166L;
                    continue block30;
                }
            }
            break;
        }
        v15 = \u0106s9V.Z1X8.matcher(CRACKME_cfbaed8e_fdb4_4702_9d6d_4d7efb71a3d0a6ffb062);
        var3_2 = new byte[-1705826813311806470L == -1705826813311806469L ? -1821266583 : 1059677622 ^ 1059677622];
        v16 = new String(var3_2, "UTF-8");
        v17 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
        if (true) ** GOTO lbl165
        block31: while (true) {
            v17 = v18 / (6768281421414713326L ^ -3532546928251605177L);
lbl165:
            // 2 sources

            switch ((int)v17) {
                case -876892595: {
                    break block31;
                }
                case 827670035: {
                    v18 = -5297286615228071533L ^ 4520631810814307038L;
                    continue block31;
                }
                case 1453550488: {
                    if (-441428609037490955L == -441428609037490954L) {
                        v18 = 8398968845324855915L;
                        continue block31;
                    }
                    v18 = 4823960443224541738L - -8908514174459103081L;
                    continue block31;
                }
            }
            break;
        }
        return v15.replaceAll(v16);
    }

    /*
     * Handled impossible loop by adding 'first' condition
     * WARNING - void declaration
     */
    private static /* synthetic */ boolean -0mdu9RBZBW094fb(String[] stringArray, String string) {
        String[] CRACKME_936558ca_3f9d_4bf3_967c_4b975765b1e7d2b81a62;
        void CRACKME_936558ca_3f9d_4bf3_967c_4b975765b1e70cf07dca;
        if (CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == (0x955C1E4A15309C61L ^ 0x309837083F43F24AL)) {
            int n = -1402482620 - 2071031265;
            if ((n ^ (0xFFCF3168 ^ 0x8030CE97)) != 0) {
                int cfr_ignored_2 = 0x943D36B4 ^ 0x943D36B5;
            }
        } else {
            int n = 0xB8928F1A ^ 0xB8928F1A;
        }
        int n = 390687858 - 1592622523;
        String string2 = CRACKME_936558ca_3f9d_4bf3_967c_4b975765b1e70cf07dca.toLowerCase();
        int cfr_ignored_3 = 211642979 - 264641828;
        String string3 = CRACKME_936558ca_3f9d_4bf3_967c_4b975765b1e7d2b81a62[0x3FDD5128 ^ 0x3FDD512B];
        long l = CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
        boolean bl = true;
        block4: while (true) {
            long l2;
            if (!bl || (bl = false) || !true) {
                l = (0x82B2EDB16E1F780EL ^ 0x1E77C1E37E8CABFBL) / l2;
            }
            switch ((int)l) {
                case -1744862508: {
                    l2 = 0x83EED2297E4CAF14L ^ 0x40D191CDB5D8156FL;
                    continue block4;
                }
                case -876892595: {
                    break block4;
                }
            }
            break;
        }
        String string4 = string3.toLowerCase();
        while (true) {
            long l3;
            long l4;
            if ((l4 = (l3 = CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (0xF6B6BABC9B1CC1DBL ^ 0x48BC41B64B8F8F3CL)) == 0L ? 0 : (l3 < 0L ? -1 : 1)) == false) {
                continue;
            }
            int n2 = 0x10BB334C ^ 0x10BB334D;
            if (l4 == n2) {
                return string2.startsWith(string4);
            }
            l4 = 0xCE19AD0A ^ 0x4B706BC5;
        }
    }

    private static /* synthetic */ String GOUs0rxkbuLIMLv0(\u00d3vR\u017a \u00f3vR\u017a) {
        if (CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == (0x7129A25D9EB1E8C7L ^ 0x7480A197E995B163L)) {
            if ((0x67AED64 ^ 0xF02E3ECC ^ (0x658081E ^ 0x79A7F7E1)) != 0) {
                int n = 5030368610447442955L == 5030368610447442956L ? 1735492031 : 0x787A40C8 ^ 0x787A40C9;
            }
        } else {
            int cfr_ignored_1 = 0x7724BEAB ^ 0x7724BEAB;
        }
        int cfr_ignored_2 = 0xB18BF0D2 ^ 0x3BF37B48;
        int cfr_ignored_3 = 0xC93471C ^ 0x800C0B1B;
        int n = 3790617848379697401L == 3790617848379697402L ? 1786780593 : 0x35A29EB4 ^ 0xCD4F8893;
        while (true) {
            long l;
            long l2;
            if ((l2 = (l = CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-8477734065522818720L >>> "\u0000\u0000".length())) == 0L ? 0 : (l < 0L ? -1 : 1)) == false) {
                continue;
            }
            if (l2 == (0x2210F08B ^ 0xDDEF0F74)) {
                \u00d3vR\u017a CRACKME_7e2a568e_f210_49dc_8e7f_970cc2d9c398723a0f2c;
                return CRACKME_7e2a568e_f210_49dc_8e7f_970cc2d9c398723a0f2c.\u0142W\u015aq;
            }
            l2 = 224459678 - 2097645270;
        }
    }

    /*
     * Unable to fully structure code
     */
    private static /* synthetic */ String kVHeHzKl5sfJmyTA(String var0) {
        block27: {
            block26: {
                if (\u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == (-7536764962425776119L ^ 684840267661860526L)) {
                    if ((-1199034677 ^ -412450645 ^ 799706742 - -1347776905) != 0) {
                        v0 = -2043136599694880441L == -2043136599694880440L ? 1801302329 : -2068677194 ^ -2068677193;
                    }
                } else {
                    -756623164 ^ -756623164;
                }
                var2_1 = new byte[435293219 ^ 435293218];
                var2_1[8081771225919727250L == 8081771225919727251L ? -1533081047 : -315562724 ^ -315562724] = -1389768427 ^ -1389768395;
                if (CRACKME_519118e0_fbe8_4b32_8574_3cf719cf40855ac2b70a.contains(new String(var2_1, "UTF-8"))) break block26;
                v1 = CRACKME_519118e0_fbe8_4b32_8574_3cf719cf40855ac2b70a;
                break block27;
            }
            while (true) {
                block28: {
                    if ((v2 = (cfr_temp_0 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (1903789303481548791L ^ -409312563855770350L)) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) {
                        continue;
                    }
                    if (v2 != 1209421652 - 1209421653) break block28;
                    var2_1 = new byte[791855187 ^ 791855186];
                    var2_1[1207110602 ^ 1207110602] = 1186459087 ^ 1186459117;
                    v3 = new StringBuilder().append(new String(var2_1, "UTF-8"));
                    v4 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                    if (true) ** GOTO lbl40
                }
                v2 = 570515635 ^ -927224008;
            }
            block17: while (true) {
                v4 = v5 / (8284336447045813078L ^ -349961874781671032L);
lbl40:
                // 2 sources

                switch ((int)v4) {
                    case -1450408714: {
                        v5 = 4138448204988544586L - -499745324544819747L;
                        continue block17;
                    }
                    case -876892595: {
                        break block17;
                    }
                    case 569616302: {
                        v5 = 8948315563622651652L ^ 3984141702314701005L;
                        continue block17;
                    }
                }
                break;
            }
            v6 = v3.append(CRACKME_519118e0_fbe8_4b32_8574_3cf719cf40855ac2b70a);
            var2_1 = new byte[-1964627613 ^ -1964627614];
            var2_1[-691240297 ^ -691240297] = 768524958 ^ 768524988;
            v7 = new String(var2_1, "UTF-8");
            v8 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
            if (true) ** GOTO lbl64
            block18: while (true) {
                v8 = v9 / (5426166726361260398L - 6846745114832526436L);
lbl64:
                // 2 sources

                switch ((int)v8) {
                    case -1593503074: {
                        v9 = 7736956476992752489L - 99759542540798877L;
                        continue block18;
                    }
                    case -876892595: {
                        break block18;
                    }
                    case -157805758: {
                        v9 = 8173971678309192661L ^ 3910796635291158562L;
                        continue block18;
                    }
                }
                break;
            }
            v10 = v6.append(v7);
            v11 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
            if (true) ** GOTO lbl84
            block19: while (true) {
                v11 = v12 / (5509481057411091199L == 5509481057411091200L ? -1956814143987314058L : 713639268273303850L - 6743409514251312324L);
lbl84:
                // 2 sources

                switch ((int)v11) {
                    case -1804078285: {
                        v12 = -6922327033522965396L - -9019135301366047925L;
                        continue block19;
                    }
                    case -1479001142: {
                        if (-1307244279928944192L == -1307244279928944191L) {
                            v12 = 318066434757093354L;
                            continue block19;
                        }
                        v12 = -4889793822831739477L ^ -3529644070055546294L;
                        continue block19;
                    }
                    case -876892595: {
                        break block19;
                    }
                    case 855793239: {
                        if (-603048625543199644L == -603048625543199643L) {
                            v12 = -771474010813390230L;
                            continue block19;
                        }
                        v12 = 5217960505510600392L ^ 8236056630807273488L;
                        continue block19;
                    }
                }
                break;
            }
            v1 = v10.toString();
        }
        return v1;
    }

    /*
     * WARNING - void declaration
     */
    private static /* synthetic */ boolean m-Z-RpVz5A_rAAnW(String string, String string2) {
        String CRACKME_8246ded3_c8b3_4372_83ef_613aa5b8c4fa991cef9e;
        void CRACKME_8246ded3_c8b3_4372_83ef_613aa5b8c4facce23c43;
        if (CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == 4766781750349984535L - 5799672922509672011L) {
            if ((0x572C7599 ^ 0x17DDD0F0 ^ (844681118418919785L == 844681118418919786L ? 1660830016 : 0x63044F3A ^ 0x1CFBB0C5)) != 0) {
                int n = -4427574452565381919L == -4427574452565381918L ? 225450078 : 0x7DC9DE17 ^ 0x7DC9DE16;
            }
        } else {
            int cfr_ignored_1 = 0x13015403 ^ 0x13015403;
        }
        String string3 = CRACKME_8246ded3_c8b3_4372_83ef_613aa5b8c4facce23c43.toLowerCase();
        long l = CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
        block4: while (true) {
            switch ((int)l) {
                case -876892595: {
                    break block4;
                }
                case 41179195: {
                    l = (5121014539171427634L - 9197237601830092344L) / (-5231204441507967871L - -4600828564243173465L);
                    continue block4;
                }
            }
            break;
        }
        String string4 = CRACKME_8246ded3_c8b3_4372_83ef_613aa5b8c4fa991cef9e.toLowerCase();
        int cfr_ignored_2 = 0x142E7A6 ^ 0xD1CCE9EB;
        while (true) {
            long l2;
            long l3;
            if ((l3 = (l2 = CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-7894031769080261393L == -7894031769080261392L ? 957003468909209831L : 0xA1F7F3DF8648A78DL ^ 0xD59005F3C7BF386L)) == 0L ? 0 : (l2 < 0L ? -1 : 1)) == false) {
                continue;
            }
            if (l3 == (-7429871838787411263L == -7429871838787411262L ? 1902988564 : 0xE045501D ^ 0xE045501C)) {
                return string3.startsWith(string4);
            }
            l3 = 0xB4CC9676 ^ 0x4FE4371C;
        }
    }

    /*
     * Handled impossible loop by adding 'first' condition
     */
    private static String phvIyAWdJNBKcWaD(String string) {
        String CRACKME_f1e43a66_f390_4022_9884_fac62840879ed83eaa04;
        if (CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == (0xD43EFC66C83C63C5L ^ 0x1574340A4D29BB0L)) {
            int cfr_ignored_1 = 0xA5A7BFE8 ^ 0xA5A7BFE9;
        } else {
            int n = 0x47F4FE35 ^ 0x47F4FE35;
        }
        int n = 2044478916 >>> "\u0000\u0000".length();
        int n2 = 0x98F5250D ^ 0x98F5250E;
        byte[] byArray = new byte[n2];
        byArray[0x9BFB6552 ^ 0x9BFB6552] = 0xABE94C19 ^ 0xABE94C67;
        byArray[0x546BA155 ^ 0x546BA154] = 0xC029DF3 ^ 0xC029DC0;
        byArray[0xA7A4474D ^ 0xA7A4474F] = 0xB624029B ^ 0xB62402E5;
        String string2 = new String(byArray, "UTF-8");
        int cfr_ignored_2 = -874297636 - 1169910697;
        byArray = new byte[0x5CBD62CA ^ 0x5CBD62CB];
        int n3 = 0x4197860C ^ 0x4197860C;
        byArray[n3] = 0xD5B5EF45 ^ 0xD5B5EF6B;
        String string3 = new String(byArray, "UTF-8");
        long l = CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
        boolean bl = true;
        block4: while (true) {
            long l2;
            if (!bl || (bl = false) || !true) {
                l = (0x1AA5E014C9E22ACL ^ 0xFE2D0D8B5C524B56L) / l2;
            }
            switch ((int)l) {
                case -876892595: {
                    return CRACKME_f1e43a66_f390_4022_9884_fac62840879ed83eaa04.replace(string2, string3);
                }
                case 398606084: {
                    l2 = 0xEEEFC5D70AF7D340L ^ 0x94C7C84990CBA60DL;
                    continue block4;
                }
            }
            break;
        }
        return CRACKME_f1e43a66_f390_4022_9884_fac62840879ed83eaa04.replace(string2, string3);
    }

    /*
     * Handled impossible loop by adding 'first' condition
     */
    private static /* synthetic */ String Dm15ybQEJfOIVAvo(String string) {
        String CRACKME_b4920a6c_fc05_4fc8_8e4d_9e54b096811fb7eb35ab;
        if (CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == -4934692046119863421L - 7147884153860709442L) {
            if ((1751704080 >>> "\u0000\u0000".length() ^ 400197374 - -1747286273) != 0) {
                int cfr_ignored_1 = 0x84DC9699 ^ 0x84DC9698;
            }
        } else {
            int cfr_ignored_2 = 0xDED90ECC ^ 0xDED90ECC;
        }
        long l = CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
        boolean bl = true;
        block5: while (true) {
            long l2;
            if (!bl || (bl = false) || !true) {
                l = l2 / (0x764C806564F55D5FL ^ 0xC727AA98ADB84FE0L);
            }
            switch ((int)l) {
                case -876892595: {
                    return \u0106s9V.phvIyAWdJNBKcWaD(CRACKME_b4920a6c_fc05_4fc8_8e4d_9e54b096811fb7eb35ab);
                }
                case -260939402: {
                    l2 = 0x2F861781062BFAABL ^ 0xEF05559CB33C61BFL;
                    continue block5;
                }
                case 1624143275: {
                    l2 = 5700189466057183167L - -6005252588660041599L;
                    continue block5;
                }
            }
            break;
        }
        return \u0106s9V.phvIyAWdJNBKcWaD(CRACKME_b4920a6c_fc05_4fc8_8e4d_9e54b096811fb7eb35ab);
    }

    /*
     * Unable to fully structure code
     */
    public List<String> onTabComplete(@NotNull CommandSender var1_1, @NotNull Command var2_2, @NotNull String var3_3, @NotNull String[] var4_4) {
        block308: {
            block337: {
                block340: {
                    block335: {
                        block336: {
                            block334: {
                                block323: {
                                    block321: {
                                        block329: {
                                            block307: {
                                                block322: {
                                                    block325: {
                                                        block327: {
                                                            block326: {
                                                                block319: {
                                                                    block314: {
                                                                        block320: {
                                                                            block316: {
                                                                                block313: {
                                                                                    block312: {
                                                                                        block311: {
                                                                                            block310: {
                                                                                                block309: {
                                                                                                    if (\u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == (5284674832234680655L ^ -5071648413458704600L)) {
                                                                                                        v0 = -28229868 ^ -28229867;
                                                                                                    } else {
                                                                                                        -392229727 ^ -392229727;
                                                                                                    }
                                                                                                    v1 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                                    if (true) ** GOTO lbl18
                                                                                                    block210: while (true) {
                                                                                                        v1 = v2 / (7280510151678957005L ^ -5266939143067742299L);
lbl18:
                                                                                                        // 2 sources

                                                                                                        switch ((int)v1) {
                                                                                                            case -1763555572: {
                                                                                                                v2 = 241034366605843843L - -1884905831555447580L;
                                                                                                                continue block210;
                                                                                                            }
                                                                                                            case -1750451317: {
                                                                                                                v2 = -502356174331314242L ^ 7069505990291081568L;
                                                                                                                continue block210;
                                                                                                            }
                                                                                                            case -876892595: {
                                                                                                                break block210;
                                                                                                            }
                                                                                                            case 290990858: {
                                                                                                                v2 = 8973155118531808961L - 7456638146318208563L;
                                                                                                                continue block210;
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                    }
                                                                                                    var14_5 = new byte[558057197 ^ 558057186];
                                                                                                    var14_5[424402127 ^ 424402122] = -113308520 ^ -113308426;
                                                                                                    var14_5[929537256 ^ 929537257] = -2118299337 ^ -2118299324;
                                                                                                    var14_5[-1371039735 ^ -1371039742] = 1832937567 ^ 1832937515;
                                                                                                    v3 = 159580784 ^ 159580794;
                                                                                                    var14_5[v3] = 481288899 ^ 481288866;
                                                                                                    var14_5[974601544 ^ 974601544] = -286768429 ^ -286768474;
                                                                                                    v4 = -1804699951 ^ -1804700008;
                                                                                                    var14_5[-448343610 ^ -448343614] = v4;
                                                                                                    var14_5[848908692 ^ 848908700] = -987289082 ^ -987288972;
                                                                                                    v5 = -1280564581 ^ -1280564483;
                                                                                                    var14_5[1006915181 ^ 1006915179] = v5;
                                                                                                    var14_5[-290932537 ^ -290932534] = -2069576593 ^ -2069576704;
                                                                                                    var14_5[1807899417 ^ 1807899419] = 1832757514 ^ 1832757615;
                                                                                                    v6 = -930560095 ^ -930560049;
                                                                                                    var14_5[-767210211 ^ -767210221] = v6;
                                                                                                    var14_5[796900610 ^ 796900619] = -1993819604 ^ -1993819583;
                                                                                                    v7 = -1507254669 ^ -1507254668;
                                                                                                    v8 = -264624162 ^ -264624207;
                                                                                                    var14_5[v7] = v8;
                                                                                                    var14_5[57568797 ^ 57568785] = -1379335604 ^ -1379335643;
                                                                                                    v9 = -1465311488 ^ -1465311485;
                                                                                                    v10 = -1054018620 ^ -1054018634;
                                                                                                    var14_5[v9] = v10;
                                                                                                    var5_6 = \u017b\u017c\u0107m.7\u015aCz.getConfigurationSection(new String(var14_5, "UTF-8"));
                                                                                                    v11 = 1530424178 ^ -1801642862;
                                                                                                    if (((void)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c).length != (-510579962 ^ -510579961)) break block309;
                                                                                                    v12 = -1856726860 ^ -1856726860;
                                                                                                    v13 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[v12];
                                                                                                    v14 = new String[-530772921 ^ -530772913];
                                                                                                    var14_5 = new byte[-1842485560 ^ -1842485554];
                                                                                                    var14_5[2139606539 ^ 2139606536] = 1819885877 ^ 1819885914;
                                                                                                    var14_5[886596752 ^ 886596757] = 606018830 ^ 606018922;
                                                                                                    v15 = -1497362394 ^ -1497362365;
                                                                                                    var14_5[1632333144 ^ 1632333145] = v15;
                                                                                                    var14_5[-1599995833 ^ -1599995833] = 141417165 ^ 141417151;
                                                                                                    var14_5[-1596877797 ^ -1596877799] = 763100291 ^ 763100399;
                                                                                                    var14_5[225939300 ^ 225939296] = -1740379624 ^ -1740379527;
                                                                                                    718621967 ^ -1171241185;
                                                                                                    v14[-1466190864 ^ -1466190864] = new String(var14_5, "UTF-8");
                                                                                                    -1012364484 ^ -1308095063;
                                                                                                    v16 = 1729891247 ^ 1729891246;
                                                                                                    var14_5 = new byte[1602576366 ^ 1602576362];
                                                                                                    var14_5[-998187217 ^ -998187219] = 2087921293 ^ 2087921387;
                                                                                                    var14_5[962936619 ^ 962936619] = -171791130 ^ -171791217;
                                                                                                    var14_5[-386165043 ^ -386165044] = -751009071 ^ -751009089;
                                                                                                    v17 = 574199992 ^ 574199995;
                                                                                                    var14_5[v17] = -848278984 ^ -848278953;
                                                                                                    v14[v16] = new String(var14_5, "UTF-8");
                                                                                                    v18 = 339157630 ^ 1339106955;
                                                                                                    var14_5 = new byte[-1540825242 ^ -1540825248];
                                                                                                    v19 = 209978194 ^ 209978195;
                                                                                                    var14_5[v19] = 1385092606 ^ 1385092497;
                                                                                                    var14_5[511663119 ^ 511663114] = 1713250557 ^ 1713250458;
                                                                                                    v20 = -1951859696 ^ -1951859694;
                                                                                                    v21 = -1907977422 ^ -1907977380;
                                                                                                    var14_5[v20] = v21;
                                                                                                    var14_5[105063745 ^ 105063745] = -1882139750 ^ -1882139655;
                                                                                                    var14_5[674677033 ^ 674677034] = 633345400 ^ 633345310;
                                                                                                    v22 = -91782587 ^ -91782591;
                                                                                                    var14_5[v22] = 853549551 ^ 853549446;
                                                                                                    v23 = 1251011269 - -710539609;
                                                                                                    v14[1228295739 ^ 1228295737] = new String(var14_5, "UTF-8");
                                                                                                    -586657697 ^ 2019784790;
                                                                                                    1122250947 ^ -1861610310;
                                                                                                    v24 = -1397312813 ^ -1397312816;
                                                                                                    -750153552 - 576057125;
                                                                                                    v25 = 238248685 ^ 238248673;
                                                                                                    var14_5 = new byte[v25];
                                                                                                    v26 = 1228240936 ^ 1228240931;
                                                                                                    var14_5[v26] = -1775459880 ^ -1775459914;
                                                                                                    var14_5[1092250134 ^ 1092250142] = -1465757959 ^ -1465758067;
                                                                                                    var14_5[1957054272 ^ 1957054274] = -422904909 ^ -422904889;
                                                                                                    v27 = -2141595086 ^ -2141595083;
                                                                                                    var14_5[v27] = -1431728050 ^ -1431728081;
                                                                                                    v28 = 822545968 ^ 822545973;
                                                                                                    var14_5[v28] = -1507328182 ^ -1507328219;
                                                                                                    v29 = 1098093292 ^ 1098093184;
                                                                                                    var14_5[1317991965 ^ 1317991961] = v29;
                                                                                                    var14_5[-2014619196 ^ -2014619196] = 1921076556 ^ 1921076543;
                                                                                                    v30 = 373617461 ^ 373617498;
                                                                                                    var14_5[986195257 ^ 986195251] = v30;
                                                                                                    v31 = 1512744272 ^ 1512744249;
                                                                                                    var14_5[2023202326 ^ 2023202335] = v31;
                                                                                                    v32 = 698881686 ^ 698881723;
                                                                                                    var14_5[-1098808128 ^ -1098808125] = v32;
                                                                                                    var14_5[-1513311882 ^ -1513311888] = -691181011 ^ -691180978;
                                                                                                    var14_5[561210386 ^ 561210387] = 1290377597 ^ 1290377496;
                                                                                                    v14[v24] = new String(var14_5, "UTF-8");
                                                                                                    v33 = 655453531 ^ 655453535;
                                                                                                    v34 = 1960990306 ^ 1960990309;
                                                                                                    var14_5 = new byte[v34];
                                                                                                    var14_5[1110258963 ^ 1110258960] = 337884893 ^ 337884860;
                                                                                                    var14_5[72432048 ^ 72432054] = 824931589 ^ 824931680;
                                                                                                    var14_5[-481054970 ^ -481054972] = 91909729 ^ 91909650;
                                                                                                    v35 = -2046764493 ^ -2046764463;
                                                                                                    var14_5[-1242827426 ^ -1242827430] = v35;
                                                                                                    var14_5[-293408251 ^ -293408251] = -1664175791 ^ -1664175819;
                                                                                                    var14_5[-811139659 ^ -811139664] = 1375306008 ^ 1375306100;
                                                                                                    v36 = 1887687632 ^ 1887687633;
                                                                                                    var14_5[v36] = -1494312198 ^ -1494312301;
                                                                                                    v14[v33] = new String(var14_5, "UTF-8");
                                                                                                    var14_5 = new byte[-1091344401 ^ -1091344407];
                                                                                                    var14_5[1475871847 ^ 1475871842] = -1259224903 ^ -1259224868;
                                                                                                    var14_5[-1336554982 ^ -1336554981] = -654621130 ^ -654621096;
                                                                                                    var14_5[435986204 ^ 435986200] = -171107719 ^ -171107819;
                                                                                                    var14_5[-1632358392 ^ -1632358390] = 604197704 ^ 604197673;
                                                                                                    v37 = -1083038608 ^ -1083038608;
                                                                                                    var14_5[v37] = 792418783 ^ 792418746;
                                                                                                    var14_5[-598876045 ^ -598876048] = -850612073 ^ -850611979;
                                                                                                    v14[845251034 ^ 845251039] = new String(var14_5, "UTF-8");
                                                                                                    v38 = 2020711402 ^ 2020711407;
                                                                                                    var14_5 = new byte[v38];
                                                                                                    v39 = 1202481287 ^ 1202481287;
                                                                                                    var14_5[v39] = 772636775 ^ 772636678;
                                                                                                    v40 = -598286501 ^ -598286542;
                                                                                                    var14_5[1789994137 ^ 1789994138] = v40;
                                                                                                    var14_5[-1363127760 ^ -1363127756] = 373825383 ^ 373825289;
                                                                                                    var14_5[990123189 ^ 990123191] = 2069891938 ^ 2069891855;
                                                                                                    var14_5[-683027739 ^ -683027740] = 1826201682 ^ 1826201654;
                                                                                                    v14[1094049705 ^ 1094049711] = new String(var14_5, "UTF-8");
                                                                                                    -424100097 - 1422754065;
                                                                                                    v41 = 2034025680 ^ 2034025687;
                                                                                                    var14_5 = new byte[105628336 ^ 105628340];
                                                                                                    v42 = -1440635205 ^ -1440635205;
                                                                                                    var14_5[v42] = -1576233246 ^ -1576233334;
                                                                                                    var14_5[-1290819271 ^ -1290819269] = 887347846 ^ 887347946;
                                                                                                    var14_5[-2007755693 ^ -2007755696] = -1343435388 ^ -1343435285;
                                                                                                    v43 = -658274407 ^ -658274314;
                                                                                                    var14_5[-945815894 ^ -945815893] = v43;
                                                                                                    -2074106962 - 2027160443;
                                                                                                    v14[v41] = new String(var14_5, "UTF-8");
                                                                                                    -1526730023 - -222089288;
                                                                                                    v44 = Arrays.asList(v14);
                                                                                                    v45 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                                    if (true) ** GOTO lbl259
                                                                                                }
                                                                                                if (((void)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c).length != (-1997066022 ^ -1997066024)) break block310;
                                                                                                v46 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[-1449865613 ^ -1449865613];
                                                                                                var14_5 = new byte[-2078335632 ^ -2078335626];
                                                                                                var14_5[-1546044120 ^ -1546044120] = -1436325458 ^ -1436325427;
                                                                                                v47 = 1672110458 ^ 1672110459;
                                                                                                v48 = -1532491346 ^ -1532491327;
                                                                                                var14_5[v47] = v48;
                                                                                                v49 = 2009610806 ^ 2009610803;
                                                                                                v50 = 2120339847 ^ 2120339936;
                                                                                                var14_5[v49] = v50;
                                                                                                v51 = 1074607139 ^ 1074607137;
                                                                                                var14_5[v51] = -960340099 ^ -960340205;
                                                                                                v52 = 305160428 ^ 305160431;
                                                                                                var14_5[v52] = 1031592561 ^ 1031592471;
                                                                                                var14_5[11551729 ^ 11551733] = -349058630 ^ -349058605;
                                                                                                v53 = new String(var14_5, "UTF-8");
                                                                                                v54 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                                if (true) ** GOTO lbl296
                                                                                                block211: while (true) {
                                                                                                    v45 = v55 / (-4541859331549895509L ^ 2860391677333196356L);
lbl259:
                                                                                                    // 2 sources

                                                                                                    switch ((int)v45) {
                                                                                                        case -876892595: {
                                                                                                            break block211;
                                                                                                        }
                                                                                                        case 750968613: {
                                                                                                            v55 = 4194332148982535351L - 186251342726587769L;
                                                                                                            continue block211;
                                                                                                        }
                                                                                                        case 1207985043: {
                                                                                                            v55 = 3349332257063036613L ^ -2174153763557825280L;
                                                                                                            continue block211;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                }
                                                                                                v56 = new ArrayList<E>();
                                                                                                v57 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                                if (true) ** GOTO lbl276
                                                                                                block212: while (true) {
                                                                                                    v57 = v58 / (2430036140507971262L ^ 4636146787167918200L);
lbl276:
                                                                                                    // 2 sources

                                                                                                    switch ((int)v57) {
                                                                                                        case -1427480818: {
                                                                                                            v58 = -2767165548812012027L - 4034059605591807516L;
                                                                                                            continue block212;
                                                                                                        }
                                                                                                        case -876892595: {
                                                                                                            return (List)StringUtil.copyPartialMatches((String)v13, v44, v56);
                                                                                                        }
                                                                                                        case 486463443: {
                                                                                                            v58 = -3251587043254063446L ^ 4783427207138011751L;
                                                                                                            continue block212;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                }
                                                                                                return (List)StringUtil.copyPartialMatches((String)v13, v44, v56);
                                                                                                block213: while (true) {
                                                                                                    v54 = v59 / (-1943239737529173575L - -6695244745841244179L);
lbl296:
                                                                                                    // 2 sources

                                                                                                    switch ((int)v54) {
                                                                                                        case -876892595: {
                                                                                                            break block213;
                                                                                                        }
                                                                                                        case -842829519: {
                                                                                                            v59 = -7339215401855404685L ^ -91417550625421773L;
                                                                                                            continue block213;
                                                                                                        }
                                                                                                        case 2064600940: {
                                                                                                            v59 = 7858709409737351235L ^ 3927073347344841475L;
                                                                                                            continue block213;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                }
                                                                                                if (!v46.equalsIgnoreCase(v53)) break block310;
                                                                                                v60 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[-847631904 ^ -847631903];
                                                                                                377179716 ^ 1902122698;
                                                                                                v61 = new String[1490094117 ^ 1490094116];
                                                                                                var14_5 = new byte[206160233 ^ 206160237];
                                                                                                var14_5[-1308541903 ^ -1308541903] = -871692771 ^ -871692687;
                                                                                                var14_5[-1680538348 ^ -1680538345] = -1302855761 ^ -1302855733;
                                                                                                var14_5[-1104015367 ^ -1104015368] = -508625290 ^ -508625383;
                                                                                                var14_5[63752550 ^ 63752548] = -1150624694 ^ -1150624725;
                                                                                                v61[-161283573 ^ -161283573] = new String(var14_5, "UTF-8");
                                                                                                406568392 >>> "\u0000\u0000".length();
                                                                                                v62 = Arrays.asList(v61);
                                                                                                v63 = new ArrayList<E>();
                                                                                                v64 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                                if (true) ** GOTO lbl755
                                                                                            }
                                                                                            if (((void)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c).length != (-1788669750 ^ -1788669752)) break block311;
                                                                                            var14_5 = new byte[-1807699064 ^ -1807699068];
                                                                                            var14_5[1040619581 ^ 1040619580] = -272381866 ^ -272381901;
                                                                                            var14_5[-1470758950 ^ -1470758947] = 1852340698 ^ 1852340667;
                                                                                            v65 = 1760800469 ^ 1760800464;
                                                                                            var14_5[v65] = -1285471395 ^ -1285471438;
                                                                                            v66 = 1235411980 ^ 1235411972;
                                                                                            var14_5[v66] = -106552117 ^ -106552129;
                                                                                            var14_5[-561455870 ^ -561455866] = -1035244100 ^ -1035244080;
                                                                                            var14_5[771040154 ^ 771040144] = 979094806 ^ 979094905;
                                                                                            var14_5[-970582073 ^ -970582079] = -1874854429 ^ -1874854528;
                                                                                            var14_5[1558841350 ^ 1558841349] = 2127849265 ^ 2127849244;
                                                                                            var14_5[1430465300 ^ 1430465309] = 206117007 ^ 206117094;
                                                                                            var14_5[1895490678 ^ 1895490678] = 1096078996 ^ 1096079079;
                                                                                            var14_5[-1834411790 ^ -1834411792] = -905103527 ^ -905103571;
                                                                                            var14_5[77697812 ^ 77697823] = -1980379399 ^ -1980379497;
                                                                                            if (!CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[-91914985 ^ -91914985].equalsIgnoreCase(new String(var14_5, "UTF-8"))) break block311;
                                                                                            v67 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[360282026 ^ 360282027];
                                                                                            v68 = new String[219704344 ^ 219704342];
                                                                                            var14_5 = new byte[1748526169 ^ 1748526170];
                                                                                            v69 = -109313595 ^ -109313593;
                                                                                            var14_5[v69] = -966475902 ^ -966475822;
                                                                                            v70 = -1918716064 ^ -1918716064;
                                                                                            var14_5[v70] = -512303658 ^ -512303713;
                                                                                            v71 = -1451655475 ^ -1451655546;
                                                                                            var14_5[2041836908 ^ 2041836909] = v71;
                                                                                            v68[2132200008 ^ 2132200008] = new String(var14_5, "UTF-8");
                                                                                            v72 = -1934704478 ^ -1934704477;
                                                                                            var14_5 = new byte[1341553479 ^ 1341553476];
                                                                                            v73 = 1896275331 ^ 1896275393;
                                                                                            var14_5[529665492 ^ 529665494] = v73;
                                                                                            var14_5[369107107 ^ 369107106] = 866277808 ^ 866277863;
                                                                                            v74 = 1010635328 ^ 1010635273;
                                                                                            var14_5[-282143491 ^ -282143491] = v74;
                                                                                            v68[v72] = new String(var14_5, "UTF-8");
                                                                                            v75 = 917455781 ^ 917455783;
                                                                                            var14_5 = new byte[1718965958 ^ 1718965957];
                                                                                            v76 = 1140458571 ^ 1140458570;
                                                                                            var14_5[v76] = 1419461411 ^ 1419461480;
                                                                                            var14_5[-1330564353 ^ -1330564355] = 1609163768 ^ 1609163692;
                                                                                            var14_5[-1164139486 ^ -1164139486] = 54331291 ^ 54331346;
                                                                                            v68[v75] = new String(var14_5, "UTF-8");
                                                                                            var14_5 = new byte[139282669 ^ 139282670];
                                                                                            var14_5[2080285902 ^ 2080285903] = 1300369877 ^ 1300369822;
                                                                                            v77 = -114419901 ^ -114419903;
                                                                                            var14_5[v77] = 979928373 ^ 979928439;
                                                                                            var14_5[-1014880677 ^ -1014880677] = -382427233 ^ -382427178;
                                                                                            -2072720100 - 1986352536;
                                                                                            v68[625432779 ^ 625432776] = new String(var14_5, "UTF-8");
                                                                                            v78 = -454592837 ^ -454592833;
                                                                                            1350797265 - -1448529372;
                                                                                            var14_5 = new byte[1812370064 ^ 1812370067];
                                                                                            var14_5[-507267452 ^ -507267451] = -97033936 ^ -97033865;
                                                                                            v79 = -1553945112 ^ -1553945110;
                                                                                            var14_5[v79] = 1363699766 ^ 1363699828;
                                                                                            v80 = -1637121120 ^ -1637121120;
                                                                                            var14_5[v80] = 1904817985 ^ 1904817928;
                                                                                            v81 = -1242970102 ^ 1024406081;
                                                                                            v68[v78] = new String(var14_5, "UTF-8");
                                                                                            v82 = -1282909051 ^ -1282909049;
                                                                                            var14_5 = new byte[v82];
                                                                                            v83 = 1464631794 ^ 1464631795;
                                                                                            var14_5[v83] = 814703585 ^ 814703527;
                                                                                            v84 = -888460475 ^ -888460532;
                                                                                            var14_5[-1485454585 ^ -1485454585] = v84;
                                                                                            v68[-621300104 ^ -621300099] = new String(var14_5, "UTF-8");
                                                                                            var14_5 = new byte[-2042627923 ^ -2042627921];
                                                                                            v85 = -1001492588 ^ -1001492588;
                                                                                            var14_5[v85] = -40427746 ^ -40427689;
                                                                                            var14_5[-1878171113 ^ -1878171114] = 210270311 ^ 210270269;
                                                                                            v68[1811481837 ^ 1811481835] = new String(var14_5, "UTF-8");
                                                                                            var14_5 = new byte[-650682198 ^ -650682199];
                                                                                            v86 = 533370538 ^ 533370595;
                                                                                            var14_5[-312690796 ^ -312690796] = v86;
                                                                                            v87 = 1063439896 ^ 1063439897;
                                                                                            var14_5[v87] = 1638801652 ^ 1638801590;
                                                                                            v88 = -1949890747 ^ -1949890745;
                                                                                            var14_5[v88] = 897553656 ^ 897553596;
                                                                                            v68[-1547355411 ^ -1547355414] = new String(var14_5, "UTF-8");
                                                                                            var14_5 = new byte[732805685 ^ 732805686];
                                                                                            v89 = 2056609598 ^ 2056609598;
                                                                                            v90 = 1162164392 ^ 1162164449;
                                                                                            var14_5[v89] = v90;
                                                                                            var14_5[-1602953603 ^ -1602953604] = -1952201330 ^ -1952201260;
                                                                                            v91 = 2048072745 ^ 2048072747;
                                                                                            var14_5[v91] = 30839140 ^ 30839091;
                                                                                            v68[65663431 ^ 65663439] = new String(var14_5, "UTF-8");
                                                                                            v92 = -1457213480 ^ -1457213478;
                                                                                            var14_5 = new byte[v92];
                                                                                            v93 = 955120105 ^ 955120105;
                                                                                            v94 = 720785722 ^ 720785779;
                                                                                            var14_5[v93] = v94;
                                                                                            var14_5[-1365196672 ^ -1365196671] = 814481471 ^ 814481519;
                                                                                            v68[1762449335 ^ 1762449342] = new String(var14_5, "UTF-8");
                                                                                            var14_5 = new byte[1007982753 ^ 1007982754];
                                                                                            var14_5[-1615521734 ^ -1615521736] = -51489127 ^ -51489075;
                                                                                            var14_5[-724459522 ^ -724459521] = 201677737 ^ 201677818;
                                                                                            var14_5[-381709887 ^ -381709887] = 1917348728 ^ 1917348657;
                                                                                            v68[-493947152 ^ -493947142] = new String(var14_5, "UTF-8");
                                                                                            v95 = 1222775147 ^ 1222775136;
                                                                                            var14_5 = new byte[269932544 ^ 269932547];
                                                                                            var14_5[2042291154 ^ 2042291152] = -1627654777 ^ -1627654710;
                                                                                            v96 = -561309129 ^ -561309129;
                                                                                            var14_5[v96] = 1954162005 ^ 1954161948;
                                                                                            var14_5[1923699254 ^ 1923699255] = 159419336 ^ 159419288;
                                                                                            v68[v95] = new String(var14_5, "UTF-8");
                                                                                            var14_5 = new byte[-3869067 ^ -3869066];
                                                                                            var14_5[1688911523 ^ 1688911521] = 64773483 ^ 64773417;
                                                                                            v97 = -607092422 ^ -607092365;
                                                                                            var14_5[-175953507 ^ -175953507] = v97;
                                                                                            v98 = -1311533527 ^ -1311533528;
                                                                                            var14_5[v98] = -1154145945 ^ -1154146005;
                                                                                            v68[-2118271296 ^ -2118271284] = new String(var14_5, "UTF-8");
                                                                                            -489746600 - 1178650211;
                                                                                            v99 = -1932886478 ^ -1932886480;
                                                                                            var14_5 = new byte[v99];
                                                                                            var14_5[-1244168667 ^ -1244168668] = -1939696826 ^ -1939696894;
                                                                                            v100 = 381561826 ^ 381561826;
                                                                                            var14_5[v100] = 1720259739 ^ 1720259794;
                                                                                            v68[-1180586324 ^ -1180586335] = new String(var14_5, "UTF-8");
                                                                                            v101 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                            if (true) ** GOTO lbl770
                                                                                        }
                                                                                        1849559858 ^ 295739422;
                                                                                        if (((void)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c).length != (1966231024 ^ 1966231027)) break block312;
                                                                                        var14_5 = new byte[-646042356 ^ -646042358];
                                                                                        var14_5[1365782055 ^ 1365782050] = -1341373545 ^ -1341373456;
                                                                                        var14_5[-173896148 ^ -173896147] = -438013506 ^ -438013487;
                                                                                        var14_5[-307840963 ^ -307840967] = -393588218 ^ -393588113;
                                                                                        var14_5[-209172616 ^ -209172616] = -1470976057 ^ -1470976092;
                                                                                        var14_5[47038735 ^ 47038732] = -1702802004 ^ -1702801974;
                                                                                        var14_5[655639240 ^ 655639242] = -1377240012 ^ -1377239974;
                                                                                        -2130017925 ^ 1415094500;
                                                                                        if (!CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[-877623130 ^ -877623130].equalsIgnoreCase(new String(var14_5, "UTF-8"))) break block312;
                                                                                        1532915189 - 34078772;
                                                                                        var14_5 = new byte[1977272659 ^ 1977272663];
                                                                                        var14_5[1651385246 ^ 1651385246] = -790693747 ^ -790693663;
                                                                                        var14_5[-1024313691 ^ -1024313689] = 1577105073 ^ 1577105104;
                                                                                        var14_5[1020039893 ^ 1020039892] = 1298946787 ^ 1298946700;
                                                                                        var14_5[-523978259 ^ -523978258] = 394158362 ^ 394158462;
                                                                                        if (!CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[391976856 ^ 391976857].equalsIgnoreCase(new String(var14_5, "UTF-8"))) break block312;
                                                                                        v102 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                        if (true) ** GOTO lbl821
                                                                                    }
                                                                                    v103 = -1569321548 ^ 442654991;
                                                                                    if (((void)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c).length != (-541456144 ^ -541456142)) break block313;
                                                                                    var14_5 = new byte[1317723925 ^ 1317723920];
                                                                                    v104 = -371605524 ^ -371605624;
                                                                                    var14_5[1835621729 ^ 1835621728] = v104;
                                                                                    var14_5[1954173216 ^ 1954173216] = -488110240 ^ -488110335;
                                                                                    v105 = -1401399905 ^ -1401399818;
                                                                                    var14_5[415714767 ^ 415714764] = v105;
                                                                                    v106 = -1627845842 ^ -1627845846;
                                                                                    var14_5[v106] = 1239846880 ^ 1239846798;
                                                                                    var14_5[-1969559215 ^ -1969559213] = 1087859770 ^ 1087859799;
                                                                                    if (!CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[296247597 ^ 296247597].equalsIgnoreCase(new String(var14_5, "UTF-8"))) break block313;
                                                                                    v107 = 1734959922 - 1637803140;
                                                                                    v108 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[465338169 ^ 465338168];
                                                                                    -200458032 ^ 1637357678;
                                                                                    525156384 ^ -1344823584;
                                                                                    v109 = new String[-1057956055 ^ -1057956051];
                                                                                    1593192815 - 979014309;
                                                                                    var14_5 = new byte[550125950 ^ 550125937];
                                                                                    var14_5[-1889032376 ^ -1889032372] = -1904201641 ^ -1904201693;
                                                                                    v110 = 1899630606 ^ 1899630604;
                                                                                    var14_5[v110] = 328419725 ^ 328419817;
                                                                                    var14_5[-13688278 ^ -13688279] = -2084087475 ^ -2084087508;
                                                                                    var14_5[-837060734 ^ -837060729] = -1387277185 ^ -1387277286;
                                                                                    v111 = 259454381 ^ 259454371;
                                                                                    var14_5[v111] = 705137161 ^ 705137256;
                                                                                    v112 = -2067996773 ^ -2067996782;
                                                                                    var14_5[v112] = -1932449894 ^ -1932449793;
                                                                                    var14_5[-1327313296 ^ -1327313284] = -851192262 ^ -851192229;
                                                                                    v113 = 1975845604 ^ 1975845527;
                                                                                    var14_5[1426184460 ^ 1426184452] = v113;
                                                                                    var14_5[-1056485008 ^ -1056484995] = -77417751 ^ -77417827;
                                                                                    v114 = -961416117 ^ -961416128;
                                                                                    var14_5[v114] = 1156506587 ^ 1156506559;
                                                                                    var14_5[667002204 ^ 667002202] = 1885443304 ^ 1885443269;
                                                                                    var14_5[-1511832642 ^ -1511832641] = -375607082 ^ -375607130;
                                                                                    v115 = 267336223 ^ 267336298;
                                                                                    var14_5[610173989 ^ 610173986] = v115;
                                                                                    var14_5[-579453169 ^ -579453169] = 1503811588 ^ 1503811697;
                                                                                    var14_5[-644520632 ^ -644520638] = 1872204106 ^ 1872204088;
                                                                                    v109[-836878854 ^ -836878854] = new String(var14_5, "UTF-8");
                                                                                    v116 = -902492536 ^ -902492535;
                                                                                    var14_5 = new byte[946734098 ^ 946734106];
                                                                                    var14_5[790525990 ^ 790525990] = -916428516 ^ -916428419;
                                                                                    var14_5[-1711228762 ^ -1711228765] = -1213948767 ^ -1213948728;
                                                                                    v117 = 1071387441 ^ 1071387445;
                                                                                    var14_5[v117] = 187995849 ^ 187995822;
                                                                                    var14_5[1431096224 ^ 1431096225] = -592106652 ^ -592106752;
                                                                                    v118 = 1315333996 ^ 1315333999;
                                                                                    var14_5[v118] = 2000567010 ^ 2000566991;
                                                                                    var14_5[-1489013206 ^ -1489013208] = 609975314 ^ 609975414;
                                                                                    var14_5[16396168 ^ 16396174] = 1409066454 ^ 1409066416;
                                                                                    v119 = -1013217242 ^ -1013217247;
                                                                                    var14_5[v119] = -338625763 ^ -338625687;
                                                                                    v109[v116] = new String(var14_5, "UTF-8");
                                                                                    v120 = -850267729 ^ -850267740;
                                                                                    var14_5 = new byte[v120];
                                                                                    v121 = -9088340 ^ -9088346;
                                                                                    var14_5[v121] = -1298989247 ^ -1298989256;
                                                                                    v122 = -513787901 ^ -513787798;
                                                                                    var14_5[-90569640 ^ -90569634] = v122;
                                                                                    v123 = -1836521217 ^ -1836521221;
                                                                                    var14_5[v123] = 869569760 ^ 869569741;
                                                                                    var14_5[1257720500 ^ 1257720501] = 2043957016 ^ 2043957116;
                                                                                    var14_5[1250031058 ^ 1250031063] = 115982454 ^ 115982338;
                                                                                    v124 = -204193917 ^ -204193805;
                                                                                    var14_5[469853688 ^ 469853680] = v124;
                                                                                    v125 = -165498279 ^ -165498288;
                                                                                    var14_5[v125] = -1848067160 ^ -1848067132;
                                                                                    v126 = -904312902 ^ -904312882;
                                                                                    var14_5[-1825603362 ^ -1825603363] = v126;
                                                                                    var14_5[1389476334 ^ 1389476329] = -148833907 ^ -148833795;
                                                                                    var14_5[197476138 ^ 197476136] = 1149148003 ^ 1149147914;
                                                                                    v127 = -193829778 ^ -193829778;
                                                                                    var14_5[v127] = 1518358459 ^ 1518358494;
                                                                                    v109[-2018686346 ^ -2018686348] = new String(var14_5, "UTF-8");
                                                                                    v128 = -270823094 ^ -270823095;
                                                                                    v129 = 190977792 ^ 190977805;
                                                                                    var14_5 = new byte[v129];
                                                                                    v130 = 1503386838 ^ 1503386847;
                                                                                    var14_5[v130] = 878840649 ^ 878840633;
                                                                                    var14_5[110983891 ^ 110983894] = -597136590 ^ -597136553;
                                                                                    v131 = 1380591277 ^ 1380591274;
                                                                                    var14_5[v131] = -137471913 ^ -137471965;
                                                                                    var14_5[546907852 ^ 546907848] = -759954373 ^ -759954355;
                                                                                    var14_5[-1352873483 ^ -1352873473] = -1832067539 ^ -1832067491;
                                                                                    v132 = 627184913 ^ 627184915;
                                                                                    var14_5[v132] = -2037520729 ^ -2037520694;
                                                                                    var14_5[1383304107 ^ 1383304104] = -2119644768 ^ -2119644721;
                                                                                    var14_5[-1990867525 ^ -1990867533] = 1003610336 ^ 1003610249;
                                                                                    v133 = 611327044 ^ 611327055;
                                                                                    var14_5[v133] = 934204110 ^ 934204066;
                                                                                    v134 = 241752988 ^ 241752986;
                                                                                    var14_5[v134] = 1709943545 ^ 1709943508;
                                                                                    var14_5[-789330438 ^ -789330442] = 892527764 ^ 892527853;
                                                                                    v135 = 1774510148 ^ 1774510149;
                                                                                    var14_5[v135] = 284988693 ^ 284988784;
                                                                                    var14_5[1959673140 ^ 1959673140] = -837406965 ^ -837406855;
                                                                                    v109[v128] = new String(var14_5, "UTF-8");
                                                                                    v136 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                    if (true) ** GOTO lbl1080
                                                                                }
                                                                                36069125 ^ -486883049;
                                                                                -1708012023 - -1949671733;
                                                                                if (((void)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c).length != (1662572476 ^ 1662572479)) break block314;
                                                                                v137 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[1119984338 ^ 1119984338];
                                                                                v138 = 2004528720 ^ 2004528725;
                                                                                var14_5 = new byte[v138];
                                                                                var14_5[1255114472 ^ 1255114474] = -14411117 ^ -14411010;
                                                                                var14_5[-612103223 ^ -612103224] = 575268058 ^ 575268030;
                                                                                v139 = -826148196 ^ -826148110;
                                                                                var14_5[-118094767 ^ -118094763] = v139;
                                                                                var14_5[529100301 ^ 529100302] = 630162764 ^ 630162725;
                                                                                var14_5[1953589893 ^ 1953589893] = -119115801 ^ -119115898;
                                                                                v140 = new String(var14_5, "UTF-8");
                                                                                v141 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                if (true) ** GOTO lbl1123
                                                                                block214: while (true) {
                                                                                    v64 = v142 / (-4733268617645052874L ^ -7993144845153610803L);
lbl755:
                                                                                    // 2 sources

                                                                                    switch ((int)v64) {
                                                                                        case -1317365567: {
                                                                                            v142 = 165542803742322970L ^ 8401945746775490328L;
                                                                                            continue block214;
                                                                                        }
                                                                                        case -1300262604: {
                                                                                            v142 = 1710196278177773466L ^ -1124837176478644235L;
                                                                                            continue block214;
                                                                                        }
                                                                                        case -876892595: {
                                                                                            return (List)StringUtil.copyPartialMatches((String)v60, v62, v63);
                                                                                        }
                                                                                        case 160423166: {
                                                                                            v142 = -345926445695847453L ^ 1461950193004592026L;
                                                                                            continue block214;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                }
                                                                                return (List)StringUtil.copyPartialMatches((String)v60, v62, v63);
                                                                                block215: while (true) {
                                                                                    v101 = v143 / (3202585369283387035L ^ -6725229583881052849L);
lbl770:
                                                                                    // 2 sources

                                                                                    switch ((int)v101) {
                                                                                        case -1986912123: {
                                                                                            v143 = -2912723743634363118L - -636661997813809527L;
                                                                                            continue block215;
                                                                                        }
                                                                                        case -1215573189: {
                                                                                            v143 = -1944584793086425576L ^ 5686842608668302485L;
                                                                                            continue block215;
                                                                                        }
                                                                                        case -876892595: {
                                                                                            break block215;
                                                                                        }
                                                                                        case 118165038: {
                                                                                            v143 = 7995863704096561258L ^ 2114299821733945367L;
                                                                                            continue block215;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                }
                                                                                v144 = Arrays.asList(v68);
                                                                                v145 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                if (true) ** GOTO lbl787
                                                                                block216: while (true) {
                                                                                    v145 = v146 / (4230573507766872280L ^ 696788341551380993L);
lbl787:
                                                                                    // 2 sources

                                                                                    switch ((int)v145) {
                                                                                        case -876892595: {
                                                                                            break block216;
                                                                                        }
                                                                                        case -48982835: {
                                                                                            v146 = 3814156461641228786L ^ -6810064099274785032L;
                                                                                            continue block216;
                                                                                        }
                                                                                        case 774124523: {
                                                                                            v146 = 5602312274445687699L - 763020681233447108L;
                                                                                            continue block216;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                }
                                                                                v147 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                if (true) ** GOTO lbl802
                                                                                block217: while (true) {
                                                                                    v147 = v148 / (4971487497160561031L ^ -5990912886365302530L);
lbl802:
                                                                                    // 2 sources

                                                                                    switch ((int)v147) {
                                                                                        case -876892595: {
                                                                                            break block217;
                                                                                        }
                                                                                        case -857578098: {
                                                                                            v148 = -3403734530366777486L - 1954753799705811681L;
                                                                                            continue block217;
                                                                                        }
                                                                                        case -477238435: {
                                                                                            v148 = -6888224420263084207L - 7911467456878472463L;
                                                                                            continue block217;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                }
                                                                                v149 = new ArrayList<E>();
                                                                                while (true) {
                                                                                    if ((v150 = (cfr_temp_0 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (1618631743182763846L ^ 1518135086399173299L)) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) continue;
                                                                                    if (v150 == (-662886384 ^ 662886383)) {
                                                                                        return (List)StringUtil.copyPartialMatches((String)v67, v144, v149);
                                                                                    }
                                                                                    v150 = -74193329 ^ 1210219182;
                                                                                }
                                                                                block219: while (true) {
                                                                                    v102 = v151 / (-3306188462286222403L ^ 3178476079934926647L);
lbl821:
                                                                                    // 2 sources

                                                                                    switch ((int)v102) {
                                                                                        case -2074846258: {
                                                                                            v151 = -8737143209851170716L - -2339789445423976285L;
                                                                                            continue block219;
                                                                                        }
                                                                                        case -876892595: {
                                                                                            break block219;
                                                                                        }
                                                                                        case 625011475: {
                                                                                            v151 = 8565937247415448220L ^ 8739704016436093641L;
                                                                                            continue block219;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                }
                                                                                v152 = zNb\u015b.Y\u017btq.getDataFolder();
                                                                                var14_5 = new byte[-602133453 ^ -602133452];
                                                                                var14_5[-1787709318 ^ -1787709319] = 504333151 ^ 504333113;
                                                                                var14_5[2146775684 ^ 2146775680] = -1863302317 ^ -1863302342;
                                                                                v153 = 1977890389 ^ 1977890387;
                                                                                v154 = -287106121 ^ -287106108;
                                                                                var14_5[v153] = v154;
                                                                                var14_5[-177322643 ^ -177322643] = -863102552 ^ -863102517;
                                                                                var14_5[2115438808 ^ 2115438810] = -1918719057 ^ -1918719039;
                                                                                v155 = -1425006710 ^ -1425006705;
                                                                                var14_5[v155] = 1703295325 ^ 1703295290;
                                                                                var14_5[1653868995 ^ 1653868994] = -3562790 ^ -3562827;
                                                                                v156 = new String(var14_5, "UTF-8");
                                                                                while (true) {
                                                                                    block315: {
                                                                                        if ((v157 = (cfr_temp_1 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (685525713278604369L ^ 4650096563942594076L)) == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1)) == false) continue;
                                                                                        v158 = -453971075 ^ 453971074;
                                                                                        if (v157 != v158) break block315;
                                                                                        CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18782279f3 = new File(v152, v156);
                                                                                        v159 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                        if (true) ** GOTO lbl868
                                                                                    }
                                                                                    v157 = -1959589941 - -1821954939;
                                                                                }
                                                                                block221: while (true) {
                                                                                    v159 = v160 / (4021614751345074714L ^ -8696589540507175227L);
lbl868:
                                                                                    // 2 sources

                                                                                    switch ((int)v159) {
                                                                                        case -876892595: {
                                                                                            break block221;
                                                                                        }
                                                                                        case 1438109244: {
                                                                                            v160 = -9124366243209098840L ^ -3204958354106787101L;
                                                                                            continue block221;
                                                                                        }
                                                                                        case 1453833298: {
                                                                                            v160 = 5793922305355586132L - -3944571962342846993L;
                                                                                            continue block221;
                                                                                        }
                                                                                        case 1522039719: {
                                                                                            v160 = -6538531880135555255L ^ 7262095594539807258L;
                                                                                            continue block221;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                }
                                                                                var7_11 = new ArrayList<E>();
                                                                                25868045 ^ 494554030;
                                                                                if (!CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18782279f3.exists()) break block316;
                                                                                909657947 - 1806315832;
                                                                                if (!CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18782279f3.isDirectory()) break block316;
                                                                                v161 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                block222: while (true) {
                                                                                    switch ((int)v161) {
                                                                                        case -876892595: {
                                                                                            break block222;
                                                                                        }
                                                                                        case 571196602: {
                                                                                            v161 = (-5268678847817577621L ^ 7996569821868927341L) / (-7872294660023960503L ^ -7019350715503922767L);
                                                                                            continue block222;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                }
                                                                                CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18c40f17f2 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18782279f3.listFiles();
                                                                                if (CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18c40f17f2 == null) break block316;
                                                                                -1589823783 ^ 1813466900;
                                                                                v162 = 805262973 ^ 1232179115;
                                                                                var9_17 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18c40f17f2;
                                                                                var10_20 = var9_17.length;
                                                                                var11_22 = 1982797497 ^ 1982797497;
                                                                                -532782435 ^ -411711316;
                                                                                while (true) {
                                                                                    block317: {
                                                                                        if (var11_22 >= var10_20) break;
                                                                                        CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d180961fb80 = var9_17[var11_22];
                                                                                        if (!CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d180961fb80.isFile()) break block317;
                                                                                        v163 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d180961fb80.getName();
                                                                                        var14_5 = new byte[189666918 ^ 189666914];
                                                                                        var14_5[1683234275 ^ 1683234274] = -1739506319 ^ -1739506424;
                                                                                        var14_5[1309230953 ^ 1309230955] = -217795368 ^ -217795403;
                                                                                        v164 = -616131211 ^ -616131210;
                                                                                        v165 = 1771549867 ^ 1771549895;
                                                                                        var14_5[v164] = v165;
                                                                                        v166 = -207208115 ^ -207208115;
                                                                                        var14_5[v166] = 2106332276 ^ 2106332250;
                                                                                        v167 = new String(var14_5, "UTF-8");
                                                                                        v168 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                        if (true) ** GOTO lbl990
                                                                                        block224: while (true) {
                                                                                            v168 = v169 / (-478725816304526390L - -4150582277989355892L);
lbl990:
                                                                                            // 2 sources

                                                                                            switch ((int)v168) {
                                                                                                case -876892595: {
                                                                                                    break block224;
                                                                                                }
                                                                                                case -823170431: {
                                                                                                    v169 = -2336800123980736676L >>> "\u0000\u0000".length();
                                                                                                    continue block224;
                                                                                                }
                                                                                                case -596098117: {
                                                                                                    v169 = 1354491584067605319L ^ -5427220151893283509L;
                                                                                                    continue block224;
                                                                                                }
                                                                                                case 1866513778: {
                                                                                                    v169 = 3672051521761318932L ^ 8552262718280448951L;
                                                                                                    continue block224;
                                                                                                }
                                                                                            }
                                                                                            break;
                                                                                        }
                                                                                        if (!v163.endsWith(v167)) break block317;
                                                                                        v170 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                        if (true) ** GOTO lbl1015
                                                                                        block225: while (true) {
                                                                                            v170 = v171 / (-7710404793157165012L >>> "\u0000\u0000".length());
lbl1015:
                                                                                            // 2 sources

                                                                                            switch ((int)v170) {
                                                                                                case -2033440454: {
                                                                                                    v171 = -5812964014128889070L - 3962023385618276432L;
                                                                                                    continue block225;
                                                                                                }
                                                                                                case -876892595: {
                                                                                                    break block225;
                                                                                                }
                                                                                                case 1367643919: {
                                                                                                    v171 = -97817883374739315L ^ 4371199018389004395L;
                                                                                                    continue block225;
                                                                                                }
                                                                                            }
                                                                                            break;
                                                                                        }
                                                                                        v172 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d180961fb80.getName();
                                                                                        while (true) {
                                                                                            v173 = -776593738948996160L >>> "\u0000\u0000".length();
                                                                                            cfr_temp_2 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - v173;
                                                                                            v174 = cfr_temp_2 == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1);
                                                                                            if (v174 == false) continue;
                                                                                            v175 = 975442984 ^ -975442985;
                                                                                            if (v174 == v175) {
                                                                                                CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18084c420f.add(v172);
                                                                                                break;
                                                                                            }
                                                                                            v174 = 261725756 ^ -387016419;
                                                                                        }
                                                                                    }
                                                                                    1960823585 ^ -1704012263;
                                                                                    ++var11_22;
                                                                                }
                                                                            }
                                                                            v176 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[-1822273876 ^ -1822273874];
                                                                            while (true) {
                                                                                block318: {
                                                                                    if ((v177 = (cfr_temp_3 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (7322442779574348702L ^ 962106165255667052L)) == 0L ? 0 : (cfr_temp_3 < 0L ? -1 : 1)) == false) continue;
                                                                                    if (v177 != (835767466 ^ -835767467)) break block318;
                                                                                    v178 = new ArrayList<E>();
                                                                                    v179 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                                    if (true) ** GOTO lbl1067
                                                                                }
                                                                                v177 = 877064722 ^ 190161877;
                                                                            }
                                                                            block228: while (true) {
                                                                                v179 = v180 / (-422404988986311715L - -1055560094219054007L);
lbl1067:
                                                                                // 2 sources

                                                                                switch ((int)v179) {
                                                                                    case -1200872060: {
                                                                                        v180 = 3927763997982477529L ^ 6656592600452298445L;
                                                                                        continue block228;
                                                                                    }
                                                                                    case -876892595: {
                                                                                        return (List)StringUtil.copyPartialMatches((String)v176, (Iterable)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18084c420f, v178);
                                                                                    }
                                                                                    case 1497751935: {
                                                                                        v180 = 1807870041211414919L ^ -7867293863732667562L;
                                                                                        continue block228;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            }
                                                                            return (List)StringUtil.copyPartialMatches((String)v176, (Iterable)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18084c420f, v178);
                                                                            block229: while (true) {
                                                                                v136 = v181 / (3883697892971868852L >>> "\u0000\u0000".length());
lbl1080:
                                                                                // 2 sources

                                                                                switch ((int)v136) {
                                                                                    case -876892595: {
                                                                                        break block229;
                                                                                    }
                                                                                    case -187697880: {
                                                                                        v181 = -4209645700361469365L - -4784227577451902776L;
                                                                                        continue block229;
                                                                                    }
                                                                                    case 1248103839: {
                                                                                        v181 = -7505697161949021948L ^ -388053722762779406L;
                                                                                        continue block229;
                                                                                    }
                                                                                    case 1676353137: {
                                                                                        v181 = 3814818932331176200L - 5793666151817792846L;
                                                                                        continue block229;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            }
                                                                            v182 = Arrays.asList(v109);
                                                                            v183 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                            if (true) ** GOTO lbl1099
                                                                            block230: while (true) {
                                                                                v183 = (3784191554251518427L ^ 1671130060815539687L) / v184;
lbl1099:
                                                                                // 2 sources

                                                                                switch ((int)v183) {
                                                                                    case -876892595: {
                                                                                        break block230;
                                                                                    }
                                                                                    case -214852673: {
                                                                                        v184 = -2437199926635979522L ^ 2425337642426785922L;
                                                                                        continue block230;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            }
                                                                            v185 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                            block231: while (true) {
                                                                                switch ((int)v185) {
                                                                                    case -876892595: {
                                                                                        break block231;
                                                                                    }
                                                                                    case 580796955: {
                                                                                        v185 = (957229110278634686L - -56035304349234209L) / (-632157238958491515L ^ -4335265982310523076L);
                                                                                        continue block231;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            }
                                                                            v186 = new ArrayList<E>();
                                                                            while (true) {
                                                                                if ((v187 = (cfr_temp_4 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (5394688618166496488L >>> "\u0000\u0000".length())) == 0L ? 0 : (cfr_temp_4 < 0L ? -1 : 1)) == false) continue;
                                                                                if (v187 == (1820182090 ^ -1820182091)) {
                                                                                    return (List)StringUtil.copyPartialMatches((String)v108, v182, v186);
                                                                                }
                                                                                v187 = -1367056742 - 1715439270;
                                                                            }
                                                                            block233: while (true) {
                                                                                v188 = 3805528214269800751L - 1966642376610440988L;
                                                                                v141 = v189 / v188;
lbl1123:
                                                                                // 2 sources

                                                                                switch ((int)v141) {
                                                                                    case -1528610147: {
                                                                                        v189 = -8824770092798870266L ^ 5960210694081762397L;
                                                                                        continue block233;
                                                                                    }
                                                                                    case -1208211577: {
                                                                                        v189 = 7711612315730553037L ^ -3362469387629115359L;
                                                                                        continue block233;
                                                                                    }
                                                                                    case -876892595: {
                                                                                        break block233;
                                                                                    }
                                                                                    case 1588919597: {
                                                                                        v189 = -8667095657188410210L - -60723173545936465L;
                                                                                        continue block233;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            }
                                                                            if (!v137.equalsIgnoreCase(v140)) break block314;
                                                                            v190 = -1050894707 ^ -1050894708;
                                                                            var14_5 = new byte[-542681211 ^ -542681208];
                                                                            v191 = -1134141816 ^ -1134141823;
                                                                            var14_5[v191] = 1585865089 ^ 1585865201;
                                                                            v192 = -1137741506 ^ -1137741510;
                                                                            var14_5[v192] = -975041966 ^ -975042012;
                                                                            var14_5[723525990 ^ 723525988] = 901133788 ^ 901133745;
                                                                            var14_5[1822078831 ^ 1822078821] = 579878161 ^ 579878241;
                                                                            var14_5[-2026269097 ^ -2026269100] = -1257042157 ^ -1257042052;
                                                                            var14_5[-79050430 ^ -79050428] = 513036584 ^ 513036549;
                                                                            v193 = 951963577 ^ 951963569;
                                                                            var14_5[v193] = -1114548699 ^ -1114548660;
                                                                            v194 = 794975531 ^ 794975527;
                                                                            v195 = 1133505206 ^ 1133505231;
                                                                            var14_5[v194] = v195;
                                                                            var14_5[2085030531 ^ 2085030534] = 646864061 ^ 646864088;
                                                                            v196 = -1218431014 ^ -1218431041;
                                                                            var14_5[-116522446 ^ -116522445] = v196;
                                                                            v197 = 1324711416 ^ 1324711308;
                                                                            var14_5[1591540295 ^ 1591540288] = v197;
                                                                            var14_5[-452953730 ^ -452953739] = -641636136 ^ -641636172;
                                                                            v198 = var14_5[743930862 ^ 743930862] = 1487763003 ^ 1487763017;
                                                                            if (CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[v190].equalsIgnoreCase(new String(var14_5, "UTF-8"))) break block319;
                                                                            v199 = -740559868 ^ -740559861;
                                                                            var14_5 = new byte[v199];
                                                                            var14_5[-275810373 ^ -275810376] = -1942773773 ^ -1942773870;
                                                                            var14_5[287174654 ^ 287174650] = 1426878937 ^ 1426878893;
                                                                            v200 = 251929072 ^ 251929078;
                                                                            var14_5[v200] = 692765410 ^ 692765391;
                                                                            v201 = -892632936 ^ -892632836;
                                                                            var14_5[-1694134914 ^ -1694134916] = v201;
                                                                            v202 = -1703504797 ^ -1703504796;
                                                                            var14_5[v202] = 1060811130 ^ 1060811023;
                                                                            v203 = -1799665126 ^ -1799665042;
                                                                            var14_5[133182709 ^ 133182712] = v203;
                                                                            var14_5[925531438 ^ 925531431] = -1146610877 ^ -1146610906;
                                                                            var14_5[438043484 ^ 438043478] = 1148505526 ^ 1148505540;
                                                                            var14_5[1027478771 ^ 1027478781] = 83905634 ^ 83905539;
                                                                            v204 = 1573992067 ^ 1573992072;
                                                                            v205 = 220992559 ^ 220992587;
                                                                            var14_5[v204] = v205;
                                                                            var14_5[-781426305 ^ -781426313] = 1005534013 ^ 1005534030;
                                                                            var14_5[-459268492 ^ -459268495] = -663549621 ^ -663549650;
                                                                            var14_5[-1345559760 ^ -1345559760] = 2087428857 ^ 2087428748;
                                                                            var14_5[-203967668 ^ -203967680] = 922925377 ^ 922925344;
                                                                            v206 = -813033919 ^ -813033935;
                                                                            var14_5[990978373 ^ 990978372] = v206;
                                                                            if (CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[299829110 ^ 299829111].equalsIgnoreCase(new String(var14_5, "UTF-8"))) break block320;
                                                                            var14_5 = new byte[303795161 ^ 303795153];
                                                                            v207 = -92191734 ^ -92191729;
                                                                            var14_5[v207] = -1518537820 ^ -1518537779;
                                                                            v208 = 756739667 ^ 756739667;
                                                                            var14_5[v208] = 871483756 ^ 871483661;
                                                                            v209 = 416744691 ^ 416744596;
                                                                            var14_5[-51829423 ^ -51829419] = v209;
                                                                            var14_5[1087325602 ^ 1087325601] = 183567853 ^ 183567808;
                                                                            v210 = -171305532 ^ -171305533;
                                                                            var14_5[v210] = -690154647 ^ -690154723;
                                                                            var14_5[-514168575 ^ -514168573] = 110813074 ^ 110813174;
                                                                            v211 = 917635353 ^ 917635359;
                                                                            var14_5[v211] = -1657181381 ^ -1657181347;
                                                                            var14_5[-732673276 ^ -732673275] = -740122156 ^ -740122192;
                                                                            if (CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[-100528643 ^ -100528644].equalsIgnoreCase(new String(var14_5, "UTF-8"))) break block320;
                                                                            v212 = 1145931596 ^ 1145931591;
                                                                            var14_5 = new byte[v212];
                                                                            var14_5[311049254 ^ 311049251] = -474141835 ^ -474141951;
                                                                            var14_5[1202249728 ^ 1202249730] = 1967521138 ^ 1967521051;
                                                                            v213 = 387808797 ^ 387808788;
                                                                            var14_5[v213] = -1241570052 ^ -1241570160;
                                                                            var14_5[-668113190 ^ -668113190] = -1069998089 ^ -1069998190;
                                                                            var14_5[206376713 ^ 206376718] = 829484243 ^ 829484195;
                                                                            var14_5[1738922627 ^ 1738922626] = 1508954715 ^ 1508954687;
                                                                            var14_5[1325989524 ^ 1325989520] = 2052725374 ^ 2052725331;
                                                                            v214 = -1385610773 ^ -1385610776;
                                                                            var14_5[v214] = -1543511273 ^ -1543511197;
                                                                            v215 = 1839449843 ^ 1839449731;
                                                                            var14_5[1124864418 ^ 1124864426] = v215;
                                                                            var14_5[446208741 ^ 446208751] = -1044903893 ^ -1044903854;
                                                                            var14_5[813233131 ^ 813233133] = 2079712086 ^ 2079712063;
                                                                            -350432923 - 704662861;
                                                                            if (!CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[-577892006 ^ -577892005].equalsIgnoreCase(new String(var14_5, "UTF-8"))) break block308;
                                                                        }
                                                                        -386937144 - 2073665758;
                                                                        if (CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d183852efd6 == null) break block321;
                                                                        break block322;
                                                                    }
                                                                    v216 = 429718387 ^ 429718391;
                                                                    if (((void)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c).length != v216) break block323;
                                                                    var14_5 = new byte[-916427350 ^ -916427345];
                                                                    v217 = -1809341748 ^ -1809341779;
                                                                    var14_5[1853925200 ^ 1853925200] = v217;
                                                                    v218 = 1487900313 ^ 1487900400;
                                                                    var14_5[25204028 ^ 25204031] = v218;
                                                                    v219 = 1279672259 ^ 1279672257;
                                                                    v220 = -1648232670 ^ -1648232625;
                                                                    var14_5[v219] = v220;
                                                                    var14_5[1523559843 ^ 1523559847] = 1299311128 ^ 1299311222;
                                                                    v221 = 1986900802 ^ 1986900803;
                                                                    var14_5[v221] = -1862756634 ^ -1862756734;
                                                                    if (!CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[-849536555 ^ -849536555].equalsIgnoreCase(new String(var14_5, "UTF-8"))) break block323;
                                                                    v222 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[1132932742 ^ 1132932743];
                                                                    var14_5 = new byte[1078858560 ^ 1078858575];
                                                                    var14_5[780010968 ^ 780010974] = 1876103493 ^ 1876103528;
                                                                    var14_5[1150128569 ^ 1150128560] = -1455020065 ^ -1455020102;
                                                                    v223 = 1782183862 ^ 1782183890;
                                                                    var14_5[620174435 ^ 620174440] = v223;
                                                                    var14_5[-622116727 ^ -622116727] = 1292603571 ^ 1292603590;
                                                                    v224 = 1083617509 ^ 1083617511;
                                                                    var14_5[v224] = 263689089 ^ 263689189;
                                                                    v225 = 1514115671 ^ 1514115623;
                                                                    var14_5[800159109 ^ 800159108] = v225;
                                                                    var14_5[-344922373 ^ -344922376] = 1205333300 ^ 1205333333;
                                                                    v226 = 1117325913 ^ 1117325905;
                                                                    var14_5[v226] = -1640811269 ^ -1640811384;
                                                                    v227 = -826162335 ^ -826162331;
                                                                    v228 = 1804927450 ^ 1804927406;
                                                                    var14_5[v227] = v228;
                                                                    v229 = -1874861788 ^ -1874861784;
                                                                    v230 = 1924218995 ^ 1924218898;
                                                                    var14_5[v229] = v230;
                                                                    var14_5[-1449213998 ^ -1449213992] = -1805041621 ^ -1805041575;
                                                                    var14_5[-244684935 ^ -244684932] = 1001714756 ^ 1001714721;
                                                                    v231 = -461291461 ^ -461291466;
                                                                    var14_5[v231] = 2142670702 ^ 2142670618;
                                                                    v232 = -1295831061 ^ -1295831138;
                                                                    var14_5[-745303465 ^ -745303472] = v232;
                                                                    v233 = -737121928 ^ -737121930;
                                                                    v234 = -1500257664 ^ -1500257567;
                                                                    var14_5[v233] = v234;
                                                                    v235 = new String(var14_5, "UTF-8");
                                                                    v236 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                    if (true) ** GOTO lbl1917
                                                                }
                                                                while (true) {
                                                                    v237 = -3313942857668838216L >>> "\u0000\u0000".length();
                                                                    cfr_temp_5 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - v237;
                                                                    v238 = cfr_temp_5 == 0L ? 0 : (cfr_temp_5 < 0L ? -1 : 1);
                                                                    if (v238 == false) continue;
                                                                    if (v238 == (-1535829846 ^ 1535829845)) break;
                                                                    v238 = -340364904 ^ -1889283673;
                                                                }
                                                                v239 = zNb\u015b.Y\u017btq.getConfig();
                                                                v240 = 1250106226 ^ 1250106233;
                                                                var14_5 = new byte[v240];
                                                                var14_5[-2127651674 ^ -2127651666] = 395708885 ^ 395708852;
                                                                v241 = -947166875 ^ -947166962;
                                                                var14_5[942833544 ^ 942833546] = v241;
                                                                var14_5[-1840488875 ^ -1840488880] = 1896686473 ^ 1896686562;
                                                                var14_5[-882263127 ^ -882263128] = 1148284223 ^ 1148284246;
                                                                v242 = -1087107427 ^ -1087107390;
                                                                var14_5[1601664336 ^ 1601664342] = v242;
                                                                var14_5[-903399946 ^ -903399937] = -157747046 ^ -157746953;
                                                                var14_5[390354220 ^ 390354214] = -297057831 ^ -297057860;
                                                                v243 = 1838740732 ^ 1838740616;
                                                                var14_5[-1705688566 ^ -1705688567] = v243;
                                                                var14_5[-845893373 ^ -845893373] = 953228589 ^ 953228633;
                                                                v244 = 1045498530 ^ 1045498534;
                                                                var14_5[v244] = 1092707549 ^ 1092707506;
                                                                var14_5[354668669 ^ 354668666] = -194102116 ^ -194102030;
                                                                v245 = new String(var14_5, "UTF-8");
                                                                while (true) {
                                                                    if ((v246 = (cfr_temp_6 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-8602149993656322825L - 1245589962405144157L)) == 0L ? 0 : (cfr_temp_6 < 0L ? -1 : 1)) == false) continue;
                                                                    if (v246 == 1752714951 - 1752714952) break;
                                                                    v246 = 1533692418 ^ -256815200;
                                                                }
                                                                v247 = v239.getString(v245);
                                                                v248 = 859738191 ^ 859738190;
                                                                var14_5 = new byte[v248];
                                                                var14_5[-395762865 ^ -395762865] = 1503788890 ^ 1503788916;
                                                                v249 = new String(var14_5, "UTF-8");
                                                                var14_5 = new byte[48491977 ^ 48491976];
                                                                var14_5[-1624073103 ^ -1624073103] = 1651441951 ^ 1651441970;
                                                                v250 = new String(var14_5, "UTF-8");
                                                                while (true) {
                                                                    if ((v251 = (cfr_temp_7 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-1373084016588597264L ^ 2952629394417163754L)) == 0L ? 0 : (cfr_temp_7 < 0L ? -1 : 1)) == false) continue;
                                                                    v252 = -416402721 ^ 416402720;
                                                                    if (v251 == v252) break;
                                                                    v251 = -967529591 ^ 397492666;
                                                                }
                                                                CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18e356ea2e = v247.replace(v249, v250);
                                                                while (true) {
                                                                    block324: {
                                                                        if ((v253 = (cfr_temp_8 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (2679293944262428678L - -5378541925397314182L)) == 0L ? 0 : (cfr_temp_8 < 0L ? -1 : 1)) == false) continue;
                                                                        if (v253 != 365416886 - 365416887) break block324;
                                                                        v254 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                        if (true) ** GOTO lbl1388
                                                                    }
                                                                    v253 = -1642558483 - -1469545578;
                                                                }
                                                                block238: while (true) {
                                                                    v255 = 3474937184108264353L ^ -7252928182236618450L;
                                                                    v254 = v256 / v255;
lbl1388:
                                                                    // 2 sources

                                                                    switch ((int)v254) {
                                                                        case -1392435842: {
                                                                            v256 = 2131590861767020439L ^ -2877331446826243682L;
                                                                            continue block238;
                                                                        }
                                                                        case -876892595: {
                                                                            break block238;
                                                                        }
                                                                        case 399289504: {
                                                                            v256 = 3170493631842984397L ^ -4222670979353339075L;
                                                                            continue block238;
                                                                        }
                                                                        case 574668825: {
                                                                            v256 = 1179782364790187313L ^ -1889956547373166131L;
                                                                            continue block238;
                                                                        }
                                                                    }
                                                                    break;
                                                                }
                                                                v257 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                if (true) ** GOTO lbl1409
                                                                block239: while (true) {
                                                                    v257 = v258 / (7859294670437812565L - -8946362115166319396L);
lbl1409:
                                                                    // 2 sources

                                                                    switch ((int)v257) {
                                                                        case -876892595: {
                                                                            break block239;
                                                                        }
                                                                        case -180428228: {
                                                                            v258 = -2908748777102288987L - -5717051697346823226L;
                                                                            continue block239;
                                                                        }
                                                                        case 959952342: {
                                                                            v258 = -3378155775953146612L ^ 6984760756215267018L;
                                                                            continue block239;
                                                                        }
                                                                    }
                                                                    break;
                                                                }
                                                                v259 = new StringBuilder();
                                                                var14_5 = new byte[2023613379 ^ 2023613384];
                                                                var14_5[395399012 ^ 395399011] = -1011182382 ^ -1011182430;
                                                                var14_5[-269975276 ^ -269975273] = 1923981252 ^ 1923981289;
                                                                var14_5[-482907663 ^ -482907653] = -1456537786 ^ -1456537752;
                                                                v260 = -41300629 ^ -41300627;
                                                                var14_5[v260] = -454402926 ^ -454402846;
                                                                v261 = 1829918519 ^ 1829918526;
                                                                var14_5[v261] = 1849890831 ^ 1849890934;
                                                                var14_5[-1655197737 ^ -1655197738] = -2058856154 ^ -2058856119;
                                                                var14_5[-1084780065 ^ -1084780065] = 498896078 ^ 498896058;
                                                                var14_5[-675565590 ^ -675565598] = -573426982 ^ -573427018;
                                                                var14_5[991863226 ^ 991863231] = -343390178 ^ -343390089;
                                                                v262 = -171718570 ^ -171718574;
                                                                var14_5[v262] = -1422691690 ^ -1422691614;
                                                                var14_5[-492727600 ^ -492727598] = -2056921706 ^ -2056921626;
                                                                v263 = new String(var14_5, "UTF-8");
                                                                v264 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                if (true) ** GOTO lbl1441
                                                                block240: while (true) {
                                                                    v264 = v265 / (6861046467706066403L ^ 4646403485249166653L);
lbl1441:
                                                                    // 2 sources

                                                                    switch ((int)v264) {
                                                                        case -1833466030: {
                                                                            v265 = -402637807116561076L >>> "\u0000\u0000".length();
                                                                            continue block240;
                                                                        }
                                                                        case -1228542289: {
                                                                            v265 = 1867796765391009253L - -554298203707601261L;
                                                                            continue block240;
                                                                        }
                                                                        case -876892595: {
                                                                            break block240;
                                                                        }
                                                                        case 281467596: {
                                                                            v265 = -4995659058860514335L - -6458927548816301131L;
                                                                            continue block240;
                                                                        }
                                                                    }
                                                                    break;
                                                                }
                                                                v266 = v259.append(v263).append(CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18e356ea2e);
                                                                while (true) {
                                                                    if ((v267 = (cfr_temp_9 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (4557261213301753273L ^ 3999851816333341848L)) == 0L ? 0 : (cfr_temp_9 < 0L ? -1 : 1)) == false) continue;
                                                                    if (v267 == (-1818752118 ^ 1818752117)) break;
                                                                    v267 = -1521142879 ^ 468423614;
                                                                }
                                                                v268 = v266.toString();
                                                                v269 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                block242: while (true) {
                                                                    switch ((int)v269) {
                                                                        case -876892595: {
                                                                            break block242;
                                                                        }
                                                                        case -501454638: {
                                                                            v269 = (3016303303658608485L ^ 6446364987030345548L) / (-5036092927645268196L >>> "\u0000\u0000".length());
                                                                            continue block242;
                                                                        }
                                                                    }
                                                                    break;
                                                                }
                                                                CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18084c420f = \u017b\u017c\u0107m.7\u015aCz.getConfigurationSection(v268);
                                                                if (CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18297b12fb == null) break block325;
                                                                v270 = 1135344228 ^ 1135344230;
                                                                CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18c40f17f2 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[v270];
                                                                v271 = -419306997 ^ -419306998;
                                                                var14_5 = new byte[v271];
                                                                var14_5[-914815950 ^ -914815950] = -129776027 ^ -129776057;
                                                                v272 = new String(var14_5, "UTF-8");
                                                                v273 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                if (true) ** GOTO lbl1494
                                                                block243: while (true) {
                                                                    v273 = v274 / (8900518380774509260L ^ -5064810338696273179L);
lbl1494:
                                                                    // 2 sources

                                                                    switch ((int)v273) {
                                                                        case -945750967: {
                                                                            v274 = 2734871990876120388L >>> "\u0000\u0000".length();
                                                                            continue block243;
                                                                        }
                                                                        case -876892595: {
                                                                            break block243;
                                                                        }
                                                                        case 368321580: {
                                                                            v274 = 3739577867570945021L ^ -7750303106062559673L;
                                                                            continue block243;
                                                                        }
                                                                        case 1851879792: {
                                                                            v274 = -5523550121931754750L - -8919711009454840867L;
                                                                            continue block243;
                                                                        }
                                                                    }
                                                                    break;
                                                                }
                                                                var9_18 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18703c36b8.startsWith(v272);
                                                                if (CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d182be21e8e == false) break block326;
                                                                v275 = -1347407524 ^ -1443226112;
                                                                v276 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                if (true) ** GOTO lbl1531
                                                            }
                                                            v277 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18703c36b8;
                                                            143382260 ^ -1553052492;
                                                            break block327;
                                                            block244: while (true) {
                                                                v276 = v278 / (5378563854357518099L ^ -4075500423276300538L);
lbl1531:
                                                                // 2 sources

                                                                switch ((int)v276) {
                                                                    case -2059350147: {
                                                                        v278 = 198946168501599646L ^ -436828081865778634L;
                                                                        continue block244;
                                                                    }
                                                                    case -876892595: {
                                                                        break block244;
                                                                    }
                                                                    case 1253330978: {
                                                                        v278 = -4214285767640128419L ^ 1664513395964774104L;
                                                                        continue block244;
                                                                    }
                                                                }
                                                                break;
                                                            }
                                                            v277 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18703c36b8.substring(-564692696 ^ -564692695);
                                                            v279 = 296556106 - 617647201;
                                                        }
                                                        CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18ef2d5335 = v277;
                                                        v280 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18297b12fb.getKeys((boolean)(-138601421 ^ -138601421));
                                                        while (true) {
                                                            v281 = 751294288638925129L ^ 5085246214124219492L;
                                                            cfr_temp_10 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - v281;
                                                            v282 = cfr_temp_10 == 0L ? 0 : (cfr_temp_10 < 0L ? -1 : 1);
                                                            if (v282 == false) continue;
                                                            if (v282 == (-2136691370 ^ 2136691369)) break;
                                                            v282 = -507726501 - -570199807;
                                                        }
                                                        v283 = v280.stream();
                                                        v284 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                        block246: while (true) {
                                                            switch ((int)v284) {
                                                                case -876892595: {
                                                                    break block246;
                                                                }
                                                                case 1216298437: {
                                                                    v284 = (-2142652545202377475L - 5332500353563042064L) / (983884244986717657L ^ -7337731653152650525L);
                                                                    continue block246;
                                                                }
                                                            }
                                                            break;
                                                        }
                                                        v285 = (Function<String, String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, D4tv6pGsnl75Qoys(java.lang.String ), (Ljava/lang/String;)Ljava/lang/String;)();
                                                        1899652358 ^ 1804528886;
                                                        v286 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                        if (true) ** GOTO lbl1576
                                                        block247: while (true) {
                                                            v286 = v287 / (-6296986983333693157L ^ 5054104141999527644L);
lbl1576:
                                                            // 2 sources

                                                            switch ((int)v286) {
                                                                case -1681459898: {
                                                                    v287 = 82909592522058447L ^ 7008844218784544802L;
                                                                    continue block247;
                                                                }
                                                                case -1191398420: {
                                                                    v287 = 6932744674151712407L ^ -7627565233180482581L;
                                                                    continue block247;
                                                                }
                                                                case -876892595: {
                                                                    break block247;
                                                                }
                                                            }
                                                            break;
                                                        }
                                                        v288 = v283.map(v285);
                                                        v289 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                        block248: while (true) {
                                                            switch ((int)v289) {
                                                                case -876892595: {
                                                                    break block248;
                                                                }
                                                                case 489584469: {
                                                                    v289 = (-748603284696612828L - 6701623154943369920L) / (-8683384868852463116L ^ 8076831391890498482L);
                                                                    continue block248;
                                                                }
                                                            }
                                                            break;
                                                        }
                                                        v290 = (Predicate<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, gAHusKZY5Tkkp-ms(java.lang.String java.lang.String ), (Ljava/lang/String;)Z)((String)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18ef2d5335);
                                                        v291 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                        if (true) ** GOTO lbl1605
                                                        block249: while (true) {
                                                            v291 = v292 / (4441474469379542986L ^ 2057751803182528376L);
lbl1605:
                                                            // 2 sources

                                                            switch ((int)v291) {
                                                                case -876892595: {
                                                                    break block249;
                                                                }
                                                                case 1369260951: {
                                                                    v292 = -7464078269529524571L ^ -675167435081257588L;
                                                                    continue block249;
                                                                }
                                                                case 1645072481: {
                                                                    v292 = -6370578104556987382L - -8361171891335642435L;
                                                                    continue block249;
                                                                }
                                                            }
                                                            break;
                                                        }
                                                        v293 = v288.filter(v290);
                                                        while (true) {
                                                            block328: {
                                                                if ((v294 = (cfr_temp_11 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-6818614164483858006L ^ -4536337893331136183L)) == 0L ? 0 : (cfr_temp_11 < 0L ? -1 : 1)) == false) continue;
                                                                if (v294 != (-650350392 ^ 650350391)) break block328;
                                                                v295 = v293.map((Function<String, String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, PRryBTP2sBIwodDV(java.lang.String ), (Ljava/lang/String;)Ljava/lang/String;)());
                                                                v296 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                                if (true) ** GOTO lbl1635
                                                            }
                                                            v294 = -137957369 ^ -1422225645;
                                                        }
                                                        block251: while (true) {
                                                            v297 = 5829175973087102923L - -4243658733695087106L;
                                                            v296 = v298 / v297;
lbl1635:
                                                            // 2 sources

                                                            switch ((int)v296) {
                                                                case -1611543004: {
                                                                    v298 = -2856486896676085289L - -8160231871091203323L;
                                                                    continue block251;
                                                                }
                                                                case -876892595: {
                                                                    break block251;
                                                                }
                                                                case 1870745649: {
                                                                    v298 = 9030195334214705226L - -4245520077418185016L;
                                                                    continue block251;
                                                                }
                                                            }
                                                            break;
                                                        }
                                                        CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18699c2f0c = v295.collect(Collectors.<T>toList());
                                                        549090316 >>> "\u0000\u0000".length();
                                                        v299 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[142756758 ^ 142756756];
                                                        while (true) {
                                                            v300 = -87539480162273266L ^ -7503259342112776655L;
                                                            cfr_temp_12 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - v300;
                                                            v301 = cfr_temp_12 == 0L ? 0 : (cfr_temp_12 < 0L ? -1 : 1);
                                                            if (v301 == false) continue;
                                                            if (v301 == (-393217766 ^ 393217765)) break;
                                                            v301 = -1891348360 ^ -17017526;
                                                        }
                                                        while (true) {
                                                            v302 = -1210030448970321059L ^ -4231871182943573558L;
                                                            cfr_temp_13 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - v302;
                                                            v303 = cfr_temp_13 == 0L ? 0 : (cfr_temp_13 < 0L ? -1 : 1);
                                                            if (v303 == false) continue;
                                                            if (v303 == (172770335 ^ -172770336)) {
                                                                return (List)StringUtil.copyPartialMatches((String)v299, CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18699c2f0c, new ArrayList<E>());
                                                            }
                                                            v303 = -1019855981 - 1183384833;
                                                        }
                                                    }
                                                    while (true) {
                                                        if ((v304 = (cfr_temp_14 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (7479702387428274750L - -3604430386511565538L)) == 0L ? 0 : (cfr_temp_14 < 0L ? -1 : 1)) == false) continue;
                                                        if (v304 == (-289894345 ^ -289894346)) {
                                                            return new ArrayList<String>();
                                                        }
                                                        v304 = -1267565784 - -1488490744;
                                                    }
                                                }
                                                v305 = 366904024 ^ -1951523580;
                                                CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18e356ea2e = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[-1021857778 ^ -1021857780];
                                                var14_5 = new byte[-1048163962 ^ -1048163961];
                                                var14_5[-1574828101 ^ -1574828101] = -341040153 ^ -341040187;
                                                v306 = new String(var14_5, "UTF-8");
                                                while (true) {
                                                    v307 = -3271384162389460058L ^ 5139336480086116427L;
                                                    cfr_temp_15 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - v307;
                                                    v308 = cfr_temp_15 == 0L ? 0 : (cfr_temp_15 < 0L ? -1 : 1);
                                                    if (v308 == false) continue;
                                                    if (v308 == (-1897345873 ^ -1897345874)) {
                                                        CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18297b12fb = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d1892cfda06.startsWith(v306);
                                                        if (CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18cf1b049a != false) {
                                                            break;
                                                        }
                                                        break block307;
                                                    }
                                                    v308 = 681323215 ^ 1643759649;
                                                }
                                                v309 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d1892cfda06.substring(1796485270 ^ 1796485271);
                                                break block329;
                                            }
                                            v309 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d1892cfda06;
                                            -347786122 ^ 73481237;
                                        }
                                        CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d185eda6d65 = v309;
                                        while (true) {
                                            v310 = -5648789965329923552L >>> "\u0000\u0000".length();
                                            cfr_temp_16 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - v310;
                                            v311 = cfr_temp_16 == 0L ? 0 : (cfr_temp_16 < 0L ? -1 : 1);
                                            if (v311 == false) continue;
                                            if (v311 == (1977583455 ^ -1977583456)) break;
                                            v311 = 427968190 - -501408851;
                                        }
                                        v312 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d183852efd6.getKeys((boolean)(2019866991 ^ 2019866991)).stream();
                                        while (true) {
                                            block330: {
                                                if ((v313 = (cfr_temp_17 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (215575148664167052L - -3461278429044267571L)) == 0L ? 0 : (cfr_temp_17 < 0L ? -1 : 1)) == false) continue;
                                                if (v313 != (-1473907080 ^ 1473907079)) break block330;
                                                v314 = (Function<String, String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, T3HGfeuH7MWvx-oy(java.lang.String ), (Ljava/lang/String;)Ljava/lang/String;)();
                                                v315 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                if (true) ** GOTO lbl1776
                                            }
                                            v313 = 1775973669 - 1333576654;
                                        }
                                        block258: while (true) {
                                            v315 = v316 / (182622934855209100L ^ 6966596852302036453L);
lbl1776:
                                            // 2 sources

                                            switch ((int)v315) {
                                                case -876892595: {
                                                    break block258;
                                                }
                                                case 800305543: {
                                                    v316 = 7716608089595662973L ^ -4029394517105481139L;
                                                    continue block258;
                                                }
                                                case 1178391853: {
                                                    v316 = 7715836598684372017L ^ 3535586113150698546L;
                                                    continue block258;
                                                }
                                                case 1185630599: {
                                                    v316 = -8648830706436551188L ^ -1390818875547090506L;
                                                    continue block258;
                                                }
                                            }
                                            break;
                                        }
                                        v317 = v312.map(v314);
                                        v318 = (Predicate<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, m-Z-RpVz5A_rAAnW(java.lang.String java.lang.String ), (Ljava/lang/String;)Z)((String)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d185eda6d65);
                                        while (true) {
                                            block331: {
                                                if ((v319 = (cfr_temp_18 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-7088413305846945888L ^ -5880963017867177922L)) == 0L ? 0 : (cfr_temp_18 < 0L ? -1 : 1)) == false) continue;
                                                v320 = -680189237 ^ 680189236;
                                                if (v319 != v320) break block331;
                                                v321 = v317.filter(v318);
                                                v322 = (Function<String, String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, G1Lj9qnx4DwJxCuH(java.lang.String ), (Ljava/lang/String;)Ljava/lang/String;)();
                                                v323 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                if (true) ** GOTO lbl1811
                                            }
                                            v319 = -581056276 - -163384532;
                                        }
                                        block260: while (true) {
                                            v323 = v324 / (2018751308386365367L ^ -8290977142628530198L);
lbl1811:
                                            // 2 sources

                                            switch ((int)v323) {
                                                case -1880638260: {
                                                    v324 = -812534169336636207L ^ -3262881562744029433L;
                                                    continue block260;
                                                }
                                                case -935791421: {
                                                    v324 = -1580778814265783516L ^ -7652205975028342702L;
                                                    continue block260;
                                                }
                                                case -876892595: {
                                                    break block260;
                                                }
                                                case -707636484: {
                                                    v324 = 3011657146425155650L ^ -8670208318710498631L;
                                                    continue block260;
                                                }
                                            }
                                            break;
                                        }
                                        v325 = v321.map(v322);
                                        while (true) {
                                            if ((v326 = (cfr_temp_19 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (6075060021283691293L ^ 1763493660658266077L)) == 0L ? 0 : (cfr_temp_19 < 0L ? -1 : 1)) == false) continue;
                                            if (v326 == (79618456 ^ -79618457)) break;
                                            v326 = 1260803984 - -2121488909;
                                        }
                                        v327 = Collectors.toList();
                                        while (true) {
                                            block332: {
                                                if ((v328 = (cfr_temp_20 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-2800435582250590235L - 7056380762798907251L)) == 0L ? 0 : (cfr_temp_20 < 0L ? -1 : 1)) == false) continue;
                                                if (v328 != (-2023084444 ^ 2023084443)) break block332;
                                                CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d182be21e8e = v325.collect(v327);
                                                1429986512 ^ -397006539;
                                                -510249097 - 627923100;
                                                v329 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[968880562 ^ 968880560];
                                                2142964978 - 925295617;
                                                v330 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                                if (true) ** GOTO lbl1857
                                            }
                                            v328 = -1608331382 - 448232543;
                                        }
                                        block263: while (true) {
                                            v330 = v331 / (3562317709149909594L - 1842856905049154739L);
lbl1857:
                                            // 2 sources

                                            switch ((int)v330) {
                                                case -876892595: {
                                                    break block263;
                                                }
                                                case 548949861: {
                                                    v331 = -7217135432286828631L ^ 8563614639386480436L;
                                                    continue block263;
                                                }
                                                case 1267513037: {
                                                    v331 = -2426685430312252241L - 1370889689072438273L;
                                                    continue block263;
                                                }
                                            }
                                            break;
                                        }
                                        v332 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                        if (true) ** GOTO lbl1873
                                        block264: while (true) {
                                            v332 = v333 / (2385446134439466311L ^ -4628443381169534428L);
lbl1873:
                                            // 2 sources

                                            switch ((int)v332) {
                                                case -1883076992: {
                                                    v333 = -4564318814834516489L ^ 5358442279836874660L;
                                                    continue block264;
                                                }
                                                case -876892595: {
                                                    break block264;
                                                }
                                                case 1745611147: {
                                                    v333 = -7843757500843078080L ^ -3522577452397631351L;
                                                    continue block264;
                                                }
                                            }
                                            break;
                                        }
                                        v334 = new ArrayList<E>();
                                        v335 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                        block265: while (true) {
                                            switch ((int)v335) {
                                                case -973120227: {
                                                    v335 = (1568544866582147380L ^ 8661941838010597183L) / (-7714133186116890856L - 4848100086557823290L);
                                                    continue block265;
                                                }
                                                case -876892595: {
                                                    return (List)StringUtil.copyPartialMatches((String)v329, (Iterable)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d1836cd6553, v334);
                                                }
                                            }
                                            break;
                                        }
                                        return (List)StringUtil.copyPartialMatches((String)v329, (Iterable)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d1836cd6553, v334);
                                    }
                                    while (true) {
                                        block333: {
                                            if ((v336 = (cfr_temp_21 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-4135920913126218552L - 4232125761957625505L)) == 0L ? 0 : (cfr_temp_21 < 0L ? -1 : 1)) == false) continue;
                                            if (v336 != (-1862113166 ^ -1862113165)) break block333;
                                            v337 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                            if (true) ** GOTO lbl1904
                                        }
                                        v336 = 965591948 >>> "\u0000\u0000".length();
                                    }
                                    block267: while (true) {
                                        v337 = (-793165229725603486L ^ -4730737963043633045L) / v338;
lbl1904:
                                        // 2 sources

                                        switch ((int)v337) {
                                            case -1449469500: {
                                                v338 = -6225557064901689024L >>> "\u0000\u0000".length();
                                                continue block267;
                                            }
                                            case -876892595: {
                                                return new ArrayList<String>();
                                            }
                                        }
                                        break;
                                    }
                                    return new ArrayList<String>();
                                    block268: while (true) {
                                        v339 = 985877972151162901L ^ -3719922162726831786L;
                                        v236 = v340 / v339;
lbl1917:
                                        // 2 sources

                                        switch ((int)v236) {
                                            case -876892595: {
                                                break block268;
                                            }
                                            case 691415350: {
                                                v340 = 2509263412032415493L - 6452302592517370353L;
                                                continue block268;
                                            }
                                            case 1240056217: {
                                                v340 = -6101836361681808277L - 2886564484722498518L;
                                                continue block268;
                                            }
                                        }
                                        break;
                                    }
                                    if (!v222.equalsIgnoreCase(v235)) break block323;
                                    if (CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d183852efd6 == null) break block323;
                                    v341 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                    if (true) ** GOTO lbl2029
                                }
                                -873668744 ^ -1158534082;
                                if (((void)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c).length != (921349113 ^ 921349117)) break block334;
                                var14_5 = new byte[-353860174 ^ -353860169];
                                var14_5[-1259927732 ^ -1259927736] = 1683493590 ^ 1683493560;
                                var14_5[1130261803 ^ 1130261800] = 482098916 ^ 482098829;
                                v342 = -1728354323 ^ -1728354420;
                                var14_5[633669426 ^ 633669426] = v342;
                                v343 = -1889534602 ^ -1889534693;
                                var14_5[1727116671 ^ 1727116669] = v343;
                                var14_5[-1384083967 ^ -1384083968] = 1319904532 ^ 1319904624;
                                if (!CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[613678940 ^ 613678940].equalsIgnoreCase(new String(var14_5, "UTF-8"))) break block334;
                                -696462370 ^ 1218721367;
                                -7704241 ^ 2141810251;
                                var14_5 = new byte[-399557104 ^ -399557096];
                                var14_5[77785353 ^ 77785358] = 740836001 ^ 740836053;
                                var14_5[-5738104 ^ -5738103] = -26256706 ^ -26256678;
                                v344 = 1565912901 ^ 1565912899;
                                var14_5[v344] = 1341486631 ^ 1341486657;
                                var14_5[-132472852 ^ -132472852] = 858010316 ^ 858010285;
                                var14_5[-1639047080 ^ -1639047078] = -671585661 ^ -671585561;
                                var14_5[1111941476 ^ 1111941479] = -254248647 ^ -254248684;
                                v345 = 529497263 ^ 529497258;
                                v346 = 793997734 ^ 793997775;
                                var14_5[v345] = v346;
                                var14_5[-938657091 ^ -938657095] = 1150123616 ^ 1150123527;
                                -2130128857 ^ -361965138;
                                if (!CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[-27836703 ^ -27836704].equalsIgnoreCase(new String(var14_5, "UTF-8"))) break block334;
                                1000280721 - -1523130347;
                                v347 = 656719868 >>> "\u0000\u0000".length();
                                v348 = -238066503 ^ 1624637938;
                                if (8\u0105ja.\u0142Dzn == null) break block335;
                                break block336;
                            }
                            -2085298447 ^ -859372135;
                            if (((void)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c).length != (-1434638982 ^ -1434638978)) break block308;
                            break block337;
                            block269: while (true) {
                                v341 = v349 / (-6988138837608321387L ^ 7201229589815802145L);
lbl2029:
                                // 2 sources

                                switch ((int)v341) {
                                    case -1655858923: {
                                        v349 = -928870338102993440L ^ -1936596644508661203L;
                                        continue block269;
                                    }
                                    case -876892595: {
                                        break block269;
                                    }
                                    case 906135352: {
                                        v349 = -3490693442024461877L - 2945305311288126056L;
                                        continue block269;
                                    }
                                    case 2095247495: {
                                        v349 = -7649932219765353938L ^ 6803869572239572964L;
                                        continue block269;
                                    }
                                }
                                break;
                            }
                            v350 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d183852efd6.getKeys((boolean)(-1293860298 ^ -1293860298));
                            while (true) {
                                if ((v351 = (cfr_temp_22 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-3413207551900899037L ^ 7690108511471588350L)) == 0L ? 0 : (cfr_temp_22 < 0L ? -1 : 1)) == false) continue;
                                if (v351 == (409934483 ^ 409934482)) break;
                                v351 = -851421605 - -678609401;
                            }
                            v352 = v350.stream().map((Function<String, String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, Dm15ybQEJfOIVAvo(java.lang.String ), (Ljava/lang/String;)Ljava/lang/String;)());
                            while (true) {
                                block338: {
                                    if ((v353 = (cfr_temp_23 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (3120927630226204058L ^ -4348034042824907994L)) == 0L ? 0 : (cfr_temp_23 < 0L ? -1 : 1)) == false) continue;
                                    if (v353 != -526244110 - -526244109) break block338;
                                    v354 = v352.filter((Predicate<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, -0mdu9RBZBW094fb(java.lang.String[] java.lang.String ), (Ljava/lang/String;)Z)((String[])CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c));
                                    v355 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                    if (true) ** GOTO lbl2061
                                }
                                v353 = 1754408218 ^ 1126372606;
                            }
                            block272: while (true) {
                                v355 = v356 / (-8528067193345100621L ^ 4067804753028978400L);
lbl2061:
                                // 2 sources

                                switch ((int)v355) {
                                    case -876892595: {
                                        break block272;
                                    }
                                    case -458201509: {
                                        v356 = 7166159269958590032L - 6004724732737283872L;
                                        continue block272;
                                    }
                                    case -378397346: {
                                        v356 = 2946070500580053270L ^ -4250732369326651400L;
                                        continue block272;
                                    }
                                    case 959082555: {
                                        v356 = -3184507855671809652L - -5857337141255477651L;
                                        continue block272;
                                    }
                                }
                                break;
                            }
                            v357 = v354.map((Function<String, String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, kVHeHzKl5sfJmyTA(java.lang.String ), (Ljava/lang/String;)Ljava/lang/String;)());
                            v358 = Collectors.toList();
                            while (true) {
                                if ((v359 = (cfr_temp_24 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (2627373324467093302L ^ 7378627718971612317L)) == 0L ? 0 : (cfr_temp_24 < 0L ? -1 : 1)) == false) continue;
                                if (v359 == 2029492753 - 2029492754) {
                                    return v357.collect(v358);
                                }
                                v359 = 508160800 - 595079105;
                            }
                        }
                        while (true) {
                            if ((v360 = (cfr_temp_25 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (5854488208989663852L ^ 4208112280901720470L)) == 0L ? 0 : (cfr_temp_25 < 0L ? -1 : 1)) == false) continue;
                            if (v360 == (506766390 ^ -506766391)) break;
                            v360 = 1173622498 ^ -407541804;
                        }
                        v361 = 8\u0105ja.\u0142Dzn.values();
                        while (true) {
                            if ((v362 = (cfr_temp_26 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-4128531407995820771L - -7049725211682332970L)) == 0L ? 0 : (cfr_temp_26 < 0L ? -1 : 1)) == false) continue;
                            if (v362 == (1704599572 ^ -1704599573)) break;
                            v362 = -1861733920 ^ -1934356176;
                        }
                        v363 = v361.stream();
                        while (true) {
                            if ((v364 = (cfr_temp_27 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (6932605045437919562L - -3525392856365592410L)) == 0L ? 0 : (cfr_temp_27 < 0L ? -1 : 1)) == false) continue;
                            if (v364 == (1928272409 ^ 1928272408)) break;
                            v364 = -288952417 ^ -141968758;
                        }
                        v365 = v363.map((Function<\u00d3vR\u017a, String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, GOUs0rxkbuLIMLv0(\u00d3vR\u017a ), (L\u00d3vR\u017a;)Ljava/lang/String;)());
                        while (true) {
                            block339: {
                                v366 = -3489647778341299477L ^ -8741805669649992902L;
                                cfr_temp_28 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - v366;
                                v367 = cfr_temp_28 == 0L ? 0 : (cfr_temp_28 < 0L ? -1 : 1);
                                if (v367 == false) continue;
                                v368 = 885944036 - 885944037;
                                if (v367 != v368) break block339;
                                v369 = v365.filter((Predicate<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, V0U52PdObG5cOdqV(java.lang.String[] java.lang.String ), (Ljava/lang/String;)Z)((String[])CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c));
                                v370 = Collectors.toList();
                                v371 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                                if (true) ** GOTO lbl2127
                            }
                            v367 = -528917431 ^ 1353140735;
                        }
                        block278: while (true) {
                            v371 = (-4861891791536724443L ^ 2277049426203536103L) / v372;
lbl2127:
                            // 2 sources

                            switch ((int)v371) {
                                case -876892595: {
                                    break block278;
                                }
                                case 379160473: {
                                    v372 = -6019091105208171697L - -2200826172946859997L;
                                    continue block278;
                                }
                            }
                            break;
                        }
                        v373 /* !! */  = v369.collect(v370);
                        break block340;
                    }
                    while (true) {
                        if ((v374 = (cfr_temp_29 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (7748915614593635321L ^ -7819331043280066528L)) == 0L ? 0 : (cfr_temp_29 < 0L ? -1 : 1)) == false) continue;
                        v375 = -1048378933 ^ 1048378932;
                        if (v374 == v375) break;
                        v374 = 445798643 - -2014276315;
                    }
                    v373 /* !! */  = v376;
                    while (true) {
                        v377 = -6710526737185504890L - -3171512319031126589L;
                        cfr_temp_30 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - v377;
                        v378 = cfr_temp_30 == 0L ? 0 : (cfr_temp_30 < 0L ? -1 : 1);
                        if (v378 == false) continue;
                        if (v378 == (178619189 ^ -178619190)) {
                            v376 = new ArrayList<E>();
                            break;
                        }
                        v378 = 410729304 - 2011580669;
                    }
                }
                CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d1892cfda06 = v373 /* !! */ ;
                v379 = 965500007 ^ 965500004;
                -1606806719 ^ -949197954;
                v380 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[v379];
                while (true) {
                    v381 = -6117571131007068954L ^ -267578629272891844L;
                    cfr_temp_31 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - v381;
                    v382 = cfr_temp_31 == 0L ? 0 : (cfr_temp_31 < 0L ? -1 : 1);
                    if (v382 == false) continue;
                    if (v382 == (-1806977373 ^ 1806977372)) break;
                    v382 = 1463884597 - 578404552;
                }
                while (true) {
                    block341: {
                        v383 = 277741919484125828L >>> "\u0000\u0000".length();
                        cfr_temp_32 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - v383;
                        v384 = cfr_temp_32 == 0L ? 0 : (cfr_temp_32 < 0L ? -1 : 1);
                        if (v384 == false) continue;
                        v385 = 904302188 - 904302189;
                        if (v384 != v385) break block341;
                        v386 = new ArrayList<E>();
                        v387 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                        if (true) ** GOTO lbl2194
                    }
                    v384 = -862791369 ^ -1819417350;
                }
                block283: while (true) {
                    v387 = v388 / (2501499131285859864L ^ -6716825387157465645L);
lbl2194:
                    // 2 sources

                    switch ((int)v387) {
                        case -1615141395: {
                            v388 = 229987173792219058L ^ 7784546716940879506L;
                            continue block283;
                        }
                        case -876892595: {
                            return (List)StringUtil.copyPartialMatches((String)v380, (Iterable)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18ed9422f6, v386);
                        }
                        case -654547915: {
                            v388 = 8625584876379222583L ^ 4041315804788208999L;
                            continue block283;
                        }
                        case -635269101: {
                            v388 = -7694267199777165622L ^ 1370062158696662282L;
                            continue block283;
                        }
                    }
                    break;
                }
                return (List)StringUtil.copyPartialMatches((String)v380, (Iterable)CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18ed9422f6, v386);
            }
            v389 = -1675083406 ^ -1675083406;
            v390 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[v389];
            v391 = -1325491363 ^ -1325491368;
            var14_5 = new byte[v391];
            var14_5[-1830458800 ^ -1830458798] = -146587254 ^ -146587161;
            v392 = 1860441225 ^ 1860441226;
            var14_5[v392] = 633071809 ^ 633071784;
            v393 = -1609195882 ^ -1609195882;
            v394 = 586682776 ^ 586682873;
            var14_5[v393] = v394;
            var14_5[751362235 ^ 751362234] = 1142789919 ^ 1142790011;
            v395 = 1744282928 ^ 1744282932;
            var14_5[v395] = -1608518511 ^ -1608518401;
            v396 = new String(var14_5, "UTF-8");
            while (true) {
                if ((v397 = (cfr_temp_33 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-5108471307203008107L ^ 4185944087098519380L)) == 0L ? 0 : (cfr_temp_33 < 0L ? -1 : 1)) == false) continue;
                if (v397 == (182442758 ^ 182442759)) {
                    if (v390.equalsIgnoreCase(v396)) {
                        break;
                    }
                    break block308;
                }
                v397 = 962243618 - 2118060320;
            }
            v398 = 712233863 ^ 712233862;
            v399 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[v398];
            var14_5 = new byte[624576131 ^ 624576136];
            var14_5[-630551458 ^ -630551468] = -863657756 ^ -863657827;
            v400 = -2097224344 ^ -2097224424;
            var14_5[1040152681 ^ 1040152673] = v400;
            v401 = 2003356682 ^ 2003356684;
            var14_5[v401] = -96039511 ^ -96039488;
            v402 = -42169013 ^ -42169025;
            var14_5[357185892 ^ 357185895] = v402;
            var14_5[1114259678 ^ 1114259673] = 1757554360 ^ 1757554376;
            var14_5[-1363612191 ^ -1363612192] = 1791088813 ^ 1791088841;
            v403 = -43940538 ^ -43940566;
            var14_5[1011532435 ^ 1011532442] = v403;
            v404 = -966831923 ^ -966831921;
            var14_5[v404] = 1247363199 ^ 1247363094;
            v405 = 459645676 ^ 459645592;
            var14_5[-62198333 ^ -62198330] = v405;
            var14_5[-2006905010 ^ -2006905010] = 728482656 ^ 728482565;
            var14_5[-1260115092 ^ -1260115096] = 1683990364 ^ 1683990385;
            v406 = new String(var14_5, "UTF-8");
            while (true) {
                if ((v407 = (cfr_temp_34 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (1373024217670991396L ^ -3079630479179572465L)) == 0L ? 0 : (cfr_temp_34 < 0L ? -1 : 1)) == false) continue;
                if (v407 == 1168054857 - 1168054858) {
                    if (v399.equalsIgnoreCase(v406)) {
                        break;
                    }
                    break block308;
                }
                v407 = 331172521 ^ -2039310959;
            }
            v408 = CRACKME_6d0e6cd3_63a8_4a4d_876b_e4db0ee27d18aa4df54c[-211334287 ^ -211334286];
            v409 = new String[-1617224847 ^ -1617224845];
            var14_5 = new byte[-1709152584 ^ -1709152583];
            v410 = -1152679170 ^ -1152679211;
            var14_5[1243735949 ^ 1243735949] = v410;
            v409[-573251176 ^ -573251176] = new String(var14_5, "UTF-8");
            v411 = 883081864 ^ 883081865;
            v412 = -778638445 ^ -778638446;
            var14_5 = new byte[v412];
            v413 = -1908389501 ^ -1908389501;
            v414 = -892674558 ^ -892674513;
            var14_5[v413] = v414;
            v409[v411] = new String(var14_5, "UTF-8");
            v415 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
            block286: while (true) {
                switch ((int)v415) {
                    case -876892595: {
                        break block286;
                    }
                    case 1158878982: {
                        v415 = (2038371959621947262L ^ -2730755767023758271L) / (498156999741159681L - 6410563685825148254L);
                        continue block286;
                    }
                }
                break;
            }
            v416 = Arrays.asList(v409);
            v417 = 1249148103 ^ -1958231973;
            v418 = new ArrayList<E>();
            while (true) {
                v419 = -2893192589813264913L ^ -224203191276577312L;
                cfr_temp_35 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - v419;
                v420 = cfr_temp_35 == 0L ? 0 : (cfr_temp_35 < 0L ? -1 : 1);
                if (v420 == false) continue;
                if (v420 == -562100730 - -562100729) {
                    return (List)StringUtil.copyPartialMatches((String)v408, v416, v418);
                }
                v420 = -1727038295 - -995373391;
            }
        }
        while (true) {
            if ((v421 = (cfr_temp_36 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (-3618599771789265194L ^ 644784776068901721L)) == 0L ? 0 : (cfr_temp_36 < 0L ? -1 : 1)) == false) continue;
            if (v421 == (-161666425 ^ 161666424)) break;
            v421 = -367285499 - -1108188348;
        }
        while (true) {
            if ((v422 = (cfr_temp_37 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (3925601464125491533L - 1546239328048990003L)) == 0L ? 0 : (cfr_temp_37 < 0L ? -1 : 1)) == false) continue;
            if (v422 == (1095593207 ^ -1095593208)) {
                return new ArrayList<String>();
            }
            v422 = -1828083355 ^ -2144423064;
        }
    }

    /*
     * Unable to fully structure code
     */
    private static /* synthetic */ boolean V0U52PdObG5cOdqV(String[] var0, String var1_1) {
        if (\u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == -6550402261366601482L - -9150476114081426570L) {
            if ((1415177979 - 529415765 ^ -1633727589 - 513756060) != 0) {
                39456927 ^ 39456926;
            }
        } else {
            v0 = -8369294917501853750L == -8369294917501853749L ? -81259925 : 1767497716 ^ 1767497716;
        }
        -658975408 ^ 251905857;
        while (true) {
            block13: {
                if ((v1 = (cfr_temp_0 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (2930053111527153289L == 2930053111527153290L ? -3718131194388469540L : -5141927622355145692L ^ -5086358170905350611L)) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) {
                    continue;
                }
                if (v1 != (399598397 ^ -399598398)) break block13;
                v2 = CRACKME_f14ed8b5_cce0_4ed5_ad26_9363c494bcc9a37645bc.toLowerCase();
                v3 = CRACKME_f14ed8b5_cce0_4ed5_ad26_9363c494bcc9329edbbd[1010684047 ^ 1010684044].toLowerCase();
                v4 = \u0106s9V.CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
                if (true) ** GOTO lbl23
            }
            v1 = 598407822 ^ -297878342;
        }
        block7: while (true) {
            v4 = v5 / (-3639681206974240577L == -3639681206974240576L ? 2703927625656165969L : -7828290634324581131L - -316910977194338589L);
lbl23:
            // 2 sources

            switch ((int)v4) {
                case -1662028406: {
                    v5 = 4361291689951960304L ^ -9042067933687690332L;
                    continue block7;
                }
                case -1487846717: {
                    if (-9006702682058609374L == -9006702682058609373L) {
                        v5 = 9202013249742061628L;
                        continue block7;
                    }
                    v5 = -5587115367001953890L ^ 2069042923402100251L;
                    continue block7;
                }
                case -876892595: {
                    break block7;
                }
                case 1060765765: {
                    v5 = 1783722311966062501L ^ -896135167060015945L;
                    continue block7;
                }
            }
            break;
        }
        return v2.startsWith(v3);
    }

    private static /* synthetic */ String D4tv6pGsnl75Qoys(String string) {
        String CRACKME_eb34d0c1_9f8b_46dc_a395_024834ad9c018da3e17b;
        if (CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == (0x1F952E9FDFF3E651L ^ 0xC39A1AE768C021ACL)) {
            if (((3341512937566116839L == 3341512937566116840L ? -1265548904 : -698080868 >>> "\u0000\u0000".length()) ^ (0x30A12333 ^ 0x4F5EDCCC)) != 0) {
                int cfr_ignored_0 = 0x2A6F33E9 ^ 0x2A6F33E8;
            }
        } else {
            int cfr_ignored_1 = 0xC1B46906 ^ 0xC1B46906;
        }
        int n = -8154006470922986657L == -8154006470922986656L ? -398050596 : -115290072 - -2066508305;
        return \u0106s9V.phvIyAWdJNBKcWaD(CRACKME_eb34d0c1_9f8b_46dc_a395_024834ad9c018da3e17b);
    }

    public \u0106s9V() {
        \u0106s9V CRACKME_05491e25_27ca_408c_aee1_a4fd1ff6db696022ce28;
        if (CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == -3445740006248218273L - -250660043433640070L) {
            if ((0x8A822A25 ^ 0x1DCFF3A5 ^ (-432251112235596939L == -432251112235596938L ? -1122199143 : 0x29F359CE ^ 0x560CA631)) != 0) {
                int n = 6479127912873248011L == 6479127912873248012L ? -1343549418 : 0x7959D71A ^ 0x7959D71B;
            }
        } else {
            int cfr_ignored_0 = 0xA4A795E6 ^ 0xA4A795E6;
        }
    }

    /*
     * WARNING - void declaration
     */
    private static /* synthetic */ boolean gAHusKZY5Tkkp-ms(String string, String string2) {
        String CRACKME_406ab3de_09d0_4ae8_8dc0_2b0219c0d638408ef926;
        void CRACKME_406ab3de_09d0_4ae8_8dc0_2b0219c0d63885a8f1bb;
        if (CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == (291999361928479195L == 291999361928479196L ? 3566009420590031221L : 0xE6303BE004F6FE61L ^ 0xDABC82D506F36E68L)) {
            if ((-542434505 - -851650574 ^ 1059066169 - -1088417478) != 0) {
                int cfr_ignored_1 = 0x8F1DC9B4 ^ 0x8F1DC9B5;
            }
        } else {
            int cfr_ignored_2 = 0xD0717610 ^ 0xD0717610;
        }
        String string3 = CRACKME_406ab3de_09d0_4ae8_8dc0_2b0219c0d63885a8f1bb.toLowerCase();
        while (true) {
            long l;
            long l2;
            if ((l2 = (l = CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 - (7176411097227063190L == 7176411097227063191L ? 8169742267214486495L : 0x65F847068D5CF70DL ^ 0x870414878559ECFEL)) == 0L ? 0 : (l < 0L ? -1 : 1)) == false) continue;
            if (l2 == -768725388 - -768725387) break;
            l2 = 869882230 - 1866812267;
        }
        String string4 = CRACKME_406ab3de_09d0_4ae8_8dc0_2b0219c0d638408ef926.toLowerCase();
        long l = CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
        block5: while (true) {
            switch ((int)l) {
                case -876892595: {
                    break block5;
                }
                case 373824241: {
                    l = (0xD89E4D846E32FFE6L ^ 0x9DDD59DB5E73BB47L) / (-8119987098559439188L >>> "\u0000\u0000".length());
                    continue block5;
                }
            }
            break;
        }
        return string3.startsWith(string4);
    }

    private static /* synthetic */ String T3HGfeuH7MWvx-oy(String string) {
        String CRACKME_c6333436_bf25_42f2_87f9_20dbb19b31a72a766510;
        if (CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15 == -344876688774232858L - -3592839605159803833L) {
            if ((0x420C55DE ^ 0xAD3DD09 ^ (0xCD635A35 ^ 0xB29CA5CA)) != 0) {
                int n = 7981782287590249851L == 7981782287590249852L ? -1900282041 : 0x43D03EFD ^ 0x43D03EFC;
            }
        } else {
            int cfr_ignored_0 = 0xE834243B ^ 0xE834243B;
        }
        long l = CRACKME_3d615412_c2d7_4b5b_a1fa_db9ea2bf000b_100b8f15;
        block4: while (true) {
            switch ((int)l) {
                case -876892595: {
                    break block4;
                }
                case 519192140: {
                    l = (5204472331904283056L - 2273280152196515372L) / (-3711531250876860408L - -5708159407732661658L);
                    continue block4;
                }
            }
            break;
        }
        return \u0106s9V.phvIyAWdJNBKcWaD(CRACKME_c6333436_bf25_42f2_87f9_20dbb19b31a72a766510);
    }
}
