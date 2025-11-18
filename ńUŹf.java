/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  0QZ8
 *  8\u0105ja
 *  e4wV
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.command.TabCompleter
 *  zNb\u015b
 *  \u017b\u017c\u0107m
 */
import java.lang.invoke.LambdaMetafactory;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public class \u0144U\u0179f
implements TabCompleter,
CommandExecutor {
    private static final DecimalFormat 5fEW;
    public static long CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d = -2180888783137447789L;
    private static String[] CRACKME_BITCH;

    public \u0144U\u0179f() {
        if (CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d == (0xC6026F71A20E96C6L ^ 0x3D794B6186A8C59CL)) {
            if ((-481677126 - 653335854 ^ -661277006 - 1486206643) != 0) {
                int cfr_ignored_1 = 0xDA04DA ^ 0xDA04DB;
            }
        } else {
            int cfr_ignored_2 = 0xE7E9E5B8 ^ 0xE7E9E5B8;
        }
        int n = 1753413533096220350L == 1753413533096220351L ? -733826152 : 0xF827C4B7 ^ 0x5CB53294;
        while (true) {
            long l;
            long l2;
            if ((l2 = (l = CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (3656548023830334787L == 3656548023830334788L ? -8880725194374465634L : 0xFA151D0F88F26563L ^ 0x84753EE85AC6E255L)) == 0L ? 0 : (l < 0L ? -1 : 1)) == false) {
                continue;
            }
            if (l2 == (8987416789554083416L == 8987416789554083417L ? 1535956096 : 0xA4E8B590 ^ 0x5B174A6F)) {
                \u0144U\u0179f CRACKME_c3ce8624_0db8_4f6d_af32_3901fbb82156490d3ea6;
                return;
            }
            l2 = -588144641 - 565532498;
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block34: {
            block33: {
                \u0144U\u0179f.CRACKME_BITCH = new String[13];
                \u0144U\u0179f.CRACKME_BITCH[0] = "\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u28e0\u28e4\u28e4\u28e4\u28e4\u28e4\u28c4\u2840\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
                \u0144U\u0179f.CRACKME_BITCH[1] = "\u2800\u2800\u2800\u2800\u2800\u28b0\u287f\u280b\u2801\u2800\u2800\u2808\u2809\u2819\u283b\u28f7\u28c4\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
                \u0144U\u0179f.CRACKME_BITCH[2] = "\u2800\u2800\u2800\u2800\u2880\u28ff\u2807\u2800\u2880\u28f4\u28f6\u287e\u283f\u283f\u283f\u28bf\u28ff\u28e6\u2840\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
                \u0144U\u0179f.CRACKME_BITCH[3] = "\u2800\u2800\u28c0\u28c0\u28f8\u287f\u2800\u2800\u28b8\u28ff\u28c7\u2800\u2800\u2800\u2800\u2800\u2800\u2819\u28f7\u2840\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
                \u0144U\u0179f.CRACKME_BITCH[4] = "\u2800\u28fe\u285f\u281b\u28ff\u2847\u2800\u2800\u28b8\u28ff\u28ff\u28f7\u28e4\u28e4\u28e4\u28e4\u28f6\u28f6\u28ff\u2807\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u28c0\u2800\u2800";
                \u0144U\u0179f.CRACKME_BITCH[5] = "\u2880\u28ff\u2800\u2880\u28ff\u2847\u2800\u2800\u2800\u283b\u28bf\u28ff\u28ff\u28ff\u28ff\u28ff\u283f\u28ff\u284f\u2800\u2800\u2800\u2800\u28b4\u28f6\u28f6\u28ff\u28ff\u28ff\u28c6";
                \u0144U\u0179f.CRACKME_BITCH[6] = "\u28b8\u28ff\u2800\u28b8\u28ff\u2847\u2800\u2800\u2800\u2800\u2800\u2808\u2809\u2801\u2800\u2800\u2800\u28ff\u2847\u28c0\u28e0\u28f4\u28fe\u28ee\u28dd\u283f\u283f\u283f\u28fb\u285f";
                \u0144U\u0179f.CRACKME_BITCH[7] = "\u28b8\u28ff\u2800\u2818\u28ff\u2847\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u28e0\u28f6\u28fe\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u287f\u2801\u2809\u2800";
                \u0144U\u0179f.CRACKME_BITCH[8] = "\u2838\u28ff\u2800\u2800\u28ff\u2847\u2800\u2800\u2800\u2800\u2800\u28e0\u28fe\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u287f\u281f\u2809\u2800\u2800\u2800\u2800";
                \u0144U\u0179f.CRACKME_BITCH[9] = "\u2800\u283b\u28f7\u28f6\u28ff\u28c7\u2800\u2800\u2800\u28a0\u28fc\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28db\u28db\u28fb\u2809\u2801\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
                \u0144U\u0179f.CRACKME_BITCH[10] = "\u2800\u2800\u2800\u2800\u28b8\u28ff\u2800\u2800\u2800\u28b8\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u2847\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
                \u0144U\u0179f.CRACKME_BITCH[11] = "\u2800\u2800\u2800\u2800\u28b8\u28ff\u28c0\u28c0\u28c0\u28fc\u287f\u28bf\u28ff\u28ff\u28ff\u28ff\u28ff\u287f\u28ff\u28ff\u287f\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
                \u0144U\u0179f.CRACKME_BITCH[12] = "\u2800\u2800\u2800\u2800\u2800\u2819\u281b\u281b\u281b\u280b\u2801\u2800\u2819\u283b\u283f\u281f\u280b\u2811\u281b\u280b\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800";
                if (\u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d == (2998547995669009L ^ -2760647386785300068L)) {
                    if ((1536107964 ^ -1842225913 ^ (-2021458052 ^ -126025597)) != 0) {
                        1931004293 ^ 1931004292;
                    }
                } else {
                    -56393224 ^ -56393224;
                }
                v0 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                if (true) ** GOTO lbl33
                block21: while (true) {
                    v0 = v1 / (-5665163698513722798L - -4998768069497469119L);
lbl33:
                    // 2 sources

                    switch ((int)v0) {
                        case -1748726849: {
                            v1 = -6182098781393131840L ^ 6504508987148517526L;
                            continue block21;
                        }
                        case -495624045: {
                            break block21;
                        }
                        case 1998915735: {
                            v1 = 1784750704940921259L ^ -4404487921744842687L;
                            continue block21;
                        }
                    }
                    break;
                }
                CRACKME_0860dcc0_5492_4b3d_9d91_b465b092c29f584b4d9f = new DecimalFormatSymbols();
                v2 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                block22: while (true) {
                    switch ((int)v2) {
                        case -495624045: {
                            break block22;
                        }
                        case 1863036402: {
                            v2 = (-78162798206957174L - -2235621624261021105L) / (674640938990287099L - -6152682433586795805L);
                            continue block22;
                        }
                    }
                    break;
                }
                CRACKME_0860dcc0_5492_4b3d_9d91_b465b092c29f584b4d9f.setGroupingSeparator((char)(2139765809 ^ 2139765791));
                -578900755 ^ 1340707269;
                while (true) {
                    if ((v3 = (cfr_temp_0 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-746339397581040490L - 3805410756513655615L)) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) {
                        continue;
                    }
                    if (v3 == (-1554210253 ^ -1554210254)) {
                        var2_1 = new byte[1774783999 ^ 1774783994];
                        if (-6909850114851885585L == -6909850114851885584L) {
                            break;
                        }
                        break block33;
                    }
                    v3 = 791662882 - -1144611893;
                }
                v4 = 965478164;
                break block34;
            }
            v4 = 511870210 ^ 511870241;
        }
        var2_1[438713313 ^ 438713317] = v4;
        var2_1[-182278179 ^ -182278178] = -3880905961831066500L == -3880905961831066499L ? 297518658 : 642649494 ^ 642649525;
        var2_1[-4525253082792660637L == -4525253082792660636L ? -1920864619 : -1311012105 ^ -1311012105] = -4405298723917791191L == -4405298723917791190L ? 1421196771 : 609322033 ^ 609322002;
        var2_1[999304098 ^ 999304099] = -4605602779879885144L == -4605602779879885143L ? 1372390582 : 870901203 ^ 870901247;
        var2_1[-2495002497421368852L == -2495002497421368851L ? -779213708 : 859351120 ^ 859351122] = 1297082048 ^ 1297082083;
        v5 = new String(var2_1, "UTF-8");
        v6 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
        if (true) ** GOTO lbl84
        block24: while (true) {
            v6 = v7 / (-6501190644537922749L ^ 7827864990221643788L);
lbl84:
            // 2 sources

            switch ((int)v6) {
                case -671518270: {
                    v7 = -7042497405035793452L >>> "\u0000\u0000".length();
                    continue block24;
                }
                case -495624045: {
                    break block24;
                }
                case 609333430: {
                    v7 = 3438914172022684688L ^ -5223177398633510026L;
                    continue block24;
                }
                case 1445899266: {
                    v7 = 8442065415849700112L - 7590649566263016011L;
                    continue block24;
                }
            }
            break;
        }
        v8 = new DecimalFormat(v5, CRACKME_0860dcc0_5492_4b3d_9d91_b465b092c29f584b4d9f);
        -225344576 - 1567283859;
        v9 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
        if (true) ** GOTO lbl103
        block25: while (true) {
            v9 = v10 / (-452287623186637275L ^ 7668793724385930715L);
lbl103:
            // 2 sources

            switch ((int)v9) {
                case -1221358128: {
                    v10 = 5338785644613739177L ^ -3292220299305240394L;
                    continue block25;
                }
                case -495624045: {
                    break block25;
                }
                case -342695499: {
                    if (-6076672491084835784L == -6076672491084835783L) {
                        v10 = -5121771666210181384L;
                        continue block25;
                    }
                    v10 = -2178186914768169324L ^ -647589032661102731L;
                    continue block25;
                }
                case 753803558: {
                    v10 = -1741744481473025900L ^ 8833952931758670933L;
                    continue block25;
                }
            }
            break;
        }
        \u0144U\u0179f.5fEW = v8;
    }

    /*
     * Exception decompiling
     */
    public static String BAaxHy1acFsWt1rb(String var0) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 15[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
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
     * Opcode count of 19598 triggered aggressive code reduction.  Override with --aggressivesizethreshold.
     * Unable to fully structure code
     */
    public boolean onCommand(@NotNull CommandSender var1_1, @NotNull Command var2_2, @NotNull String var3_3, @NotNull String[] var4_4) {
        block573: {
            block602: {
                block598: {
                    block589: {
                        block572: {
                            block571: {
                                block585: {
                                    block570: {
                                        block584: {
                                            block569: {
                                                block567: {
                                                    block568: {
                                                        block576: {
                                                            block575: {
                                                                block574: {
                                                                    if (\u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d == (3464876934247891077L ^ 209484452444127122L)) {
                                                                        v0 = -858981656 ^ -1288501993;
                                                                        if ((754514788 >>> "\u0000\u0000".length() ^ v0) != 0) {
                                                                            -364052487 ^ -364052488;
                                                                        }
                                                                    } else {
                                                                        v1 = 61557680 ^ 61557680;
                                                                    }
                                                                    v2 = 1911358590 ^ 1911358580;
                                                                    var18_5 = new byte[v2];
                                                                    var18_5[-1230158812 ^ -1230158811] = -956812802 ^ -956812918;
                                                                    v3 = -463862241 ^ -463862247;
                                                                    var18_5[v3] = -1922886709 ^ -1922886737;
                                                                    v4 = 1220542599 ^ 1220542698;
                                                                    var18_5[-1684152652 ^ -1684152653] = v4;
                                                                    var18_5[-1509590979 ^ -1509590988] = -1993559535 ^ -1993559425;
                                                                    var18_5[-1478683048 ^ -1478683045] = -937314570 ^ -937314667;
                                                                    var18_5[-309691614 ^ -309691610] = -1716364964 ^ -1716364942;
                                                                    var18_5[1184545950 ^ 1184545947] = 37456081 ^ 37456048;
                                                                    var18_5[-458591614 ^ -458591606] = 1093901781 ^ 1093901756;
                                                                    var18_5[-907495239 ^ -907495237] = -1278540707 ^ -1278540751;
                                                                    var18_5[1124598242 ^ 1124598242] = -100157871 ^ -100157896;
                                                                    v5 = new String(var18_5, "UTF-8");
                                                                    v6 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                                    if (true) ** GOTO lbl32
                                                                    block391: while (true) {
                                                                        v6 = v7 / (-5079026128783547241L - 3158052997578537422L);
lbl32:
                                                                        // 2 sources

                                                                        switch ((int)v6) {
                                                                            case -2002293690: {
                                                                                v7 = 328570366935270925L ^ 3859173676618346123L;
                                                                                continue block391;
                                                                            }
                                                                            case -529798056: {
                                                                                v7 = -3630655509230929915L ^ -6470932820123337672L;
                                                                                continue block391;
                                                                            }
                                                                            case -495624045: {
                                                                                break block391;
                                                                            }
                                                                            case 199578856: {
                                                                                v7 = -4523426209026721564L ^ -6928186807998443637L;
                                                                                continue block391;
                                                                            }
                                                                        }
                                                                        break;
                                                                    }
                                                                    if (CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.hasPermission(v5)) break block574;
                                                                    v8 = 490897986 ^ 490897928;
                                                                    var18_5 = new byte[v8];
                                                                    var18_5[522874314 ^ 522874326] = -1869298901 ^ -1869298856;
                                                                    var18_5[1612504704 ^ 1612504723] = 1477339363 ^ 1477339331;
                                                                    var18_5[1759919062 ^ 1759919058] = -720955510 ^ -720955411;
                                                                    var18_5[386726238 ^ 386726239] = 1428215461 ^ 1428215509;
                                                                    var18_5[1367785388 ^ 1367785401] = 62697210 ^ 62697113;
                                                                    var18_5[1847625945 ^ 1847625976] = -1367863531 ^ -1367863450;
                                                                    var18_5[-1944204029 ^ -1944204009] = 21118916 ^ 21118946;
                                                                    v9 = -285480805 ^ -285480730;
                                                                    var18_5[232832715 ^ 232832710] = v9;
                                                                    var18_5[1569679305 ^ 1569679233] = -922824881 ^ -922824841;
                                                                    var18_5[1654792886 ^ 1654792841] = -1189771089 ^ -1189771069;
                                                                    v10 = -835155489 ^ -835155457;
                                                                    var18_5[v10] = 2128022138 ^ 2128022037;
                                                                    var18_5[-1544265232 ^ -1544265274] = 542632723 ^ 542632754;
                                                                    v11 = 634901237 ^ 634901203;
                                                                    var18_5[v11] = 1399029798 ^ 1399029843;
                                                                    var18_5[-996374116 ^ -996374049] = 2047007531 ^ 2047007567;
                                                                    v12 = 367674871 ^ -367674770;
                                                                    var18_5[-1298645229 ^ -1298645193] = v12;
                                                                    var18_5[-1473247880 ^ -1473247925] = -236724325 ^ -236724235;
                                                                    v13 = 158859880 ^ 158859885;
                                                                    v14 = 1120830875 ^ 1120830962;
                                                                    var18_5[v13] = v14;
                                                                    var18_5[-1913189664 ^ -1913189682] = -21324545 ^ -21324577;
                                                                    v15 = -2099940392 ^ -2099940407;
                                                                    var18_5[v15] = 492922830 ^ 492922864;
                                                                    var18_5[1411909704 ^ 1411909739] = 1876913642 ^ -1876913618;
                                                                    var18_5[-1507921633 ^ -1507921641] = 509915771 ^ 509915657;
                                                                    var18_5[-151442893 ^ -151442908] = -1640514865 ^ -1640514906;
                                                                    var18_5[-1849607395 ^ -1849607397] = -201807000 ^ -201807098;
                                                                    v16 = -1757775234 ^ -1757775340;
                                                                    var18_5[293177834 ^ 293177799] = v16;
                                                                    var18_5[-2045260066 ^ -2045260054] = 1170115758 ^ 1170115786;
                                                                    v17 = 490722853 ^ 490722913;
                                                                    var18_5[v17] = 1866829322 ^ 1866829415;
                                                                    v18 = 1602423255 ^ 1602423242;
                                                                    v19 = 617563448 ^ 617563458;
                                                                    var18_5[v18] = v19;
                                                                    v20 = 402983650 ^ 402983637;
                                                                    var18_5[v20] = -1807683488 ^ -1807683520;
                                                                    v21 = -2118031417 ^ -2118031445;
                                                                    var18_5[-1109200954 ^ -1109200956] = v21;
                                                                    var18_5[999676031 ^ 999676007] = -169478322 ^ -169478357;
                                                                    var18_5[-1528734826 ^ -1528734835] = 1337668799 ^ 1337668830;
                                                                    var18_5[812371939 ^ 812371910] = 1161705888 ^ 1161705936;
                                                                    var18_5[-424302290 ^ -424302225] = -1846146893 ^ -1846146915;
                                                                    var18_5[-1631834411 ^ -1631834411] = 598187409 ^ 598187498;
                                                                    v22 = -2109426907 ^ -2109426879;
                                                                    var18_5[-715072950 ^ -715072926] = v22;
                                                                    var18_5[955703425 ^ 955703482] = 1678310995 ^ 1678311029;
                                                                    var18_5[976073227 ^ 976073282] = -1462605215 ^ -1462605240;
                                                                    v23 = -1406771060 ^ -1406771018;
                                                                    var18_5[v23] = 1063267351 ^ 1063267391;
                                                                    v24 = 1352658550 ^ 1352658461;
                                                                    var18_5[1337465782 ^ 1337465753] = v24;
                                                                    var18_5[240402847 ^ 240402816] = 1574867598 ^ 1574867690;
                                                                    var18_5[424287978 ^ 424287963] = 558439497 ^ 558439460;
                                                                    v25 = -2015941168 ^ -2015941225;
                                                                    var18_5[v25] = 408758015 ^ 408757977;
                                                                    v26 = 1550449816 ^ 1550449917;
                                                                    var18_5[1446764583 ^ 1446764555] = v26;
                                                                    v27 = 1938979767 ^ 1938979769;
                                                                    v28 = -533679662 ^ -533679630;
                                                                    var18_5[v27] = v28;
                                                                    var18_5[110079506 ^ 110079531] = -126055588 ^ -126055580;
                                                                    v29 = -304827878 ^ -304827780;
                                                                    var18_5[-631308204 ^ -631308194] = v29;
                                                                    var18_5[-1163220603 ^ -1163220547] = -1556773854 ^ -1556773884;
                                                                    var18_5[-1590059020 ^ -1590059034] = 1349138437 ^ 1349138491;
                                                                    var18_5[-1721139919 ^ -1721139944] = 1923586552 ^ 1923586455;
                                                                    v30 = -1509056619 ^ -1509056615;
                                                                    var18_5[v30] = -2069214289 ^ -2069214249;
                                                                    v31 = -1394373354 ^ -1394373368;
                                                                    v32 = -77494736 ^ -77494768;
                                                                    var18_5[v31] = v32;
                                                                    v33 = -587037529 ^ -587037471;
                                                                    var18_5[v33] = -283582615 ^ -283582713;
                                                                    v34 = 933443471 ^ 933443514;
                                                                    v35 = -1021203999 ^ -1021204072;
                                                                    var18_5[v34] = v35;
                                                                    var18_5[-2098595978 ^ -2098596015] = 201569761 ^ 201569729;
                                                                    var18_5[2081655546 ^ 2081655482] = 1797994483 ^ 1797994384;
                                                                    v36 = 1767401916 ^ 1767401893;
                                                                    var18_5[v36] = 1444788304 ^ 1444788336;
                                                                    var18_5[-700399533 ^ -700399506] = 5664840 ^ 5664801;
                                                                    var18_5[-1199126366 ^ -1199126357] = -844728626 ^ -844728661;
                                                                    v37 = -1734556556 ^ -1734556557;
                                                                    var18_5[v37] = -52376208 ^ -52376288;
                                                                    var18_5[-1640565495 ^ -1640565461] = -598125607 ^ -598125651;
                                                                    v38 = -737557077 ^ -737557054;
                                                                    var18_5[-1494770669 ^ -1494770664] = v38;
                                                                    v39 = 1827522032 ^ 1827522011;
                                                                    var18_5[v39] = 1936240097 ^ 1936240021;
                                                                    v40 = -695577999 ^ -695578039;
                                                                    var18_5[680309488 ^ 680309472] = v40;
                                                                    v41 = 708259137 ^ 708259138;
                                                                    var18_5[v41] = 325633184 ^ 325633237;
                                                                    v42 = -1119689369 ^ -1119689470;
                                                                    var18_5[129115055 ^ 129115037] = v42;
                                                                    var18_5[2004805509 ^ 2004805561] = 1668895898 ^ 1668895917;
                                                                    var18_5[521609182 ^ 521609156] = 300853738 ^ 300853639;
                                                                    var18_5[-1355014296 ^ -1355014314] = 690652084 ^ 690652096;
                                                                    v43 = -2083503681 ^ -2083503622;
                                                                    var18_5[v43] = 1813771506 ^ 1813771419;
                                                                    v44 = -1955704250 ^ -1955704212;
                                                                    var18_5[v44] = 393812301 ^ 393812333;
                                                                    var18_5[1500768916 ^ 1500768982] = 697294513 ^ 697294544;
                                                                    v45 = -843123345 ^ -843123383;
                                                                    var18_5[1126939413 ^ 1126939418] = v45;
                                                                    v46 = -1087084876 ^ -1087084837;
                                                                    var18_5[2038483811 ^ 2038483795] = v46;
                                                                    v47 = 1601496538 ^ 1601496468;
                                                                    var18_5[-745069631 ^ -745069609] = v47;
                                                                    v48 = new String(var18_5, "UTF-8");
                                                                    v49 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                                    if (true) ** GOTO lbl174
                                                                }
                                                                -926427973 - 142400830;
                                                                if (((void)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa13086deb9).length == 0) break block575;
                                                                break block576;
                                                                block392: while (true) {
                                                                    v49 = v50 / (6372468939008505787L ^ -7109032529619542889L);
lbl174:
                                                                    // 2 sources

                                                                    switch ((int)v49) {
                                                                        case -495624045: {
                                                                            break block392;
                                                                        }
                                                                        case 909780588: {
                                                                            v50 = 7468330516905674734L - 5201774012232267737L;
                                                                            continue block392;
                                                                        }
                                                                        case 979870840: {
                                                                            v50 = 7135836299792901323L - -4849636437722002249L;
                                                                            continue block392;
                                                                        }
                                                                    }
                                                                    break;
                                                                }
                                                                CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.sendMessage(0QZ8.1d9kfLKTTP4TVZYm((String)v48));
                                                                return (boolean)(2080438482 ^ 2080438483);
                                                            }
                                                            -1965440039 ^ 1307997372;
                                                            v51 = 1147091813 - 564598980;
                                                            v52 = 402744054 ^ 402744016;
                                                            var18_5 = new byte[v52];
                                                            var18_5[-743698597 ^ -743698565] = 104279231 ^ 104279252;
                                                            v53 = -523552963 ^ -523552960;
                                                            var18_5[-318698128 ^ -318698115] = v53;
                                                            v54 = -861090017 ^ -861090025;
                                                            var18_5[v54] = -54984439 ^ -54984325;
                                                            v55 = -1516748757 ^ -1516748768;
                                                            v56 = -921173069 ^ -921173030;
                                                            var18_5[v55] = v56;
                                                            v57 = -186224697 ^ -186224683;
                                                            var18_5[v57] = -722911964 ^ -722911974;
                                                            var18_5[901386766 ^ 901386756] = -1605172056 ^ -1605172018;
                                                            v58 = -68276984 ^ -68276974;
                                                            var18_5[v58] = -636432137 ^ -636432251;
                                                            var18_5[363089365 ^ 363089361] = 136160670 ^ 136160761;
                                                            v59 = -500037552 ^ -500037560;
                                                            v60 = 1919661007 ^ 1919660988;
                                                            var18_5[v59] = v60;
                                                            var18_5[1454973461 ^ 1454973494] = 458180458 ^ 458180359;
                                                            v61 = 297241422 ^ 297241424;
                                                            var18_5[v61] = -736523790 ^ -736523877;
                                                            var18_5[1909804956 ^ 1909804990] = -18102265 ^ -18102170;
                                                            var18_5[-828031373 ^ -828031366] = 716347321 ^ 716347356;
                                                            var18_5[1694116274 ^ 1694116261] = -1957681960 ^ -1957682003;
                                                            var18_5[706311214 ^ 706311178] = 560084829 ^ 560084792;
                                                            v62 = -589350113 ^ -589350141;
                                                            var18_5[v62] = -1163923144 ^ -1163923196;
                                                            v63 = -272327233 ^ -272327249;
                                                            v64 = -1791132754 ^ -1791132778;
                                                            var18_5[v63] = v64;
                                                            var18_5[1516851895 ^ 1516851878] = 1634512175 ^ 1634512145;
                                                            var18_5[1519543041 ^ 1519543041] = -824187306 ^ -824187347;
                                                            var18_5[608609388 ^ 608609357] = -1549199887 ^ -1549199969;
                                                            var18_5[-1699741547 ^ -1699741546] = -809910822 ^ -809910865;
                                                            var18_5[-1200186578 ^ -1200186573] = -245192902 ^ -245192876;
                                                            var18_5[-1233223556 ^ -1233223554] = -911345389 ^ -911345281;
                                                            v65 = -1841519655 ^ -1841519670;
                                                            var18_5[v65] = -192554944 ^ -192554912;
                                                            var18_5[-666426822 ^ -666426826] = -1801815830 ^ -1801815918;
                                                            var18_5[-1375559645 ^ -1375559625] = 1669817635 ^ 1669817605;
                                                            var18_5[1413029693 ^ 1413029666] = -1579608393 ^ -1579608364;
                                                            v66 = 1135716566 ^ 1135716536;
                                                            var18_5[1734800885 ^ 1734800883] = v66;
                                                            v67 = -626246534 ^ -626246566;
                                                            var18_5[1193507846 ^ 1193507848] = v67;
                                                            var18_5[474601149 ^ 474601144] = 589505965 ^ 589505988;
                                                            var18_5[-774333132 ^ -774333139] = 1701244077 ^ 1701244104;
                                                            var18_5[-262268966 ^ -262268991] = 403991853 ^ 403991821;
                                                            v68 = 2115771458 ^ 2115771461;
                                                            v69 = -359171181 ^ -359171133;
                                                            var18_5[v68] = v69;
                                                            var18_5[-225559119 ^ -225559148] = 1409972736 ^ 1409972798;
                                                            v70 = 752657099 ^ 752657098;
                                                            var18_5[v70] = -533401256 ^ -533401304;
                                                            v71 = -1578649405 ^ -1578649364;
                                                            var18_5[-340150377 ^ -340150399] = v71;
                                                            v72 = 925323511 ^ 925323490;
                                                            var18_5[v72] = 263210845 ^ 263210858;
                                                            v73 = 1522392145 ^ 1522392158;
                                                            var18_5[v73] = -1906405057 ^ -1906405095;
                                                            v74 = 0QZ8.1d9kfLKTTP4TVZYm((String)new String(var18_5, "UTF-8"));
                                                            -640134840 - 99328909;
                                                            while (true) {
                                                                v75 = -7484038762850071981L - -6362907295230434701L;
                                                                cfr_temp_0 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v75;
                                                                v76 = cfr_temp_0 == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                                                                if (v76 == false) continue;
                                                                if (v76 == (-532699419 ^ 532699418)) {
                                                                    CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.sendMessage(v74);
                                                                    return (boolean)(-819958939 ^ -819958940);
                                                                }
                                                                v76 = 2143617450 ^ -1779431845;
                                                            }
                                                        }
                                                        v77 = CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa13086deb9[-1640693835 ^ -1640693835];
                                                        v78 = 1142524211 ^ 1142524210;
                                                        var18_5 = new byte[v78];
                                                        v79 = -1499553791 ^ -1499553745;
                                                        var18_5[210539160 ^ 210539160] = v79;
                                                        v80 = new String(var18_5, "UTF-8");
                                                        var18_5 = new byte[1342487634 ^ 1342487633];
                                                        var18_5[2075521088 ^ 2075521088] = -387484352 ^ -387484354;
                                                        var18_5[1714033271 ^ 1714033270] = 1230271909 ^ 1230271894;
                                                        v81 = 309499566 ^ 309499600;
                                                        var18_5[1346172858 ^ 1346172856] = v81;
                                                        v82 = new String(var18_5, "UTF-8");
                                                        while (true) {
                                                            v83 = 3423136511035831602L ^ -414891033102859073L;
                                                            cfr_temp_1 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v83;
                                                            v84 = cfr_temp_1 == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1);
                                                            if (v84 == false) continue;
                                                            v85 = -1428454397 ^ -1428454398;
                                                            if (v84 == v85) break;
                                                            v84 = 1390895759 ^ -1539483831;
                                                        }
                                                        var5_6 = v77.replace(v80, v82);
                                                        while (true) {
                                                            if ((v86 = (cfr_temp_2 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (3635711372212977772L - 7024274040448788272L)) == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1)) == false) continue;
                                                            if (v86 == (-235511287 ^ -235511288)) break;
                                                            v86 = -661951703 ^ 566999657;
                                                        }
                                                        v87 = zNb\u015b.Y\u017btq.getConfig();
                                                        v88 = 788764697 ^ 788764690;
                                                        var18_5 = new byte[v88];
                                                        var18_5[912251736 ^ 912251737] = -672855510 ^ -672855485;
                                                        v89 = -1236984946 ^ -1236984956;
                                                        var18_5[v89] = -1628146363 ^ -1628146400;
                                                        v90 = -1482838060 ^ -1482838051;
                                                        v91 = 322895064 ^ 322895029;
                                                        var18_5[v90] = v91;
                                                        var18_5[1062725697 ^ 1062725702] = -1094935407 ^ -1094935297;
                                                        var18_5[544355562 ^ 544355564] = 846274132 ^ 846274059;
                                                        var18_5[-99702422 ^ -99702422] = -229775279 ^ -229775323;
                                                        v92 = -1014237210 ^ -1014237214;
                                                        var18_5[v92] = -649493307 ^ -649493334;
                                                        v93 = 699821343 ^ 699821335;
                                                        v94 = 137607751 ^ 137607718;
                                                        var18_5[v93] = v94;
                                                        var18_5[2134204744 ^ 2134204747] = 1072339550 ^ 1072339498;
                                                        var18_5[29622492 ^ 29622489] = -1544278601 ^ -1544278564;
                                                        v95 = -1642009563 ^ -1642009522;
                                                        var18_5[1880566342 ^ 1880566340] = v95;
                                                        v96 = new String(var18_5, "UTF-8");
                                                        while (true) {
                                                            if ((v97 = (cfr_temp_3 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (4805164065348181398L - -1754319154008171414L)) == 0L ? 0 : (cfr_temp_3 < 0L ? -1 : 1)) == false) continue;
                                                            if (v97 == (-156439629 ^ 156439628)) {
                                                                -814925145 - -1628698632;
                                                                CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa11684f519 = v87.getString(v96);
                                                                23626936 ^ 1067279273;
                                                                if (CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa11684f519 != null) {
                                                                    break;
                                                                }
                                                                break block567;
                                                            }
                                                            v97 = 1396339308 >>> "\u0000\u0000".length();
                                                        }
                                                        v98 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                        if (true) ** GOTO lbl350
                                                        block397: while (true) {
                                                            v98 = v99 / (460367983484911138L - -4125675716318693749L);
lbl350:
                                                            // 2 sources

                                                            switch ((int)v98) {
                                                                case -495624045: {
                                                                    break block397;
                                                                }
                                                                case -224261625: {
                                                                    v99 = -7475050593675828789L - -7977093632295604953L;
                                                                    continue block397;
                                                                }
                                                                case 1187461550: {
                                                                    v99 = -181625154587140789L ^ -7937426210926681995L;
                                                                    continue block397;
                                                                }
                                                                case 1377820304: {
                                                                    v99 = -6568365865259376622L ^ 972855372602496233L;
                                                                    continue block397;
                                                                }
                                                            }
                                                            break;
                                                        }
                                                        if (CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa11684f519.isEmpty()) break block567;
                                                        var18_5 = new byte[798167948 ^ 798167949];
                                                        var18_5[606171274 ^ 606171274] = 985278905 ^ 985278873;
                                                        v100 = new String(var18_5, "UTF-8");
                                                        v101 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                        if (true) ** GOTO lbl372
                                                        block398: while (true) {
                                                            v102 = -4612458065760372021L - -3068313096742049807L;
                                                            v101 = v103 / v102;
lbl372:
                                                            // 2 sources

                                                            switch ((int)v101) {
                                                                case -495624045: {
                                                                    break block398;
                                                                }
                                                                case -87250039: {
                                                                    v103 = -2277158394196312960L ^ 4840804763307248633L;
                                                                    continue block398;
                                                                }
                                                                case 1544483935: {
                                                                    v103 = 714435097377691586L ^ 8273241245786708775L;
                                                                    continue block398;
                                                                }
                                                                case 1640768916: {
                                                                    v103 = 6496688946990072700L >>> "\u0000\u0000".length();
                                                                    continue block398;
                                                                }
                                                            }
                                                            break;
                                                        }
                                                        if (CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa11684f519.equalsIgnoreCase(v100)) break block567;
                                                        var18_5 = new byte[1389559348 ^ 1389559358];
                                                        v104 = -1322868115 ^ -1322868120;
                                                        var18_5[v104] = 2054863236 ^ 2054863338;
                                                        var18_5[502929018 ^ 502929019] = 1546388753 ^ 1546388838;
                                                        v105 = -1684455115 ^ -1684455108;
                                                        v106 = 119557787 ^ 119557882;
                                                        var18_5[v105] = v106;
                                                        var18_5[-1608720640 ^ -1608720636] = 576263099 ^ 576263130;
                                                        var18_5[476977442 ^ 476977442] = 759311322 ^ 759311278;
                                                        var18_5[-1326206094 ^ -1326206092] = 1884020423 ^ 1884020390;
                                                        v107 = 747905706 ^ 747905704;
                                                        var18_5[v107] = -572215385 ^ -572215352;
                                                        var18_5[2059256432 ^ 2059256435] = -1594000843 ^ -1594000801;
                                                        var18_5[1130902778 ^ 1130902781] = 1527093390 ^ 1527093492;
                                                        v108 = -727155713 ^ -727155721;
                                                        var18_5[v108] = -2073047981 ^ -2073048028;
                                                        v109 = new String(var18_5, "UTF-8");
                                                        559396778 ^ -1001191433;
                                                        while (true) {
                                                            v110 = -1062089510222932698L ^ -394683017744957198L;
                                                            cfr_temp_4 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v110;
                                                            v111 = cfr_temp_4 == 0L ? 0 : (cfr_temp_4 < 0L ? -1 : 1);
                                                            if (v111 == false) continue;
                                                            v112 = 1353926748 - 1353926749;
                                                            if (v111 == v112) {
                                                                if (CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa11684f519.equalsIgnoreCase(v109)) {
                                                                    break;
                                                                }
                                                                break block568;
                                                            }
                                                            v111 = -1834029517 - -2038574098;
                                                        }
                                                        break block567;
                                                    }
                                                    v113 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                    if (true) ** GOTO lbl567
                                                }
                                                v114 = -1089629791 ^ -1776396864;
                                                924465699 - -1511109114;
                                                v115 = -1687526162 ^ -1687526232;
                                                var18_5 = new byte[v115];
                                                var18_5[1750057423 ^ 1750057468] = -124941544 ^ -124941521;
                                                v116 = 1727006657 ^ 1727006689;
                                                var18_5[1838242013 ^ 1838242028] = v116;
                                                var18_5[1908074391 ^ 1908074414] = 87973490 ^ 87973393;
                                                v117 = -1329260033 ^ -1329260035;
                                                v118 = -971584832 ^ -971584852;
                                                var18_5[v117] = v118;
                                                v119 = -1448910782 ^ -1448910730;
                                                v120 = 1510570718 ^ 1510570737;
                                                var18_5[v119] = v120;
                                                var18_5[-1814774185 ^ -1814774166] = 30312558 ^ 30312475;
                                                var18_5[-553362657 ^ -553362629] = 1017317084 ^ 1017317029;
                                                var18_5[-1268065911 ^ -1268065884] = 712973630 ^ 712973639;
                                                var18_5[-1847050421 ^ -1847050415] = -698162308 ^ -698162415;
                                                var18_5[653682589 ^ 653682572] = -1150182592 ^ -1150182530;
                                                var18_5[-342577219 ^ -342577275] = 1704699001 ^ 1704698904;
                                                var18_5[1665971051 ^ 1665971037] = -1931312676 ^ -1931312717;
                                                v121 = 1885477229 ^ 1885477198;
                                                var18_5[v121] = -1814154129 ^ -1814154219;
                                                v122 = 1953669159 ^ 1953669144;
                                                var18_5[v122] = -314848681 ^ -314848718;
                                                v123 = -961166293 ^ -961166248;
                                                var18_5[1912477862 ^ 1912477831] = v123;
                                                v124 = -545161268 ^ -545161236;
                                                var18_5[-1391401725 ^ -1391401693] = v124;
                                                var18_5[-803595479 ^ -803595473] = 1880990577 ^ 1880990495;
                                                v125 = -1086256496 ^ -1086256482;
                                                var18_5[v125] = 1627295008 ^ 1627294976;
                                                v126 = 933450640 ^ 933450724;
                                                var18_5[1693081570 ^ 1693081543] = v126;
                                                v127 = 1116822824 ^ 1116822792;
                                                var18_5[-310548554 ^ -310548561] = v127;
                                                v128 = -871129593 ^ -871129473;
                                                var18_5[1195166966 ^ 1195166970] = v128;
                                                v129 = 478378927 ^ 478378914;
                                                var18_5[v129] = 1028248237 ^ 1028248272;
                                                v130 = 1496732500 ^ 1496732536;
                                                v131 = 608688390 ^ 608688488;
                                                var18_5[v130] = v131;
                                                var18_5[-26386010 ^ -26386020] = -37025116 ^ -37025058;
                                                v132 = 349801 ^ 349706;
                                                var18_5[-1783171590 ^ -1783171624] = v132;
                                                var18_5[-1627279634 ^ -1627279676] = 917940456 ^ 917940364;
                                                var18_5[1888981008 ^ 1888981055] = 1145559480 ^ 1145559504;
                                                var18_5[405897762 ^ 405897766] = -709025909 ^ -709025812;
                                                v133 = 1380597753 ^ 1380597727;
                                                var18_5[818520426 ^ 818520446] = v133;
                                                var18_5[-1509319174 ^ -1509319190] = -259399700 ^ -259399724;
                                                v134 = 1796538474 ^ 1796538475;
                                                var18_5[v134] = 1076079255 ^ 1076079335;
                                                v135 = 290129648 ^ 290129567;
                                                var18_5[-554671111 ^ -554671134] = v135;
                                                var18_5[-619224323 ^ -619224334] = -1185022962 ^ -1185022936;
                                                v136 = 1247534328 ^ 1247534331;
                                                v137 = -1110122892 ^ -1110123007;
                                                var18_5[v136] = v137;
                                                var18_5[564154453 ^ 564154450] = 1377265853 ^ 1377265901;
                                                var18_5[482499626 ^ 482499618] = -598893355 ^ -598893401;
                                                var18_5[1299049870 ^ 1299049933] = -1078735409 ^ -1078735454;
                                                v138 = 1198408529 ^ 1198408500;
                                                var18_5[-1474227443 ^ -1474227383] = v138;
                                                v139 = 2049113775 ^ 2049113806;
                                                var18_5[1190400660 ^ 1190400703] = v139;
                                                v140 = 1940034649 ^ 1940034616;
                                                var18_5[-1835425868 ^ -1835425902] = v140;
                                                var18_5[1659128798 ^ 1659128809] = -1916624424 ^ -1916624460;
                                                v141 = 926658325 ^ 926658388;
                                                v142 = 2058326566 ^ 2058326600;
                                                var18_5[v141] = v142;
                                                v143 = 552129164 ^ 552129182;
                                                var18_5[v143] = -914868704 ^ -914868706;
                                                var18_5[295881360 ^ 295881356] = 1954143543 ^ -1954143502;
                                                v144 = 312633107 ^ 312633135;
                                                var18_5[v144] = -1399229448 ^ -1399229500;
                                                v145 = 1740089047 ^ 1740089079;
                                                var18_5[-1620063330 ^ -1620063323] = v145;
                                                var18_5[253565622 ^ 253565601] = -529594457 ^ -529594418;
                                                var18_5[2041725316 ^ 2041725327] = 1424488707 ^ 1424488810;
                                                var18_5[1836494212 ^ 1836494235] = -1120378197 ^ -1120378166;
                                                v146 = 1758440687 ^ -1758440621;
                                                var18_5[-776070354 ^ -776070349] = v146;
                                                var18_5[267700726 ^ 267700675] = 899859230 ^ 899859310;
                                                v147 = 10367457 ^ -10367451;
                                                var18_5[527440107 ^ 527440076] = v147;
                                                v148 = 347909989 ^ 347909984;
                                                var18_5[v148] = 1452151655 ^ 1452151566;
                                                v149 = 1466786555 ^ 1466786507;
                                                var18_5[v149] = 190648466 ^ 190648499;
                                                v150 = -385216124 ^ -385216027;
                                                var18_5[-335037158 ^ -335037096] = v150;
                                                var18_5[1500367462 ^ 1500367462] = 1712294347 ^ 1712294320;
                                                v151 = -1932366790 ^ -1932366732;
                                                var18_5[-656889158 ^ -656889172] = v151;
                                                var18_5[-1130806771 ^ -1130806754] = -309594314 ^ -309594346;
                                                var18_5[-2121016421 ^ -2121016357] = 2054598128 ^ 2054598018;
                                                var18_5[1523980034 ^ 1523980055] = -1702106137 ^ -1702106236;
                                                var18_5[709615478 ^ 709615487] = -1641392391 ^ -1641392484;
                                                var18_5[1148147233 ^ 1148147208] = -384321990 ^ -384322022;
                                                v152 = -2054658961 ^ -2054658991;
                                                var18_5[1842748575 ^ 1842748634] = v152;
                                                var18_5[-264573000 ^ -264573034] = -444773268 ^ -444773361;
                                                var18_5[1604763475 ^ 1604763469] = -1680131381 ^ -1680131419;
                                                v153 = -432111022 ^ -432111016;
                                                var18_5[v153] = 847837302 ^ 847837200;
                                                v154 = -1267462618 ^ -1267462642;
                                                v155 = -1395849067 - -1395848946;
                                                var18_5[v154] = v155;
                                                var18_5[215783372 ^ 215783422] = 659414700 ^ 659414666;
                                                var18_5[-1449123717 ^ -1449123741] = -1714709208 ^ -1714709171;
                                                var18_5[1159356325 ^ 1159356315] = 921314012 ^ 921313967;
                                                v156 = new String(var18_5, "UTF-8");
                                                while (true) {
                                                    if ((v157 = (cfr_temp_5 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-1092004698479936603L - -1249884629013163549L)) == 0L ? 0 : (cfr_temp_5 < 0L ? -1 : 1)) == false) continue;
                                                    if (v157 == -1052911764 - -1052911763) break;
                                                    v157 = 262582404 ^ 1588994120;
                                                }
                                                v158 = 0QZ8.1d9kfLKTTP4TVZYm((String)v156);
                                                while (true) {
                                                    if ((v159 = (cfr_temp_6 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (2403472875919144564L ^ -2485428632199975285L)) == 0L ? 0 : (cfr_temp_6 < 0L ? -1 : 1)) == false) continue;
                                                    v160 = 1968559114 ^ -1968559115;
                                                    if (v159 == v160) {
                                                        CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.sendMessage(v158);
                                                        return (boolean)(-537684711 ^ -537684712);
                                                    }
                                                    v159 = 1912891030 - -307138584;
                                                }
                                                block402: while (true) {
                                                    v113 = v161 / (-4546038905866487014L - -6045946310052283265L);
lbl567:
                                                    // 2 sources

                                                    switch ((int)v113) {
                                                        case -2025957556: {
                                                            v161 = 5714894485440744988L ^ -139031577869670669L;
                                                            continue block402;
                                                        }
                                                        case -1805772107: {
                                                            v161 = -3686655261939020843L ^ 1149529733539154871L;
                                                            continue block402;
                                                        }
                                                        case -495624045: {
                                                            break block402;
                                                        }
                                                        case 1441319708: {
                                                            v161 = -5013981452290978487L - -5591236332279650854L;
                                                            continue block402;
                                                        }
                                                    }
                                                    break;
                                                }
                                                v162 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                block403: while (true) {
                                                    switch ((int)v162) {
                                                        case -1917122828: {
                                                            v162 = (8443891810742445911L ^ -9061986524539007768L) / (5007038191229512294L - 4320898375826836L);
                                                            continue block403;
                                                        }
                                                        case -495624045: {
                                                            break block403;
                                                        }
                                                    }
                                                    break;
                                                }
                                                v163 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                if (true) ** GOTO lbl593
                                                block404: while (true) {
                                                    v163 = v164 / (-6317099406932448298L ^ -2484321166404511244L);
lbl593:
                                                    // 2 sources

                                                    switch ((int)v163) {
                                                        case -628923645: {
                                                            v164 = -2285748224782294461L ^ 1486484902707520724L;
                                                            continue block404;
                                                        }
                                                        case -495624045: {
                                                            break block404;
                                                        }
                                                        case 46964506: {
                                                            v164 = 1728657651724091551L - -5528879239064963800L;
                                                            continue block404;
                                                        }
                                                        case 799781973: {
                                                            v164 = 862184096450022286L ^ -3423898968652605448L;
                                                            continue block404;
                                                        }
                                                    }
                                                    break;
                                                }
                                                var18_5 = new byte[-1354164477 ^ -1354164461];
                                                var18_5[-1859391462 ^ -1859391462] = 12973842 ^ 12973927;
                                                v165 = 940096090 ^ 940096082;
                                                var18_5[v165] = 118634569 ^ 118634555;
                                                v166 = 1257965829 ^ 1257965824;
                                                var18_5[v166] = 1350016820 ^ 1350016858;
                                                var18_5[812393168 ^ 812393183] = -107660829 ^ -107660851;
                                                var18_5[22598218 ^ 22598214] = -1565269939 ^ -1565269980;
                                                var18_5[-519097600 ^ -519097593] = 811772652 ^ 811772547;
                                                var18_5[1896003311 ^ 1896003301] = 1557019488 ^ 1557019393;
                                                var18_5[1177634678 ^ 1177634687] = 1909669097 ^ 1909668996;
                                                var18_5[-1207087655 ^ -1207087662] = -709856017 ^ -709856101;
                                                var18_5[-1569698878 ^ -1569698877] = -1464179845 ^ -1464179960;
                                                var18_5[-1659370565 ^ -1659370567] = 1555198907 ^ 1555198942;
                                                var18_5[963654068 ^ 963654074] = -526140286 ^ -526140180;
                                                var18_5[1839911811 ^ 1839911822] = -1234743099 ^ -1234743126;
                                                var18_5[1919484255 ^ 1919484249] = 154279967 ^ 154280057;
                                                v167 = -1496422578 ^ -1496422596;
                                                var18_5[-25799067 ^ -25799066] = v167;
                                                var18_5[274021286 ^ 274021282] = -1652173444 ^ -1652173515;
                                                v168 = new StringBuilder().append(new String(var18_5, "UTF-8")).append((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e);
                                                while (true) {
                                                    if ((v169 = (cfr_temp_7 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (8519073049456539783L ^ 1495738290426835522L)) == 0L ? 0 : (cfr_temp_7 < 0L ? -1 : 1)) == false) continue;
                                                    v170 = 1526047620 ^ -1526047621;
                                                    if (v169 == v170) {
                                                        if (!\u017b\u017c\u0107m.7\u015aCz.contains(v168.toString())) {
                                                            break;
                                                        }
                                                        break block569;
                                                    }
                                                    v169 = -1268363573 ^ -121791975;
                                                }
                                                v171 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                if (true) ** GOTO lbl642
                                                block406: while (true) {
                                                    v171 = v172 / (4301035637709706425L ^ 4908384280118270393L);
lbl642:
                                                    // 2 sources

                                                    switch ((int)v171) {
                                                        case -730946008: {
                                                            v172 = 4851377623689280392L >>> "\u0000\u0000".length();
                                                            continue block406;
                                                        }
                                                        case -495624045: {
                                                            break block406;
                                                        }
                                                        case 2105127761: {
                                                            v172 = 9007703820822238822L ^ 8497278048752828994L;
                                                            continue block406;
                                                        }
                                                    }
                                                    break;
                                                }
                                                v173 = new StringBuilder();
                                                var18_5 = new byte[1587568702 ^ 1587568651];
                                                v174 = -59555969 ^ 59556035;
                                                var18_5[13614490 ^ 13614514] = v174;
                                                var18_5[578975511 ^ 578975546] = 514006024 ^ 514006143;
                                                v175 = 1659623671 ^ 1659623619;
                                                var18_5[-531616813 ^ -531616793] = v175;
                                                var18_5[509880118 ^ 509880098] = 824874720 ^ 824874694;
                                                v176 = 2094322230 ^ 2094322231;
                                                var18_5[v176] = -1098301717 ^ -1098301797;
                                                var18_5[1203763107 ^ 1203763073] = 782107973 ^ 782107937;
                                                var18_5[-1541434062 ^ -1541434062] = -1324649003 ^ -1324649042;
                                                var18_5[959543488 ^ 959543509] = -215330027 ^ -215329930;
                                                v177 = 628157024 ^ 628156937;
                                                var18_5[495624231 ^ 495624226] = v177;
                                                var18_5[-741877874 ^ -741877825] = 1307192011 ^ 1307191978;
                                                var18_5[-2147338251 ^ -2147338273] = -752083345 ^ -752083429;
                                                v178 = -266030052 ^ -266030030;
                                                v179 = 705276667 ^ 705276565;
                                                var18_5[v178] = v179;
                                                v180 = 1359183482 ^ 1359183450;
                                                var18_5[1042443818 ^ 1042443787] = v180;
                                                var18_5[-426983571 ^ -426983603] = -261300029 ^ -261300053;
                                                v181 = 545328875 ^ 545328886;
                                                var18_5[v181] = -889434057 ^ -889434023;
                                                var18_5[-294247949 ^ -294247972] = -1297820300 ^ -1297820387;
                                                var18_5[205686800 ^ 205686814] = -221738190 ^ -221738222;
                                                v182 = -274232922 ^ -274232936;
                                                var18_5[58082962 ^ 58082947] = v182;
                                                var18_5[2054202083 ^ 2054202107] = -416765033 ^ -416764938;
                                                var18_5[1814426288 ^ 1814426260] = -365415481 ^ -365415514;
                                                var18_5[1894866050 ^ 1894866061] = 1194735894 ^ 1194735920;
                                                var18_5[797893901 ^ 797893914] = 1225196616 ^ 1225196602;
                                                v183 = 1403520424 ^ 1403520504;
                                                var18_5[-605419449 ^ -605419456] = v183;
                                                v184 = -1304561135 ^ -1304561027;
                                                var18_5[-660630391 ^ -660630358] = v184;
                                                var18_5[-784969342 ^ -784969326] = 76133210 ^ 76133218;
                                                var18_5[945969365 ^ 945969353] = -398135255 ^ -398135224;
                                                var18_5[-527598494 ^ -527598511] = -39542579 ^ -39542549;
                                                v185 = 380878490 ^ 380878465;
                                                var18_5[v185] = -1605203643 ^ -1605203679;
                                                var18_5[2113688260 ^ 2113688268] = 1662498425 ^ 1662498315;
                                                v186 = 1528819642 ^ 1528819663;
                                                var18_5[-1160573358 ^ -1160573359] = v186;
                                                v187 = 1642632905 ^ 1642632922;
                                                v188 = 197961316 ^ 197961284;
                                                var18_5[v187] = v188;
                                                v189 = 1143755253 ^ 1143755230;
                                                var18_5[v189] = 115615230 ^ 115615125;
                                                var18_5[1758948250 ^ 1758948224] = -607967377 ^ -607967409;
                                                v190 = -1052222827 ^ -1052222736;
                                                var18_5[-732236061 ^ -732236054] = v190;
                                                var18_5[-1198906777 ^ -1198906767] = -246604448 ^ -246604510;
                                                var18_5[951342761 ^ 951342732] = -911827160 ^ -911827192;
                                                var18_5[1878528296 ^ 1878528293] = -1669997988 ^ -1669998047;
                                                v191 = -1370086877 ^ -1370086852;
                                                v192 = -691068181 ^ -691068280;
                                                var18_5[v191] = v192;
                                                var18_5[-1493602219 ^ -1493602233] = -167234848 ^ -167234850;
                                                var18_5[1047947704 ^ 1047947708] = -66947532 ^ -66947501;
                                                v193 = -2147458764 ^ -2147458728;
                                                var18_5[-848539639 ^ -848539637] = v193;
                                                v194 = 783656346 ^ 783656324;
                                                var18_5[v194] = 750955898 ^ 750955779;
                                                v195 = -910223058 ^ -910223102;
                                                v196 = -1400765489 ^ -1400765536;
                                                var18_5[v195] = v196;
                                                v197 = -1448443975 ^ -1448444007;
                                                var18_5[-2024473043 ^ -2024473057] = v197;
                                                var18_5[-281487436 ^ -281487470] = 128135921 ^ 128135812;
                                                var18_5[-48515714 ^ -48515737] = 1022536614 ^ 1022536653;
                                                v198 = 1174891706 ^ 1174891729;
                                                var18_5[-2119100808 ^ -2119100856] = v198;
                                                v199 = 657418857 ^ 657418769;
                                                var18_5[1900258979 ^ 1900258991] = v199;
                                                v200 = -2026982117 ^ -2026982046;
                                                var18_5[-254196998 ^ -254197037] = v200;
                                                v201 = -1061056795 ^ -1061056786;
                                                var18_5[v201] = -817621740 ^ -817621635;
                                                var18_5[1513884597 ^ 1513884562] = 492103459 - 492103518;
                                                v202 = 1648246980 ^ 1648246978;
                                                var18_5[v202] = 305875044 ^ 305874954;
                                                v203 = 636713053 ^ 636713047;
                                                var18_5[v203] = 1429115187 ^ 1429115221;
                                                v204 = new String(var18_5, "UTF-8");
                                                v205 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                block407: while (true) {
                                                    switch ((int)v205) {
                                                        case -1041604981: {
                                                            v205 = (-7062072668279243548L >>> "\u0000\u0000".length()) / (-4470077256738934182L - 5586716299325333294L);
                                                            continue block407;
                                                        }
                                                        case -495624045: {
                                                            break block407;
                                                        }
                                                    }
                                                    break;
                                                }
                                                v206 = v173.append(v204);
                                                -1936160479 ^ 200780841;
                                                var18_5 = new byte[-1547028785 ^ -1547028788];
                                                v207 = 1177715551 ^ 1177715489;
                                                var18_5[-965061375 ^ -965061375] = v207;
                                                var18_5[193752565 ^ 193752567] = 19936894 ^ 19936768;
                                                v208 = -1796457561 ^ -1796457562;
                                                var18_5[v208] = 1129962291 ^ 1129962240;
                                                v209 = new String(var18_5, "UTF-8");
                                                var18_5 = new byte[-386344283 ^ -386344284];
                                                v210 = -88872113 ^ -88872095;
                                                var18_5[-1261987059 ^ -1261987059] = v210;
                                                v211 = new String(var18_5, "UTF-8");
                                                v212 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                if (true) ** GOTO lbl768
                                                block408: while (true) {
                                                    v212 = v213 / (-1218395173408641866L ^ 5195592665753247555L);
lbl768:
                                                    // 2 sources

                                                    switch ((int)v212) {
                                                        case -1671910710: {
                                                            v213 = -8974707961115671065L ^ -2153546572293122079L;
                                                            continue block408;
                                                        }
                                                        case -495624045: {
                                                            break block408;
                                                        }
                                                        case -68552282: {
                                                            v213 = 1212271805022526056L - -7774379004713148221L;
                                                            continue block408;
                                                        }
                                                        case 2048011283: {
                                                            v213 = -8535201511708736923L ^ 783894992770938126L;
                                                            continue block408;
                                                        }
                                                    }
                                                    break;
                                                }
                                                v214 = v206.append(CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e.replace(v209, v211));
                                                v215 = 1764944503 ^ 1764944500;
                                                var18_5 = new byte[v215];
                                                v216 = -1226132480 ^ -1226132480;
                                                var18_5[v216] = 122085068 ^ 122085098;
                                                var18_5[-256545402 ^ -256545404] = 217298027 ^ 217297994;
                                                v217 = -420637286 ^ -420637191;
                                                var18_5[350652612 ^ 350652613] = v217;
                                                v218 = new String(var18_5, "UTF-8");
                                                1081676318 ^ -1644172146;
                                                v219 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                block409: while (true) {
                                                    switch ((int)v219) {
                                                        case -495624045: {
                                                            break block409;
                                                        }
                                                        case 1627075566: {
                                                            v219 = (-7385116104244056773L - -6464697774507957642L) / (6081652291182513173L - -2908174505455372685L);
                                                            continue block409;
                                                        }
                                                    }
                                                    break;
                                                }
                                                v220 = v214.append(v218);
                                                while (true) {
                                                    if ((v221 = (cfr_temp_8 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-6048190969331945416L ^ -8100498164434171124L)) == 0L ? 0 : (cfr_temp_8 < 0L ? -1 : 1)) == false) continue;
                                                    v222 = -383051748 - -383051747;
                                                    if (v221 == v222) break;
                                                    v221 = 1272383803 ^ 676939947;
                                                }
                                                v223 = 0QZ8.1d9kfLKTTP4TVZYm((String)v220.toString());
                                                1503329712 >>> "\u0000\u0000".length();
                                                while (true) {
                                                    if ((v224 = (cfr_temp_9 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-7126568520034057387L ^ 7348236967780492690L)) == 0L ? 0 : (cfr_temp_9 < 0L ? -1 : 1)) == false) continue;
                                                    v225 = 487641811 - 487641812;
                                                    if (v224 == v225) {
                                                        CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.sendMessage(v223);
                                                        v226 = -1401283706 ^ -1401283705;
                                                        return v226;
                                                    }
                                                    v224 = -1885585293 ^ -2045185284;
                                                }
                                            }
                                            v227 = 582375876 >>> "\u0000\u0000".length();
                                            v228 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                            block412: while (true) {
                                                switch ((int)v228) {
                                                    case -495624045: {
                                                        break block412;
                                                    }
                                                    case 2115275207: {
                                                        v228 = (-1609880306126916424L ^ 7434719040496453607L) / (-3844625328964651296L - -213677445375647813L);
                                                        continue block412;
                                                    }
                                                }
                                                break;
                                            }
                                            while (true) {
                                                v229 = 8939379104758298784L - 3847413964933704330L;
                                                cfr_temp_10 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v229;
                                                v230 = cfr_temp_10 == 0L ? 0 : (cfr_temp_10 < 0L ? -1 : 1);
                                                if (v230 == false) continue;
                                                v231 = 305668881 - 305668882;
                                                if (v230 == v231) break;
                                                v230 = 1602868692 ^ 1015701574;
                                            }
                                            while (true) {
                                                if ((v232 = (cfr_temp_11 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-4253697382610863602L ^ 7108798525524554328L)) == 0L ? 0 : (cfr_temp_11 < 0L ? -1 : 1)) == false) continue;
                                                if (v232 == (823884190 ^ 823884191)) break;
                                                v232 = 551411887 - -1757193712;
                                            }
                                            v233 = new StringBuilder();
                                            v234 = 87291048 - -1938191799;
                                            v235 = -199921482 ^ -199921498;
                                            var18_5 = new byte[v235];
                                            var18_5[-407451426 ^ -407451430] = -2016312398 ^ -2016312325;
                                            v236 = -723714952 ^ -723714958;
                                            var18_5[v236] = 141622843 ^ 141622874;
                                            var18_5[1857976732 ^ 1857976731] = 1014730096 ^ 1014730015;
                                            var18_5[-761760162 ^ -761760163] = 200247015 ^ 200246933;
                                            var18_5[-1650573074 ^ -1650573083] = 1276642169 ^ 1276642061;
                                            var18_5[1502564537 ^ 1502564532] = -794890169 ^ -794890200;
                                            v237 = 118608691 ^ 118608733;
                                            var18_5[-1125355088 ^ -1125355074] = v237;
                                            v238 = -1090112225 ^ -1090112135;
                                            var18_5[430884496 ^ 430884502] = v238;
                                            v239 = 1416704739 ^ 1416704656;
                                            var18_5[1927787874 ^ 1927787875] = v239;
                                            var18_5[-284211527 ^ -284211536] = 54803574 ^ 54803483;
                                            var18_5[-155634041 ^ -155634033] = -821835376 ^ -821835294;
                                            var18_5[-1263977232 ^ -1263977230] = -1738788046 ^ -1738788009;
                                            var18_5[-309860919 ^ -309860923] = -1482233860 ^ -1482233963;
                                            v240 = -1665659389 ^ -1665659274;
                                            var18_5[760128799 ^ 760128799] = v240;
                                            v241 = -1670295889 ^ -1670295904;
                                            var18_5[v241] = -1754840390 ^ -1754840428;
                                            v242 = 490431726 ^ 490431723;
                                            v243 = -1606745425 ^ -1606745407;
                                            var18_5[v242] = v243;
                                            v244 = new String(var18_5, "UTF-8");
                                            while (true) {
                                                block577: {
                                                    if ((v245 = (cfr_temp_12 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-3409719201331254161L - -4265006160114045707L)) == 0L ? 0 : (cfr_temp_12 < 0L ? -1 : 1)) == false) continue;
                                                    if (v245 != (1999825336 ^ -1999825337)) break block577;
                                                    v246 = v233.append(v244).append((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e);
                                                    var18_5 = new byte[-1653966029 ^ -1653966017];
                                                    var18_5[1409421760 ^ 1409421762] = 508110629 ^ 508110668;
                                                    v247 = -2063799871 ^ -2063799862;
                                                    var18_5[v247] = 1660323210 ^ 1660323302;
                                                    var18_5[-1365902871 ^ -1365902866] = -807551589 ^ -807551529;
                                                    var18_5[-1837469092 ^ -1837469095] = 648706733 ^ 648706760;
                                                    var18_5[-1671570984 ^ -1671570984] = 1092980786 ^ 1092980764;
                                                    var18_5[671479712 ^ 671479722] = -2123111333 ^ -2123111362;
                                                    var18_5[536645111 ^ 536645107] = 2140439712 ^ 2140439764;
                                                    var18_5[1384789671 ^ 1384789665] = 735889980 ^ 735889998;
                                                    v248 = 572821167 ^ 572821192;
                                                    var18_5[-1820307587 ^ -1820307588] = v248;
                                                    v249 = 847019773 ^ 847019659;
                                                    var18_5[83184207 ^ 83184198] = v249;
                                                    var18_5[-630959590 ^ -630959591] = 952955264 ^ 952955366;
                                                    var18_5[1647796412 ^ 1647796404] = -408291284 ^ -408291255;
                                                    v250 = new String(var18_5, "UTF-8");
                                                    v251 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                    if (true) ** GOTO lbl920
                                                }
                                                v245 = -1398843332 ^ 875633173;
                                            }
                                            block416: while (true) {
                                                v252 = -2772444079397804812L ^ 5963995775393674060L;
                                                v251 = v253 / v252;
lbl920:
                                                // 2 sources

                                                switch ((int)v251) {
                                                    case -1236476071: {
                                                        v253 = 39036836350201370L ^ -5081754099862447072L;
                                                        continue block416;
                                                    }
                                                    case -495624045: {
                                                        break block416;
                                                    }
                                                    case 1899238623: {
                                                        v253 = 9126211376916222571L ^ -2610016735256572199L;
                                                        continue block416;
                                                    }
                                                }
                                                break;
                                            }
                                            v254 = v246.append(v250);
                                            v255 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                            if (true) ** GOTO lbl935
                                            block417: while (true) {
                                                v255 = v256 / (-2767657108503992882L - 3959612303607067148L);
lbl935:
                                                // 2 sources

                                                switch ((int)v255) {
                                                    case -1343151526: {
                                                        v256 = 2157816461885564806L ^ -2884230968742603696L;
                                                        continue block417;
                                                    }
                                                    case -495624045: {
                                                        break block417;
                                                    }
                                                    case 1455357461: {
                                                        v256 = 270656350228706138L ^ 7404385516069587719L;
                                                        continue block417;
                                                    }
                                                    case 1873334051: {
                                                        v256 = 3770006068346735344L ^ -2827911680458175402L;
                                                        continue block417;
                                                    }
                                                }
                                                break;
                                            }
                                            v257 = v254.toString();
                                            while (true) {
                                                block578: {
                                                    if ((v258 = (cfr_temp_13 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (3737313732672805047L ^ 7353126376499468817L)) == 0L ? 0 : (cfr_temp_13 < 0L ? -1 : 1)) == false) continue;
                                                    if (v258 != (-102016368 ^ 102016367)) break block578;
                                                    var7_8 = \u017b\u017c\u0107m.7\u015aCz.getString(v257);
                                                    v259 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                    if (true) ** GOTO lbl961
                                                }
                                                v258 = -1752661396 ^ -1235579025;
                                            }
                                            block419: while (true) {
                                                v259 = v260 / (3779446760618264836L ^ 3937010277449381246L);
lbl961:
                                                // 2 sources

                                                switch ((int)v259) {
                                                    case -1292097519: {
                                                        v260 = 1424507718087490121L - 33093333322387708L;
                                                        continue block419;
                                                    }
                                                    case -495624045: {
                                                        break block419;
                                                    }
                                                    case 478242799: {
                                                        v260 = 3993274619065443794L ^ 8334158895113889628L;
                                                        continue block419;
                                                    }
                                                    case 1814871035: {
                                                        v260 = 7641745736581795464L >>> "\u0000\u0000".length();
                                                        continue block419;
                                                    }
                                                }
                                                break;
                                            }
                                            v261 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                            if (true) ** GOTO lbl978
                                            block420: while (true) {
                                                v262 = 308899667988887369L ^ -8171241320166443971L;
                                                v261 = v263 / v262;
lbl978:
                                                // 2 sources

                                                switch ((int)v261) {
                                                    case -2090911610: {
                                                        v263 = -9017059318907331954L - 4867426824295284461L;
                                                        continue block420;
                                                    }
                                                    case -1295847115: {
                                                        v263 = 2022574701539575746L ^ -5774117472761067855L;
                                                        continue block420;
                                                    }
                                                    case -495624045: {
                                                        break block420;
                                                    }
                                                }
                                                break;
                                            }
                                            v264 = new StringBuilder();
                                            var18_5 = new byte[410876531 ^ 410876515];
                                            var18_5[-1905827908 ^ -1905827915] = -1844130170 ^ -1844130069;
                                            v265 = -1643606581 ^ -1643606577;
                                            var18_5[v265] = 302301858 ^ 302301931;
                                            v266 = -778276793 ^ -778276788;
                                            v267 = 1466600141 ^ 1466600121;
                                            var18_5[v266] = v267;
                                            v268 = -1828034532 ^ -1828034534;
                                            var18_5[v268] = -1587190767 ^ -1587190665;
                                            var18_5[-104872372 ^ -104872369] = 1778798829 ^ 1778798751;
                                            var18_5[205539158 ^ 205539158] = -720242570 ^ -720242685;
                                            var18_5[-371334562 ^ -371334561] = -1902380268 ^ -1902380185;
                                            var18_5[-442748249 ^ -442748251] = -2128182653 ^ -2128182554;
                                            v269 = 1340250194 ^ 1340250144;
                                            var18_5[591142406 ^ 591142414] = v269;
                                            v270 = 877609232 ^ 877609245;
                                            var18_5[v270] = -1806449259 ^ -1806449158;
                                            v271 = 293627707 ^ 293627733;
                                            var18_5[-1974098872 ^ -1974098874] = v271;
                                            v272 = 196027249 ^ 196027231;
                                            var18_5[-657691805 ^ -657691796] = v272;
                                            v273 = 1595937437 ^ 1595937434;
                                            var18_5[v273] = -924286179 ^ -924286094;
                                            var18_5[-1926034628 ^ -1926034634] = 46198041 ^ 46198136;
                                            var18_5[957799073 ^ 957799076] = 1107046033 ^ 1107046143;
                                            v274 = 1275133010 ^ 1275132987;
                                            var18_5[-1842446499 ^ -1842446511] = v274;
                                            v275 = new String(var18_5, "UTF-8");
                                            while (true) {
                                                if ((v276 = (cfr_temp_14 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-5583450550938543623L ^ 6981821019667420637L)) == 0L ? 0 : (cfr_temp_14 < 0L ? -1 : 1)) == false) continue;
                                                v277 = 1249875378 ^ 1249875379;
                                                if (v276 == v277) break;
                                                v276 = 1937278888 - 257641715;
                                            }
                                            v278 = v264.append(v275);
                                            while (true) {
                                                block579: {
                                                    if ((v279 = (cfr_temp_15 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-2281422804170625451L ^ -2871593313412345785L)) == 0L ? 0 : (cfr_temp_15 < 0L ? -1 : 1)) == false) continue;
                                                    if (v279 != -178145916 - -178145915) break block579;
                                                    v280 = 1923924085 ^ 1923924090;
                                                    var18_5 = new byte[v280];
                                                    var18_5[-737158230 ^ -737158240] = 842076076 ^ 842076128;
                                                    v281 = 2939594 ^ 2939555;
                                                    var18_5[-1489451423 ^ -1489451421] = v281;
                                                    var18_5[-1889002651 ^ -1889002648] = 939755667 ^ 939755766;
                                                    var18_5[-1406321228 ^ -1406321229] = -1265816409 ^ -1265816381;
                                                    var18_5[-1315065163 ^ -1315065165] = 1071520774 ^ 1071520871;
                                                    v282 = 1642424918 ^ 1642424927;
                                                    var18_5[v282] = 2009627020 ^ 2009627113;
                                                    var18_5[-157369438 ^ -157369431] = -1464231920 ^ -1464231819;
                                                    v283 = -1631774047 ^ -1631773993;
                                                    var18_5[99468405 ^ 99468409] = v283;
                                                    var18_5[705839198 ^ 705839195] = -900681000 ^ -900681062;
                                                    v284 = 217676840 ^ 217676878;
                                                    var18_5[1834974618 ^ 1834974617] = v284;
                                                    var18_5[1441601798 ^ 1441601806] = 1370568377 ^ 1370568414;
                                                    var18_5[-498272657 ^ -498272661] = -1691498597 ^ -1691498513;
                                                    v285 = 1755173157 ^ 1755173157;
                                                    var18_5[v285] = -491317384 ^ -491317418;
                                                    v286 = 1394478497 ^ 1394478511;
                                                    var18_5[v286] = 2125793796 ^ 2125793896;
                                                    var18_5[567326519 ^ 567326518] = -1344495950 ^ -1344495915;
                                                    v287 = v278.append((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e).append(new String(var18_5, "UTF-8")).toString();
                                                    v288 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                    if (true) ** GOTO lbl1061
                                                }
                                                v279 = 258594823 - -1417357947;
                                            }
                                            block423: while (true) {
                                                v288 = v289 / (-7792320318008251138L - 173220663102333141L);
lbl1061:
                                                // 2 sources

                                                switch ((int)v288) {
                                                    case -579289792: {
                                                        v289 = -6903069754661538434L ^ -1020596522882594162L;
                                                        continue block423;
                                                    }
                                                    case -495624045: {
                                                        break block423;
                                                    }
                                                    case 1607438203: {
                                                        v289 = -2824178989614443371L ^ 2673300653045754829L;
                                                        continue block423;
                                                    }
                                                    case 2034115065: {
                                                        v289 = -5732002025488622909L - -1899228162307752691L;
                                                        continue block423;
                                                    }
                                                }
                                                break;
                                            }
                                            var8_9 = \u017b\u017c\u0107m.7\u015aCz.getString(v287);
                                            -206663259 ^ -1558048356;
                                            var18_5 = new byte[-791269340 ^ -791269324];
                                            var18_5[-1189834604 ^ -1189834607] = 1259881029 ^ 1259881003;
                                            var18_5[1186366629 ^ 1186366635] = -33734395 ^ -33734293;
                                            v290 = -1933288285 ^ -1933288277;
                                            v291 = 1804379233 ^ 1804379155;
                                            var18_5[v290] = v291;
                                            var18_5[1426675789 ^ 1426675780] = 601553786 ^ 601553687;
                                            var18_5[-82511804 ^ -82511799] = -564664056 ^ -564663961;
                                            v292 = 1914460118 ^ 1914460063;
                                            var18_5[-75420316 ^ -75420320] = v292;
                                            v293 = -412701910 ^ -412701948;
                                            var18_5[-1502391577 ^ -1502391576] = v293;
                                            var18_5[-1516258597 ^ -1516258607] = -1381488585 ^ -1381488554;
                                            var18_5[-1188175932 ^ -1188175928] = 1649013599 ^ 1649013558;
                                            v294 = -1686567596 ^ -1686567595;
                                            var18_5[v294] = 1870054718 ^ 1870054733;
                                            var18_5[14657989 ^ 14657998] = -454545887 ^ -454545835;
                                            var18_5[152750471 ^ 152750468] = -1541068330 ^ -1541068380;
                                            v295 = 1415678165 ^ 1415678112;
                                            var18_5[-2015565517 ^ -2015565517] = v295;
                                            v296 = -121589020 ^ -121589018;
                                            v297 = 2888172 ^ 2888073;
                                            var18_5[v296] = v297;
                                            v298 = 44575114 ^ 44575205;
                                            var18_5[1540584142 ^ 1540584137] = v298;
                                            v299 = -73302465 ^ -73302439;
                                            var18_5[-878930941 ^ -878930939] = v299;
                                            v300 = new StringBuilder().append(new String(var18_5, "UTF-8"));
                                            while (true) {
                                                block580: {
                                                    if ((v301 = (cfr_temp_16 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (6291248906687332161L - 5418897688705900625L)) == 0L ? 0 : (cfr_temp_16 < 0L ? -1 : 1)) == false) continue;
                                                    if (v301 != (-1420140428 ^ 1420140427)) break block580;
                                                    v302 = v300.append((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e);
                                                    var18_5 = new byte[-1292658407 ^ -1292658408];
                                                    var18_5[536409893 ^ 536409893] = 666077503 ^ 666077457;
                                                    v303 = new String(var18_5, "UTF-8");
                                                    v304 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                    if (true) ** GOTO lbl1127
                                                }
                                                v301 = -1037055056 - -335698411;
                                            }
                                            block425: while (true) {
                                                v304 = v305 / (4291950630125129653L ^ -6223644655838811176L);
lbl1127:
                                                // 2 sources

                                                switch ((int)v304) {
                                                    case -495624045: {
                                                        break block425;
                                                    }
                                                    case 932969879: {
                                                        v305 = -3814659274936154051L ^ -6839583331738791960L;
                                                        continue block425;
                                                    }
                                                    case 1696936244: {
                                                        v305 = 6387590268739098638L - -471057161421426083L;
                                                        continue block425;
                                                    }
                                                }
                                                break;
                                            }
                                            v306 = v302.append(v303);
                                            var18_5 = new byte[-1490356724 ^ -1490356723];
                                            var18_5[-1721397095 ^ -1721397095] = 1429547980 ^ 1429548002;
                                            v307 = new String(var18_5, "UTF-8");
                                            var18_5 = new byte[933151562 ^ 933151563];
                                            var18_5[-1402012611 ^ -1402012611] = -1598090444 ^ -1598090471;
                                            v308 = CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa11684f519.replace(v307, new String(var18_5, "UTF-8"));
                                            while (true) {
                                                if ((v309 = (cfr_temp_17 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-4256177129999285177L ^ -8564738271810561325L)) == 0L ? 0 : (cfr_temp_17 < 0L ? -1 : 1)) == false) continue;
                                                if (v309 == (-1693249154 ^ 1693249153)) break;
                                                v309 = -975269347 - -765326478;
                                            }
                                            v310 = v306.append(v308);
                                            v311 = 726532149 ^ 726532155;
                                            var18_5 = new byte[v311];
                                            v312 = 273980481 ^ 273980448;
                                            var18_5[294471191 ^ 294471189] = v312;
                                            v313 = -2089188530 ^ -2089188565;
                                            var18_5[-1869388914 ^ -1869388924] = v313;
                                            v314 = 1849270494 ^ 1849270490;
                                            var18_5[v314] = 302592521 ^ 302592587;
                                            var18_5[1752118567 ^ 1752118575] = -900750026 ^ -900749997;
                                            var18_5[-1986096567 ^ -1986096572] = -2134687683 ^ -2134687663;
                                            var18_5[75919645 ^ 75919638] = -1481608761 ^ -1481608783;
                                            v315 = 1198050511 ^ 1198050504;
                                            v316 = -326582520 ^ -326582417;
                                            var18_5[v315] = v316;
                                            var18_5[1219192743 ^ 1219192750] = -1394875670 ^ -1394875738;
                                            var18_5[2051271301 ^ 2051271299] = 1225037149 ^ 1225037113;
                                            var18_5[36992052 ^ 36992056] = 453700397 ^ 453700424;
                                            v317 = 1470701455 ^ 1470701450;
                                            v318 = 1440004454 ^ 1440004359;
                                            var18_5[v317] = v318;
                                            var18_5[-1200702398 ^ -1200702399] = 1650629487 ^ 1650629377;
                                            v319 = -59920349 ^ -59920315;
                                            var18_5[227505074 ^ 227505075] = v319;
                                            v320 = 127669853 ^ 127669853;
                                            v321 = -1158257672 ^ -1158257706;
                                            var18_5[v320] = v321;
                                            v322 = new String(var18_5, "UTF-8");
                                            while (true) {
                                                if ((v323 = (cfr_temp_18 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (7796961707883606358L ^ 4269542402782497530L)) == 0L ? 0 : (cfr_temp_18 < 0L ? -1 : 1)) == false) continue;
                                                if (v323 == (-1471523631 ^ 1471523630)) break;
                                                v323 = 1544686151 - 465185649;
                                            }
                                            v324 = v310.append(v322).toString();
                                            v325 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                            block428: while (true) {
                                                switch ((int)v325) {
                                                    case -495624045: {
                                                        break block428;
                                                    }
                                                    case 82710532: {
                                                        v325 = (3985155226414251527L ^ 2357403714074747888L) / (-2821867644442406211L ^ 7976266708537562462L);
                                                        continue block428;
                                                    }
                                                }
                                                break;
                                            }
                                            -1055911941 ^ 83925388;
                                            var9_10 = \u017b\u017c\u0107m.7\u015aCz.getString(v324);
                                            while (true) {
                                                v326 = 6011021861033175987L ^ -3598254195832671620L;
                                                cfr_temp_19 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v326;
                                                v327 = cfr_temp_19 == 0L ? 0 : (cfr_temp_19 < 0L ? -1 : 1);
                                                if (v327 == false) continue;
                                                v328 = 1854287027 ^ 1854287026;
                                                if (v327 == v328) break;
                                                v327 = 185946664 ^ -974301164;
                                            }
                                            -854274102 ^ 758349277;
                                            while (true) {
                                                if ((v329 = (cfr_temp_20 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (3633984754461147477L ^ -7076051382538604414L)) == 0L ? 0 : (cfr_temp_20 < 0L ? -1 : 1)) == false) continue;
                                                if (v329 == (-318773278 ^ -318773277)) break;
                                                v329 = 1988390660 ^ -1193852784;
                                            }
                                            v330 = -1255189257 ^ -1255189273;
                                            var18_5 = new byte[v330];
                                            v331 = -1316982407 ^ -1316982410;
                                            var18_5[v331] = -1897598456 ^ -1897598426;
                                            v332 = 1147697949 ^ 1147697943;
                                            var18_5[v332] = 823734855 ^ 823734822;
                                            v333 = -536105337 ^ -536105330;
                                            v334 = -1938690766 ^ -1938690721;
                                            var18_5[v333] = v334;
                                            v335 = 1838577094 ^ 1838577064;
                                            var18_5[-1522981518 ^ -1522981513] = v335;
                                            var18_5[817229219 ^ 817229220] = -700168829 ^ -700168724;
                                            var18_5[-336771797 ^ -336771802] = -1554201548 ^ -1554201509;
                                            var18_5[417287364 ^ 417287362] = -1788335255 ^ -1788335345;
                                            var18_5[1967663143 ^ 1967663143] = 1684946131 ^ 1684946086;
                                            v336 = 705929468 ^ 705929358;
                                            var18_5[-993207134 ^ -993207135] = v336;
                                            v337 = 1434247973 ^ 1434247981;
                                            var18_5[v337] = 565314238 ^ 565314252;
                                            var18_5[1925023982 ^ 1925023980] = 1191706153 ^ 1191706188;
                                            var18_5[-1476679890 ^ -1476679902] = -251785023 ^ -251785048;
                                            var18_5[-768818021 ^ -768818032] = 201054625 ^ 201054677;
                                            v338 = -177765223 ^ -177765129;
                                            var18_5[-1322200231 ^ -1322200233] = v338;
                                            var18_5[1087680103 ^ 1087680099] = 365207519 ^ 365207446;
                                            var18_5[716590327 ^ 716590326] = -1133805797 ^ -1133805720;
                                            v339 = new StringBuilder().append(new String(var18_5, "UTF-8"));
                                            while (true) {
                                                v340 = 6997122633189184572L - -2756330722992253785L;
                                                cfr_temp_21 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v340;
                                                v341 = cfr_temp_21 == 0L ? 0 : (cfr_temp_21 < 0L ? -1 : 1);
                                                if (v341 == false) continue;
                                                v342 = -1449181972 ^ -1449181971;
                                                if (v341 == v342) break;
                                                v341 = -411080732 - -1279367697;
                                            }
                                            v343 = v339.append((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e);
                                            -1565192075 - 1835593139;
                                            var18_5 = new byte[799454237 ^ 799454236];
                                            v344 = -345548281 ^ -345548281;
                                            var18_5[v344] = -278211909 ^ -278211947;
                                            v345 = new String(var18_5, "UTF-8");
                                            while (true) {
                                                block581: {
                                                    if ((v346 = (cfr_temp_22 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (1504871101252466375L - -5600396126715599568L)) == 0L ? 0 : (cfr_temp_22 < 0L ? -1 : 1)) == false) continue;
                                                    v347 = 360676330 ^ -360676331;
                                                    if (v346 != v347) break block581;
                                                    v348 = v343.append(v345);
                                                    var18_5 = new byte[-833631574 ^ -833631573];
                                                    var18_5[-73948543 ^ -73948543] = 460378990 ^ 460378944;
                                                    v349 = new String(var18_5, "UTF-8");
                                                    var18_5 = new byte[-207534253 ^ -207534254];
                                                    var18_5[-1538144621 ^ -1538144621] = 594286787 ^ 594286830;
                                                    v350 = new String(var18_5, "UTF-8");
                                                    v351 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                    if (true) ** GOTO lbl1295
                                                }
                                                v346 = 1364853520 ^ -1056186764;
                                            }
                                            block433: while (true) {
                                                v351 = v352 / (7483083352695410108L - -4699204392418673503L);
lbl1295:
                                                // 2 sources

                                                switch ((int)v351) {
                                                    case -1259345038: {
                                                        v352 = 274629767496411972L ^ -6290271863507573525L;
                                                        continue block433;
                                                    }
                                                    case -495624045: {
                                                        break block433;
                                                    }
                                                    case 1138445652: {
                                                        v352 = 8173030027433597589L ^ 8233764053807411467L;
                                                        continue block433;
                                                    }
                                                }
                                                break;
                                            }
                                            v353 = v348.append(CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa11684f519.replace(v349, v350));
                                            var18_5 = new byte[-1471517601 ^ -1471517613];
                                            v354 = 416122440 ^ 416122444;
                                            var18_5[v354] = 1374947973 ^ 1374948074;
                                            v355 = 186873538 ^ 186873538;
                                            var18_5[v355] = 838918525 ^ 838918483;
                                            v356 = -1951360378 ^ -1951360270;
                                            var18_5[47374432 ^ 47374441] = v356;
                                            v357 = -695274616 ^ -695274621;
                                            var18_5[v357] = 383129584 ^ 383129474;
                                            v358 = -1333995917 ^ -1333995909;
                                            v359 = 2131401295 ^ 2131401262;
                                            var18_5[v358] = v359;
                                            v360 = 1782929705 ^ 1782929728;
                                            var18_5[-14564177 ^ -14564178] = v360;
                                            v361 = -1817599042 ^ -1817599043;
                                            v362 = 1660820646 ^ 1660820715;
                                            var18_5[v361] = v362;
                                            var18_5[637597969 ^ 637597974] = -1073531366 ^ -1073531288;
                                            v363 = -1403457838 ^ -1403457833;
                                            v364 = -1419983539 ^ -1419983575;
                                            var18_5[v363] = v364;
                                            var18_5[-1845519731 ^ -1845519733] = -1428371247 ^ -1428371276;
                                            var18_5[-693986686 ^ -693986688] = 1342772079 ^ 1342771996;
                                            v365 = -1135706812 ^ -1135706802;
                                            v366 = 1045095660 ^ 1045095555;
                                            var18_5[v365] = v366;
                                            v367 = new String(var18_5, "UTF-8");
                                            while (true) {
                                                if ((v368 = (cfr_temp_23 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-6248046289871361164L ^ -2566104062666651783L)) == 0L ? 0 : (cfr_temp_23 < 0L ? -1 : 1)) == false) continue;
                                                if (v368 == (-404995341 ^ 404995340)) break;
                                                v368 = -509324456 >>> "\u0000\u0000".length();
                                            }
                                            v369 = v353.append(v367);
                                            v370 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                            block435: while (true) {
                                                switch ((int)v370) {
                                                    case -1232680671: {
                                                        v370 = (-2264395411116283537L ^ -9044027789809239727L) / (2224396253924228061L ^ 5940302189667552029L);
                                                        continue block435;
                                                    }
                                                    case -495624045: {
                                                        break block435;
                                                    }
                                                }
                                                break;
                                            }
                                            CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1744f8669 = \u017b\u017c\u0107m.7\u015aCz.getString(v369.toString());
                                            while (true) {
                                                block582: {
                                                    v371 = -3143224447034051131L - 7612944494901769361L;
                                                    cfr_temp_24 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v371;
                                                    v372 = cfr_temp_24 == 0L ? 0 : (cfr_temp_24 < 0L ? -1 : 1);
                                                    if (v372 == false) continue;
                                                    if (v372 != (353795175 ^ -353795176)) break block582;
                                                    v373 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                    if (true) ** GOTO lbl1372
                                                }
                                                v372 = 77212940 ^ -891954263;
                                            }
                                            block437: while (true) {
                                                v374 = -4084522007854251478L ^ 374026623247517150L;
                                                v373 = v375 / v374;
lbl1372:
                                                // 2 sources

                                                switch ((int)v373) {
                                                    case -495624045: {
                                                        break block437;
                                                    }
                                                    case -193762222: {
                                                        v375 = 7316865534274238407L ^ -9195381125012184172L;
                                                        continue block437;
                                                    }
                                                    case 934285176: {
                                                        v375 = 6531896900556183728L - 2136988559552860880L;
                                                        continue block437;
                                                    }
                                                    case 2004224008: {
                                                        v375 = 113047952880000199L - 3108831716302708981L;
                                                        continue block437;
                                                    }
                                                }
                                                break;
                                            }
                                            v376 = new StringBuilder();
                                            v377 = 409607420 ^ 409607404;
                                            var18_5 = new byte[v377];
                                            v378 = -282198171 ^ -282198170;
                                            var18_5[v378] = -140077009 ^ -140076963;
                                            v379 = -1021727140 ^ -1021727142;
                                            var18_5[v379] = 1373910467 ^ 1373910437;
                                            var18_5[-1340180343 ^ -1340180351] = 1289327995 ^ 1289327881;
                                            var18_5[-1635605423 ^ -1635605409] = 836208203 ^ 836208165;
                                            var18_5[1566765376 ^ 1566765381] = 1749391349 ^ 1749391259;
                                            v380 = -1107754062 ^ -1107754061;
                                            var18_5[v380] = -1695394178 ^ -1695394291;
                                            var18_5[-1591550208 ^ -1591550196] = -1807494788 ^ -1807494891;
                                            var18_5[-740435685 ^ -740435690] = -745260710 ^ -745260747;
                                            var18_5[1105810738 ^ 1105810742] = 2141324408 ^ 2141324337;
                                            var18_5[-1824629882 ^ -1824629879] = -1304114917 ^ -1304114891;
                                            v381 = 1585884799 ^ 1585884797;
                                            v382 = 739840749 ^ 739840648;
                                            var18_5[v381] = v382;
                                            v383 = -1826122478 ^ -1826122471;
                                            var18_5[v383] = 321028745 ^ 321028861;
                                            var18_5[-1772223376 ^ -1772223366] = 1271866107 ^ 1271866010;
                                            var18_5[-215619233 ^ -215619233] = -442585243 ^ -442585328;
                                            var18_5[753387833 ^ 753387824] = 152166529 ^ 152166636;
                                            var18_5[-1576777039 ^ -1576777034] = -558894947 ^ -558894862;
                                            v384 = new String(var18_5, "UTF-8");
                                            while (true) {
                                                v385 = 6741703143287987023L ^ 8137977636572156406L;
                                                cfr_temp_25 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v385;
                                                v386 = cfr_temp_25 == 0L ? 0 : (cfr_temp_25 < 0L ? -1 : 1);
                                                if (v386 == false) continue;
                                                if (v386 == (-1433864721 ^ 1433864720)) break;
                                                v386 = 1079369982 - 1327113710;
                                            }
                                            v387 = v376.append(v384);
                                            while (true) {
                                                if ((v388 = (cfr_temp_26 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (2916482106011777343L ^ 7052089756789103802L)) == 0L ? 0 : (cfr_temp_26 < 0L ? -1 : 1)) == false) continue;
                                                if (v388 == -1119753694 - -1119753693) break;
                                                v388 = -246198715 ^ 1211396454;
                                            }
                                            v389 = v387.append((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e);
                                            v390 = 1074729947 ^ 1074729943;
                                            var18_5 = new byte[v390];
                                            v391 = 568778795 ^ 568778784;
                                            v392 = 657690662 ^ 657690691;
                                            var18_5[v391] = v392;
                                            var18_5[-190079688 ^ -190079682] = -2024137422 ^ -2024137389;
                                            v393 = 2085701276 ^ 2085701269;
                                            var18_5[v393] = -906380570 ^ -906380665;
                                            var18_5[-980071472 ^ -980071472] = 103335057 ^ 103335103;
                                            var18_5[1135938940 ^ 1135938932] = 2042892247 ^ 2042892217;
                                            var18_5[1499932422 ^ 1499932419] = 1967718376 ^ 1967718276;
                                            var18_5[-1309439360 ^ -1309439358] = 1934680536 ^ 1934680497;
                                            var18_5[1333284385 ^ 1333284395] = 474400030 ^ 474400115;
                                            var18_5[-78345021 ^ -78345022] = 2093999129 ^ 2093999229;
                                            v394 = 400479844 ^ 400479843;
                                            var18_5[v394] = -1835179273 ^ -1835179378;
                                            var18_5[427654484 ^ 427654487] = -1283474156 ^ -1283474073;
                                            var18_5[-1481087176 ^ -1481087172] = -1334580745 ^ -1334580857;
                                            v395 = new String(var18_5, "UTF-8");
                                            while (true) {
                                                block583: {
                                                    if ((v396 = (cfr_temp_27 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-4714672561809862291L - 1191079551754728621L)) == 0L ? 0 : (cfr_temp_27 < 0L ? -1 : 1)) == false) continue;
                                                    v397 = 66099988 ^ 66099989;
                                                    if (v396 != v397) break block583;
                                                    v398 = v389.append(v395);
                                                    v399 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                                    if (true) ** GOTO lbl1460
                                                }
                                                v396 = -2046392402 - -1823596542;
                                            }
                                            block441: while (true) {
                                                v399 = v400 / (1659053421792006326L - 5392816503210443979L);
lbl1460:
                                                // 2 sources

                                                switch ((int)v399) {
                                                    case -495624045: {
                                                        break block441;
                                                    }
                                                    case -252476787: {
                                                        v400 = -7421021386719934084L ^ 2903850035402577897L;
                                                        continue block441;
                                                    }
                                                    case -122074354: {
                                                        v400 = -236391074688617593L ^ -3273034421750293167L;
                                                        continue block441;
                                                    }
                                                }
                                                break;
                                            }
                                            v401 = v398.toString();
                                            v402 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                            if (true) ** GOTO lbl1475
                                            block442: while (true) {
                                                v403 = 2655347218576225736L >>> "\u0000\u0000".length();
                                                v402 = v404 / v403;
lbl1475:
                                                // 2 sources

                                                switch ((int)v402) {
                                                    case -495624045: {
                                                        break block442;
                                                    }
                                                    case 1571887030: {
                                                        v404 = -4419261852074620450L ^ 2894304258896069156L;
                                                        continue block442;
                                                    }
                                                    case 1682418547: {
                                                        v404 = -4231686845255299891L - -8759965775964794284L;
                                                        continue block442;
                                                    }
                                                }
                                                break;
                                            }
                                            CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1cf4c016b = \u017b\u017c\u0107m.7\u015aCz.getString(v401);
                                            if (CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1cf4c016b != null) break block584;
                                            var18_5 = new byte[-1867300584 ^ -1867300581];
                                            var18_5[-1800719165 ^ -1800719166] = -1286538658 ^ -1286538643;
                                            v405 = 1562507610 ^ 1562507608;
                                            var18_5[v405] = 2114792799 ^ 2114792737;
                                            v406 = 1828996984 ^ 1828996984;
                                            v407 = -491621727 ^ -491621665;
                                            var18_5[v406] = v407;
                                            v408 = new String(var18_5, "UTF-8");
                                            var18_5 = new byte[-857900847 ^ -857900848];
                                            var18_5[-558796392 ^ -558796392] = -1564918608 ^ -1564918626;
                                            v409 = new String(var18_5, "UTF-8");
                                            v410 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                            if (true) ** GOTO lbl1511
                                            block443: while (true) {
                                                v410 = v411 / (-7927401638583337496L >>> "\u0000\u0000".length());
lbl1511:
                                                // 2 sources

                                                switch ((int)v410) {
                                                    case -495624045: {
                                                        break block443;
                                                    }
                                                    case 383893641: {
                                                        v411 = -3367086322175857219L - -3267603706748510974L;
                                                        continue block443;
                                                    }
                                                    case 971438672: {
                                                        v411 = 4690376519364106984L ^ -5419087794373353454L;
                                                        continue block443;
                                                    }
                                                }
                                                break;
                                            }
                                            CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1cf4c016b = CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e.replace(v408, v409);
                                        }
                                        while (true) {
                                            v412 = -4614279580442945281L - 5790774910906640062L;
                                            cfr_temp_28 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v412;
                                            v413 = cfr_temp_28 == 0L ? 0 : (cfr_temp_28 < 0L ? -1 : 1);
                                            if (v413 == false) continue;
                                            if (v413 == -637149028 - -637149027) {
                                                if (Boolean.valueOf(CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1744f8669).booleanValue()) {
                                                    break;
                                                }
                                                break block570;
                                            }
                                            v413 = -1661995696 ^ 1030766294;
                                        }
                                        -1186977572 ^ -674145435;
                                        var18_5 = new byte[1805412630 ^ 1805412614];
                                        v414 = 32342355 ^ 32342332;
                                        var18_5[33105371 ^ 33105365] = v414;
                                        var18_5[172163483 ^ 172163472] = -1706657420 ^ -1706657530;
                                        var18_5[-562458544 ^ -562458539] = 1619330776 ^ 1619330741;
                                        var18_5[1822700996 ^ 1822700992] = -1404929684 ^ -1404929789;
                                        var18_5[516637343 ^ 516637337] = 1177149619 ^ 1177149676;
                                        v415 = -1900848931 ^ -1900848930;
                                        var18_5[v415] = 2042496562 ^ 2042496582;
                                        v416 = -235633573 ^ -235633577;
                                        v417 = 2115136609 ^ 2115136512;
                                        var18_5[v416] = v417;
                                        var18_5[-1840682328 ^ -1840682335] = -101444722 ^ -101444630;
                                        var18_5[2043516429 ^ 2043516426] = 842691312 ^ 842691229;
                                        var18_5[1279713052 ^ 1279713054] = -1768718055 ^ -1768717974;
                                        var18_5[-1173224751 ^ -1173224741] = -565019754 ^ -565019661;
                                        v418 = -357394312 ^ -357394420;
                                        var18_5[1638856297 ^ 1638856292] = v418;
                                        var18_5[-381154237 ^ -381154238] = -190673540 ^ -190673655;
                                        var18_5[16099338 ^ 16099338] = -1962116524 ^ -1962116553;
                                        var18_5[-1407052994 ^ -1407053002] = -1776848634 ^ -1776848535;
                                        var18_5[889854288 ^ 889854303] = 1010015870 ^ 1010015756;
                                        v419 = new String(var18_5, "UTF-8");
                                        v420 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                        if (true) ** GOTO lbl1570
                                    }
                                    var18_5 = new byte[159510228 ^ 159510228];
                                    v421 = new String(var18_5, "UTF-8");
                                    break block585;
                                    block445: while (true) {
                                        v420 = v422 / (2660797075413924543L - -5550452048779303355L);
lbl1570:
                                        // 2 sources

                                        switch ((int)v420) {
                                            case -495624045: {
                                                break block445;
                                            }
                                            case 365122537: {
                                                v422 = 1799734980661429629L ^ -6261837123392467343L;
                                                continue block445;
                                            }
                                            case 1998661152: {
                                                v422 = 199689699039233335L ^ 8847457244169903647L;
                                                continue block445;
                                            }
                                        }
                                        break;
                                    }
                                    v421 = e4wV.2al_94WjzQTnIzkU((String)v419);
                                }
                                v423 = -1522201043 - 2133798701;
                                var12_13 = v421;
                                var18_5 = new byte[-693290215 ^ -693290216];
                                var18_5[1103497020 ^ 1103497020] = -1037934446 ^ -1037934414;
                                v424 = new String(var18_5, "UTF-8");
                                v425 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                if (true) ** GOTO lbl1600
                                block446: while (true) {
                                    v425 = v426 / (7024483312500508539L ^ -4584990548893995260L);
lbl1600:
                                    // 2 sources

                                    switch ((int)v425) {
                                        case -658305720: {
                                            v426 = -6423578255270155720L >>> "\u0000\u0000".length();
                                            continue block446;
                                        }
                                        case -495624045: {
                                            break block446;
                                        }
                                        case -296089231: {
                                            v426 = 8823925685454004187L - 1862472361158199565L;
                                            continue block446;
                                        }
                                    }
                                    break;
                                }
                                CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.sendMessage(v424);
                                v427 = new StringBuilder();
                                v428 = 2050960917 ^ 2050960951;
                                var18_5 = new byte[v428];
                                v429 = 1154597717 ^ 1154597719;
                                var18_5[v429] = -428943589 ^ 428943609;
                                v430 = 149983704 ^ 149983710;
                                v431 = -518953632 ^ -518953658;
                                var18_5[v430] = v431;
                                var18_5[-1138935421 ^ -1138935409] = -239796160 ^ -239796174;
                                var18_5[-1554109058 ^ -1554109086] = -1292093859 ^ -1292093910;
                                var18_5[373852990 ^ 373852987] = 1228546949 ^ 1228546981;
                                v432 = 1891529544 ^ 1891529521;
                                var18_5[1970504096 ^ 1970504120] = v432;
                                var18_5[-713573849 ^ -713573832] = 1854101803 ^ 1854101824;
                                var18_5[-455758577 ^ -455758584] = 1637996364 ^ 1637996330;
                                v433 = 1996541546 ^ 1996541565;
                                v434 = 1148982984 ^ -1148982924;
                                var18_5[v433] = v434;
                                var18_5[1129323141 ^ 1129323151] = -1534614500 ^ -1534614406;
                                var18_5[-297181215 ^ -297181190] = -973452865 ^ -973452848;
                                v435 = 843087546 ^ 843087524;
                                var18_5[v435] = -1044451232 ^ -1044451319;
                                v436 = 1520841182 ^ 1520841214;
                                var18_5[v436] = -986357851 ^ -986357808;
                                var18_5[1801375494 ^ 1801375499] = -191279138 ^ -191279181;
                                var18_5[-136778278 ^ -136778283] = 1423931638 ^ 1423931541;
                                v437 = -2100564297 ^ -2100564285;
                                var18_5[-2097646321 ^ -2097646314] = v437;
                                v438 = -1016878664 ^ -1016878690;
                                var18_5[1357886 ^ 1357886] = v438;
                                v439 = 559668106 ^ 559668112;
                                var18_5[v439] = -1436219602 ^ -1436219579;
                                var18_5[1270037389 ^ 1270037379] = -85213340 ^ -85213435;
                                var18_5[-877711737 ^ -877711721] = -36089382 ^ -36089424;
                                var18_5[-1607895972 ^ -1607895987] = 1303151719 ^ 1303151618;
                                var18_5[2010219829 ^ 2010219808] = -963308378 ^ -963308333;
                                v440 = 835284086 ^ -835284045;
                                var18_5[-1145501377 ^ -1145501399] = v440;
                                v441 = 68445467 ^ 68445449;
                                var18_5[v441] = -518595746 ^ -518595714;
                                var18_5[-417150603 ^ -417150602] = -1378952215 ^ 1378952297;
                                v442 = -1394239570 ^ -1394239602;
                                var18_5[1066999235 ^ 1066999255] = v442;
                                var18_5[-1753877126 ^ -1753877135] = -1433663231 ^ -1433663122;
                                v443 = -1107259260 ^ -1107259264;
                                v444 = 1431855099 ^ -1431855015;
                                var18_5[v443] = v444;
                                v445 = -1106244469 ^ -1106244458;
                                var18_5[v445] = -595679534 ^ -595679556;
                                var18_5[688148232 ^ 688148224] = 1394556912 ^ 1394556857;
                                v446 = 513002629 ^ 513002661;
                                var18_5[-1737450295 ^ -1737450264] = v446;
                                v447 = -710496652 ^ -710496742;
                                var18_5[-879518472 ^ -879518479] = v447;
                                v448 = 1159722908 ^ 1159722995;
                                var18_5[1522981592 ^ 1522981579] = v448;
                                v449 = 1317920404 ^ 1317920428;
                                var18_5[2143812450 ^ 2143812451] = v449;
                                v450 = new String(var18_5, "UTF-8");
                                while (true) {
                                    v451 = 1528740019072453766L - -7462722515706127182L;
                                    cfr_temp_29 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v451;
                                    v452 = cfr_temp_29 == 0L ? 0 : (cfr_temp_29 < 0L ? -1 : 1);
                                    if (v452 == false) continue;
                                    v453 = 1986261127 - 1986261128;
                                    if (v452 == v453) break;
                                    v452 = 1116305394 - 334824151;
                                }
                                v454 = v427.append(v450);
                                while (true) {
                                    v455 = -5002903444914177116L >>> "\u0000\u0000".length();
                                    cfr_temp_30 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v455;
                                    v456 = cfr_temp_30 == 0L ? 0 : (cfr_temp_30 < 0L ? -1 : 1);
                                    if (v456 == false) continue;
                                    if (v456 == 1499274099 - 1499274100) break;
                                    v456 = 1417260919 - 995864504;
                                }
                                v457 = v454.append((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa140a26cb3);
                                v458 = -1334775900 ^ -1334775827;
                                var18_5 = new byte[v458];
                                v459 = 1479510102 ^ 1479510103;
                                var18_5[v459] = -1433077143 ^ -1433077237;
                                v460 = -955794329 ^ -955794361;
                                var18_5[-435717197 ^ -435717246] = v460;
                                v461 = -1369524168 ^ -1369524157;
                                var18_5[-1517491883 ^ -1517491871] = v461;
                                var18_5[1347826650 ^ 1347826628] = -1868896093 ^ -1868896123;
                                var18_5[-1249972860 ^ -1249972853] = 1975210749 ^ 1975210715;
                                var18_5[118600229 ^ 118600210] = -1331915518 ^ -1331915415;
                                var18_5[1397592090 ^ 1397592069] = 2000347466 ^ 2000347506;
                                var18_5[-735598635 ^ -735598633] = -556856897 ^ -556856892;
                                var18_5[-2077500836 ^ -2077500836] = -1652845021 ^ -1652845051;
                                var18_5[-1806383161 ^ -1806383116] = 1125526152 ^ 1125526250;
                                v462 = 209001925 ^ 209001960;
                                var18_5[v462] = -1423684110 ^ -1423684198;
                                var18_5[1266350914 ^ 1266350851] = 1966413086 ^ 1966413176;
                                v463 = 1615827731 ^ 1615827832;
                                var18_5[-861639794 ^ -861639756] = v463;
                                v464 = 1560415754 ^ 1560415822;
                                var18_5[v464] = -1509111337 ^ -1509111311;
                                v465 = -1068106662 ^ -1068106640;
                                var18_5[v465] = 1879509142 ^ 1879509240;
                                var18_5[110094281 ^ 110094311] = -1017941211 ^ -1017941243;
                                v466 = -560416932 ^ -560416903;
                                var18_5[v466] = 970261725 ^ 970261681;
                                var18_5[-879310298 ^ -879310311] = -1576843448 ^ -1576843467;
                                var18_5[709646012 ^ 709645972] = -645499694 ^ -645499722;
                                var18_5[281207436 ^ 281207500] = -252800311 ^ -252800273;
                                var18_5[-1529854555 ^ -1529854565] = -935168481 ^ -935168390;
                                var18_5[-697565699 ^ -697565711] = -1484483604 ^ -1484483638;
                                var18_5[534808554 ^ 534808524] = 131162600 ^ 131162505;
                                v467 = 1017071274 ^ 1017071286;
                                var18_5[v467] = -1226728658 ^ -1226728629;
                                var18_5[-802251986 ^ -802251991] = -2048343479 ^ -2048343513;
                                var18_5[-1424515604 ^ -1424515627] = -855587129 ^ -855587160;
                                v468 = -1751040366 ^ -1751040266;
                                var18_5[-220983940 ^ -220983976] = v468;
                                v469 = 55603866 ^ 55603946;
                                var18_5[-1715830542 ^ -1715830553] = v469;
                                v470 = -796831158 ^ -796831145;
                                v471 = 510104236 ^ 510104273;
                                var18_5[v470] = v471;
                                var18_5[1453803841 ^ 1453803849] = -432190601 ^ -432190698;
                                v472 = 1565134162 ^ 1565134123;
                                var18_5[1658229127 ^ 1658229164] = v472;
                                v473 = -268289414 ^ -268289432;
                                v474 = 1374516131 ^ 1374516167;
                                var18_5[v473] = v474;
                                v475 = 377687789 ^ 377687783;
                                v476 = 194780249 ^ 194780220;
                                var18_5[v475] = v476;
                                var18_5[-1574268065 ^ -1574268057] = -641486158 ^ -641486138;
                                var18_5[-1429060567 ^ -1429060501] = 1091977184 ^ 1091977153;
                                var18_5[107410258 ^ 107410265] = 2054197977 ^ 2054197924;
                                var18_5[1039851381 ^ 1039851314] = -1546787636 ^ 1546787660;
                                var18_5[-1223289999 ^ -1223290024] = -310822040 ^ -310822135;
                                v477 = -14617244 ^ -14617335;
                                var18_5[773991145 ^ 773991136] = v477;
                                var18_5[-904922587 ^ -904922618] = 1280495528 ^ 1280495566;
                                v478 = -904725535 ^ -904725630;
                                var18_5[7752455 ^ 7752450] = v478;
                                var18_5[1678025103 ^ 1678025116] = 1514934438 ^ 1514934479;
                                v479 = 1025540146 ^ 1025540179;
                                var18_5[-764382527 ^ -764382506] = v479;
                                v480 = 116515200 ^ 116515315;
                                var18_5[-1873952957 ^ -1873952937] = v480;
                                var18_5[1262834027 ^ 1262834011] = -214720188 ^ -214720224;
                                var18_5[-801386822 ^ -801386872] = 2124400028 ^ 2124400058;
                                v481 = -1733679110 ^ -1733679182;
                                v482 = -272777604 ^ 272777694;
                                var18_5[v481] = v482;
                                var18_5[1988961386 ^ 1988961352] = -1510418356 ^ -1510418326;
                                v483 = -2021607007 ^ -2021606950;
                                var18_5[204323935 ^ 204323918] = v483;
                                var18_5[2029509709 ^ 2029509710] = 406020390 ^ 406020424;
                                v484 = 1117640202 ^ 1117640255;
                                var18_5[v484] = -503100734 ^ -503100746;
                                var18_5[1072460792 ^ 1072460770] = 1671661996 ^ 1671662029;
                                v485 = -1298970144 ^ -1298970168;
                                var18_5[1087658898 ^ 1087658908] = v485;
                                var18_5[-1293012697 ^ -1293012708] = -1971993493 ^ -1971993563;
                                var18_5[-290969046 ^ -290969037] = -1290943598 ^ -1290943492;
                                var18_5[-658004509 ^ -658004493] = -895204798 ^ -895204828;
                                v486 = 1765160653 ^ 1765160649;
                                var18_5[v486] = -703306540 ^ -703306563;
                                var18_5[1650973073 ^ 1650973066] = 915123225 ^ 915123316;
                                v487 = 2070254675 ^ 2070254661;
                                var18_5[v487] = 761917671 ^ 761917579;
                                v488 = -940769720 ^ -940769755;
                                var18_5[-1684197989 ^ -1684197978] = v488;
                                v489 = -392150558 ^ -392150590;
                                var18_5[1788841371 ^ 1788841432] = v489;
                                var18_5[-329719472 ^ -329719440] = -2139951166 ^ -2139951125;
                                v490 = 2086505753 ^ 2086505781;
                                var18_5[v490] = 1525168393 ^ 1525168490;
                                var18_5[-1433690976 ^ -1433690907] = -2112339804 ^ -2112339812;
                                var18_5[-1315194827 ^ -1315194854] = 81817340 ^ 81817235;
                                var18_5[1327071120 ^ 1327071148] = 1259015194 ^ 1259015291;
                                v491 = -288555717 ^ -288555715;
                                var18_5[v491] = 1253702984 ^ 1253702947;
                                var18_5[463913528 ^ 463913504] = 509270559 ^ 509270630;
                                var18_5[779927564 ^ 779927553] = -1947798926 ^ -1947798966;
                                v492 = 105349586 ^ 105349604;
                                v493 = 284355210 ^ 284355299;
                                var18_5[v492] = v493;
                                var18_5[1126370414 ^ 1126370383] = 1023641639 ^ 1023641607;
                                var18_5[-1501194873 ^ -1501194848] = -771463074 ^ -771463042;
                                var18_5[-358403921 ^ -358403863] = -1264966529 ^ 1264966557;
                                v494 = new String(var18_5, "UTF-8");
                                var18_5 = new byte[627692271 ^ 627692258];
                                var18_5[512234637 ^ 512234631] = 1518021862 ^ 1518021771;
                                var18_5[-829623969 ^ -829623974] = -611776188 ^ -611776216;
                                v495 = 997129040 ^ 997129012;
                                var18_5[1939249953 ^ 1939249952] = v495;
                                var18_5[-267717427 ^ -267717425] = -1166988029 ^ -1166987926;
                                v496 = -305170530 ^ -305170535;
                                var18_5[v496] = 635950661 ^ 635950652;
                                v497 = -1758115549 ^ -1758115552;
                                var18_5[v497] = 1467138702 ^ 1467138813;
                                var18_5[1367779248 ^ 1367779256] = 547993442 ^ 547993356;
                                var18_5[1772788654 ^ 1772788645] = 133795811 ^ 133795718;
                                v498 = -1160362785 ^ -1160362785;
                                var18_5[v498] = -233775693 ^ -233775672;
                                v499 = -57980033 ^ -57980037;
                                v500 = -2125438392 ^ -2125438408;
                                var18_5[v499] = v500;
                                v501 = 1946471110 ^ 1946471119;
                                var18_5[v501] = -2100762974 ^ -2100762941;
                                v502 = 2010716516 ^ 2010716441;
                                var18_5[-1007210972 ^ -1007210968] = v502;
                                v503 = 1395127684 ^ 1395127781;
                                var18_5[1235318805 ^ 1235318803] = v503;
                                v504 = v494.replace(new String(var18_5, "UTF-8"), CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1cf4c016b);
                                v505 = 700387218 ^ -1046482705;
                                var18_5 = new byte[1075998860 ^ 1075998854];
                                v506 = 1017326863 ^ 1017326964;
                                var18_5[-988975328 ^ -988975328] = v506;
                                var18_5[-32812468 ^ -32812476] = 762216845 ^ 762216936;
                                var18_5[-1074623404 ^ -1074623405] = -1388924056 ^ -1388924155;
                                var18_5[-790049156 ^ -790049160] = 403400372 ^ 403400415;
                                var18_5[1898214761 ^ 1898214764] = -36517079 ^ -36517049;
                                v507 = 1604608769 ^ 1604608771;
                                var18_5[v507] = -1514421544 ^ -1514421583;
                                v508 = 901277317 ^ 901277318;
                                var18_5[v508] = 793040948 ^ 793040983;
                                var18_5[-665585015 ^ -665585009] = 133682025 ^ 133681928;
                                var18_5[801505198 ^ 801505199] = -86008885 ^ -86008923;
                                var18_5[815070956 ^ 815070949] = 1776780379 ^ 1776780326;
                                v509 = new String(var18_5, "UTF-8");
                                -311480030 - 1645479717;
                                var18_5 = new byte[802616317 ^ 802616318];
                                v510 = 1690637663 ^ 1690637663;
                                v511 = 1499034213 ^ 1499034139;
                                var18_5[v510] = v511;
                                var18_5[-1224524511 ^ -1224524512] = 1253339500 ^ 1253339487;
                                v512 = 1703268942 ^ 1703268912;
                                var18_5[-2065410568 ^ -2065410566] = v512;
                                v513 = new String(var18_5, "UTF-8");
                                var18_5 = new byte[38363922 ^ 38363923];
                                var18_5[-202310484 ^ -202310484] = -1311284106 ^ -1311284136;
                                v514 = new String(var18_5, "UTF-8");
                                while (true) {
                                    if ((v515 = (cfr_temp_31 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-7332007414508188892L - 1851032590207836155L)) == 0L ? 0 : (cfr_temp_31 < 0L ? -1 : 1)) == false) continue;
                                    if (v515 == (-1842758958 ^ -1842758957)) break;
                                    v515 = 1142788660 ^ -870955507;
                                }
                                v516 = CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e.replace(v513, v514);
                                while (true) {
                                    if ((v517 = (cfr_temp_32 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-6069718022534380083L ^ 3985862471371856814L)) == 0L ? 0 : (cfr_temp_32 < 0L ? -1 : 1)) == false) continue;
                                    v518 = 1007428054 ^ 1007428055;
                                    if (v517 == v518) break;
                                    v517 = 1567346979 ^ -787938947;
                                }
                                v519 = v504.replace(v509, v516);
                                -686632023 ^ -2055644678;
                                var18_5 = new byte[-309323801 ^ -309323797];
                                v520 = 66311855 ^ 66311849;
                                v521 = 132619688 ^ 132619715;
                                var18_5[v520] = v521;
                                v522 = 1211866571 ^ 1211866542;
                                var18_5[210192322 ^ 210192328] = v522;
                                v523 = 874110910 ^ 874110907;
                                var18_5[v523] = 1749402389 ^ 1749402490;
                                v524 = -1282444304 ^ -1282444296;
                                var18_5[v524] = -1731364064 ^ -1731364031;
                                var18_5[208916669 ^ 208916668] = -66895006 ^ -66895082;
                                var18_5[104982644 ^ 104982644] = 1752095104 ^ 1752095227;
                                var18_5[-2082732177 ^ -2082732186] = 1265995001 ^ 1265994900;
                                v525 = -649644969 ^ -649644972;
                                var18_5[v525] = -1633309839 ^ -1633309926;
                                var18_5[-1985819652 ^ -1985819657] = 624244067 ^ 624243998;
                                var18_5[1953604560 ^ 1953604562] = -966003379 ^ -966003420;
                                var18_5[-200900727 ^ -200900722] = 1161461212 ^ 1161461138;
                                v526 = 1349180296 ^ 1349180300;
                                var18_5[v526] = -1793310780 ^ -1793310800;
                                v527 = new String(var18_5, "UTF-8");
                                v528 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                block451: while (true) {
                                    switch ((int)v528) {
                                        case -495624045: {
                                            break block451;
                                        }
                                        case 368499047: {
                                            v528 = (-8533779439021308538L - 492794747170169205L) / (4173647803728071643L ^ -7876481161258999018L);
                                            continue block451;
                                        }
                                    }
                                    break;
                                }
                                v529 = v519.replace(v527, CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa11684f519);
                                v530 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                if (true) ** GOTO lbl1918
                                block452: while (true) {
                                    v530 = v531 / (-5133745189154687714L ^ 1402213221061073829L);
lbl1918:
                                    // 2 sources

                                    switch ((int)v530) {
                                        case -1565520560: {
                                            v531 = -395261091882029169L ^ 3291337529727777189L;
                                            continue block452;
                                        }
                                        case -951277896: {
                                            v531 = -5095734920091166406L - 6893647760529505090L;
                                            continue block452;
                                        }
                                        case -495624045: {
                                            break block452;
                                        }
                                        case 1813873410: {
                                            v531 = 5939882380181048934L - 7641174652670482247L;
                                            continue block452;
                                        }
                                    }
                                    break;
                                }
                                v532 = v457.append(v529);
                                while (true) {
                                    if ((v533 = (cfr_temp_33 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (3059442617021607865L - 8237431044752835416L)) == 0L ? 0 : (cfr_temp_33 < 0L ? -1 : 1)) == false) continue;
                                    v534 = 1696192811 ^ 1696192810;
                                    if (v533 == v534) break;
                                    v533 = 250249531 ^ -1991482715;
                                }
                                CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.sendMessage(0QZ8.1d9kfLKTTP4TVZYm((String)v532.toString()));
                                795773688 - -1609973746;
                                while (true) {
                                    if ((v535 = (cfr_temp_34 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-4019091529166798538L ^ -9050726421752831127L)) == 0L ? 0 : (cfr_temp_34 < 0L ? -1 : 1)) == false) continue;
                                    if (v535 == (-1265163251 ^ 1265163250)) {
                                        if (8\u0105ja.BQptGjO0la9wx-GD((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e) != null) {
                                            break;
                                        }
                                        break block571;
                                    }
                                    v535 = 1566614748 - 1398749360;
                                }
                                361342073 - -51555652;
                                while (true) {
                                    v536 = -1294889835170770538L - 8156920438791600769L;
                                    cfr_temp_35 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v536;
                                    v537 = cfr_temp_35 == 0L ? 0 : (cfr_temp_35 < 0L ? -1 : 1);
                                    if (v537 == false) continue;
                                    if (v537 == 2010414793 - 2010414794) break;
                                    v537 = 1450589468 - -938047342;
                                }
                                var13_14 = 8\u0105ja.BQptGjO0la9wx-GD((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e).\u0142W\u015aq;
                                v538 = -201592172 - -960696874;
                                -419434632 - -625674661;
                                -1740934163 ^ -1612941559;
                                v539 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                block456: while (true) {
                                    switch ((int)v539) {
                                        case -495624045: {
                                            break block456;
                                        }
                                        case 1990613773: {
                                            v539 = (7598432781120003397L ^ 1536718157747984010L) / (-8462089882285316471L ^ -3732298802163071568L);
                                            continue block456;
                                        }
                                    }
                                    break;
                                }
                                v540 = 8\u0105ja.BQptGjO0la9wx-GD((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e).5w\u0105\u0106;
                                v541 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                block457: while (true) {
                                    switch ((int)v541) {
                                        case -569808901: {
                                            v541 = (7848553035228192070L ^ -4521155560912730243L) / (-434919878496373741L ^ -8340451278168573641L);
                                            continue block457;
                                        }
                                        case -495624045: {
                                            break block457;
                                        }
                                    }
                                    break;
                                }
                                var14_15 = String.valueOf(v540);
                                -1690222763 ^ -219457757;
                                v542 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                if (true) ** GOTO lbl2005
                                block458: while (true) {
                                    v542 = (-1827896198172720768L ^ 4606103763275586057L) / v543;
lbl2005:
                                    // 2 sources

                                    switch ((int)v542) {
                                        case -495624045: {
                                            break block458;
                                        }
                                        case 673959871: {
                                            v543 = 4077537014537775839L ^ 5691229114811865791L;
                                            continue block458;
                                        }
                                    }
                                    break;
                                }
                                v544 = 8\u0105ja.BQptGjO0la9wx-GD((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e);
                                v545 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                if (true) ** GOTO lbl2016
                                block459: while (true) {
                                    v545 = v546 / (5240191507396045379L ^ -5407907111471475801L);
lbl2016:
                                    // 2 sources

                                    switch ((int)v545) {
                                        case -574667849: {
                                            v546 = 243463019204242847L ^ 2375810761796481256L;
                                            continue block459;
                                        }
                                        case -495624045: {
                                            break block459;
                                        }
                                        case 1468176308: {
                                            v546 = -2160744388600937992L ^ -8384235664803112693L;
                                            continue block459;
                                        }
                                        case 1648148442: {
                                            v546 = 4859073575059695923L ^ -7805003866950985812L;
                                            continue block459;
                                        }
                                    }
                                    break;
                                }
                                v547 = v544.kSX0;
                                while (true) {
                                    if ((v548 = (cfr_temp_36 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-8894154406211469190L ^ 5170500930973733001L)) == 0L ? 0 : (cfr_temp_36 < 0L ? -1 : 1)) == false) continue;
                                    if (v548 == (-448346052 ^ -448346051)) break;
                                    v548 = -1316149129 ^ 1550489914;
                                }
                                var15_16 = String.valueOf(v547);
                                -2112558723 - 1032611634;
                                while (true) {
                                    block586: {
                                        if ((v549 = (cfr_temp_37 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (5079060343746888628L ^ -5487081530780168178L)) == 0L ? 0 : (cfr_temp_37 < 0L ? -1 : 1)) == false) continue;
                                        if (v549 != 347223769 - 347223770) break block586;
                                        var18_5 = new byte[1608775347 ^ 1608775342];
                                        v550 = -1624882277 ^ -1624882294;
                                        var18_5[v550] = 1619608119 ^ 1619608142;
                                        var18_5[1526421998 ^ 1526421993] = -1948352280 ^ -1948352346;
                                        var18_5[-719410943 ^ -719410937] = 160288881 ^ 160288838;
                                        var18_5[-1538677695 ^ -1538677683] = -2051779078 ^ 2051779134;
                                        var18_5[1043896480 ^ 1043896500] = -1508116258 ^ -1508116297;
                                        var18_5[1445069954 ^ 1445069972] = -369756886 ^ -369756834;
                                        v551 = -1569205697 ^ -1569205700;
                                        var18_5[v551] = 788568899 ^ 788568957;
                                        var18_5[239072532 ^ 239072526] = 2111184279 ^ 2111184311;
                                        var18_5[-955033839 ^ -955033843] = -2099657224 ^ -2099657314;
                                        var18_5[-1603799043 ^ -1603799041] = 295065420 ^ 295065458;
                                        var18_5[1952583186 ^ 1952583191] = 1447304767 ^ 1447304729;
                                        v552 = 1499391423 ^ 1499391446;
                                        var18_5[813412748 ^ 813412743] = v552;
                                        var18_5[-2081484033 ^ -2081484043] = 533001590 ^ 533001473;
                                        v553 = 863289216 ^ 863289234;
                                        v554 = -1478134941 ^ -1478134973;
                                        var18_5[v553] = v554;
                                        v555 = 962949635 ^ 962949652;
                                        v556 = -924299177 ^ -924299151;
                                        var18_5[v555] = v556;
                                        v557 = -1540992131 ^ -1540992152;
                                        var18_5[v557] = -1121226933 ^ -1121226963;
                                        var18_5[-1019041197 ^ -1019041188] = 829146976 ^ 829146899;
                                        v558 = -1602257785 ^ -1602257729;
                                        var18_5[1501672127 ^ 1501672103] = v558;
                                        var18_5[-201635489 ^ -201635490] = 1383925159 ^ 1383925151;
                                        v559 = 1839368110 ^ 1839368110;
                                        var18_5[v559] = 84190545 ^ 84190583;
                                        var18_5[-1504588167 ^ -1504588176] = 1715240832 ^ 1715240938;
                                        var18_5[-1268903694 ^ -1268903710] = 538434062 ^ 538434164;
                                        v560 = -581763123 ^ -581763116;
                                        v561 = -2079077018 ^ -2079077028;
                                        var18_5[v560] = v561;
                                        var18_5[-222650188 ^ -222650193] = 1582368582 ^ 1582368608;
                                        var18_5[-993110368 ^ -993110360] = 1988030085 ^ 1988030180;
                                        v562 = 1526897154 - 1526897257;
                                        var18_5[1464124743 ^ 1464124746] = v562;
                                        v563 = -2093387733 ^ -2093387712;
                                        var18_5[751468035 ^ 751468045] = v563;
                                        var18_5[533568654 ^ 533568669] = 699246369 ^ 699246406;
                                        v564 = -1930722721 ^ -1930722689;
                                        var18_5[2023446238 ^ 2023446234] = v564;
                                        v565 = new StringBuilder().append(new String(var18_5, "UTF-8"));
                                        v566 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                        if (true) ** GOTO lbl2096
                                    }
                                    v549 = 965210419 ^ 812882934;
                                }
                                block462: while (true) {
                                    v566 = v567 / (-57545389979548586L ^ -6053684642061748949L);
lbl2096:
                                    // 2 sources

                                    switch ((int)v566) {
                                        case -495624045: {
                                            break block462;
                                        }
                                        case -478558600: {
                                            v567 = 3844663057163040494L ^ 5962688904967514760L;
                                            continue block462;
                                        }
                                        case 608994909: {
                                            v567 = -9055582632013391717L - 6204779455507830415L;
                                            continue block462;
                                        }
                                        case 1692435378: {
                                            v567 = -5063547075093987515L ^ -8713442675541153824L;
                                            continue block462;
                                        }
                                    }
                                    break;
                                }
                                var18_5 = new byte[1758582114 ^ 1758582120];
                                v568 = -1263505930 ^ 1263506040;
                                var18_5[-292569473 ^ -292569482] = v568;
                                v569 = -1270311917 ^ -1270311918;
                                var18_5[v569] = 1138364195 ^ 1138364165;
                                v570 = -1221834352 ^ 1221834255;
                                var18_5[-647163325 ^ -647163324] = v570;
                                v571 = -352209139 ^ -352209141;
                                var18_5[v571] = 2125936787 ^ -2125936797;
                                var18_5[-1830977737 ^ -1830977741] = -566918472 ^ -566918498;
                                v572 = 471769541 ^ 471769543;
                                var18_5[v572] = -1633488977 ^ -1633489001;
                                var18_5[1470755867 ^ 1470755859] = 580864640 ^ -580864750;
                                var18_5[413173119 ^ 413173119] = -1629641355 ^ -1629641387;
                                v573 = 775623132 ^ 775623129;
                                var18_5[v573] = -1648868477 ^ -1648868383;
                                var18_5[-1815149159 ^ -1815149158] = 40308227 ^ 40308267;
                                v574 = v565.append(e4wV.YXwhAXryZbC3UCh0((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa161e30a22)).append(0QZ8.SELweHz5Hut8idk4((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1b30de0ef)).append(new String(var18_5, "UTF-8"));
                                v575 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                if (true) ** GOTO lbl2135
                                block463: while (true) {
                                    v575 = v576 / (2488999759008918127L ^ -722549680906258866L);
lbl2135:
                                    // 2 sources

                                    switch ((int)v575) {
                                        case -1256955944: {
                                            v576 = -7142489846393706189L ^ -1576267156806075834L;
                                            continue block463;
                                        }
                                        case -1075379907: {
                                            v576 = 4490340930838769697L - -6222813767938034563L;
                                            continue block463;
                                        }
                                        case -495624045: {
                                            break block463;
                                        }
                                        case 1368611058: {
                                            v576 = -2333707327792321330L ^ 8463929777456367271L;
                                            continue block463;
                                        }
                                    }
                                    break;
                                }
                                v577 = v574.append(CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1e3ecbb97);
                                var18_5 = new byte[950662825 ^ 950662826];
                                var18_5[216756103 ^ 216756103] = 1023081997 ^ 1023082027;
                                var18_5[-1379700352 ^ -1379700350] = -1552489352 ^ -1552489391;
                                var18_5[-1878632749 ^ -1878632750] = 220492524 ^ 220492500;
                                v578 = new String(var18_5, "UTF-8");
                                v579 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                if (true) ** GOTO lbl2160
                                block464: while (true) {
                                    v579 = v580 / (2662822305761035282L - -959535209528495603L);
lbl2160:
                                    // 2 sources

                                    switch ((int)v579) {
                                        case -600458021: {
                                            v580 = 2744115112288860854L - -7146167148610759647L;
                                            continue block464;
                                        }
                                        case -495624045: {
                                            break block464;
                                        }
                                        case 1214983995: {
                                            v580 = -2058040481436475731L ^ 4483435695455695624L;
                                            continue block464;
                                        }
                                    }
                                    break;
                                }
                                -1806952875 - 570234625;
                                v581 = 0QZ8.1d9kfLKTTP4TVZYm((String)v577.append(v578).toString());
                                while (true) {
                                    if ((v582 = (cfr_temp_38 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-4734493368148612182L ^ 539308282551346425L)) == 0L ? 0 : (cfr_temp_38 < 0L ? -1 : 1)) == false) continue;
                                    if (v582 == (-1633812182 ^ -1633812181)) {
                                        CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.sendMessage(v581);
                                        break block572;
                                    }
                                    v582 = 770623344 >>> "\u0000\u0000".length();
                                }
                            }
                            var18_5 = new byte[580556276 ^ 580556245];
                            var18_5[-725691538 ^ -725691525] = 956112170 ^ 956112204;
                            var18_5[-1177628223 ^ -1177628197] = -452853516 ^ -452853548;
                            v583 = 1816333625 ^ 1816333599;
                            var18_5[1855573249 ^ 1855573270] = v583;
                            var18_5[1259438562 ^ 1259438589] = -470047885 ^ -470047950;
                            var18_5[1331839655 ^ 1331839665] = 473295936 ^ 473295924;
                            var18_5[850731563 ^ 850731553] = 26730396 ^ 26730475;
                            v584 = 372030327 ^ 372030321;
                            v585 = -1962620131 ^ -1962620118;
                            var18_5[v584] = v585;
                            v586 = 1693669465 ^ 1693669479;
                            var18_5[-28348602 ^ -28348603] = v586;
                            v587 = 115545150 ^ 115545137;
                            var18_5[v587] = 1068356802 ^ 1068356785;
                            v588 = 875265721 ^ 875265717;
                            var18_5[v588] = 1389817599 ^ -1389817541;
                            v589 = 939271327 ^ 939271298;
                            var18_5[v589] = 7504066 ^ 7504000;
                            v590 = -2115138435 ^ -2115138444;
                            var18_5[v590] = -963557021 ^ -963557111;
                            var18_5[1279634092 ^ 1279634098] = 1360945035 ^ 1360945113;
                            var18_5[260768297 ^ 260768265] = -1538222735 ^ -1538222790;
                            var18_5[2075576290 ^ 2075576305] = 1707478949 ^ 1707478978;
                            var18_5[440595426 ^ 440595440] = -486271154 ^ -486271122;
                            v591 = 1996871688 ^ 1996871686;
                            var18_5[v591] = 783148907 ^ 783148800;
                            var18_5[-1692041222 ^ -1692041237] = -1037287512 ^ -1037287471;
                            v592 = -100603032 ^ -100603031;
                            var18_5[v592] = 741724562 ^ 741724586;
                            v593 = -319241227 ^ -319241261;
                            var18_5[1143750149 ^ 1143750174] = v593;
                            v594 = -1333422074 ^ -1333422048;
                            var18_5[54029984 ^ 54029989] = v594;
                            v595 = -460250724 ^ -460250635;
                            var18_5[-2025557398 ^ -2025557407] = v595;
                            var18_5[1734694702 ^ 1734694697] = 1463060187 ^ 1463060117;
                            var18_5[2072821828 ^ 2072821852] = -177149449 ^ -177149489;
                            v596 = -1500095867 ^ -1500095867;
                            var18_5[v596] = -893990054 ^ -893990020;
                            var18_5[-770411514 ^ -770411489] = 1042987357 ^ 1042987367;
                            v597 = 423993882 ^ 423993866;
                            v598 = -1338461559 ^ -1338461453;
                            var18_5[v597] = v598;
                            v599 = 1380048424 ^ 1380048421;
                            var18_5[v599] = -411971196 ^ 411971101;
                            var18_5[280243790 ^ 280243788] = 1253666266 ^ 1253666276;
                            v600 = 1308603227 ^ 1308603186;
                            var18_5[-1966803486 ^ -1966803466] = v600;
                            v601 = 2047284324 ^ 2047284320;
                            var18_5[v601] = -1764913292 ^ -1764913324;
                            v602 = 874999786 ^ 874999691;
                            var18_5[120875203 ^ 120875211] = v602;
                            var18_5[725687217 ^ 725687213] = 423310044 ^ 423310015;
                            v603 = new String(var18_5, "UTF-8");
                            while (true) {
                                block587: {
                                    if ((v604 = (cfr_temp_39 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (8708644626594719596L >>> "\u0000\u0000".length())) == 0L ? 0 : (cfr_temp_39 < 0L ? -1 : 1)) == false) continue;
                                    if (v604 != 1901857691 - 1901857692) break block587;
                                    v605 = 0QZ8.1d9kfLKTTP4TVZYm((String)v603);
                                    v606 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                    if (true) ** GOTO lbl2253
                                }
                                v604 = -1155294717 ^ 203889517;
                            }
                            block467: while (true) {
                                v606 = v607 / (-3827564732201247967L ^ -5716591327703327292L);
lbl2253:
                                // 2 sources

                                switch ((int)v606) {
                                    case -495624045: {
                                        break block467;
                                    }
                                    case -100055670: {
                                        v607 = 2282837898502287283L ^ -2309285608334789329L;
                                        continue block467;
                                    }
                                    case -26666727: {
                                        v607 = 239050090134496074L ^ 6102468387293634878L;
                                        continue block467;
                                    }
                                    case 314863701: {
                                        v607 = 3525841159374808153L - -7333476453832835524L;
                                        continue block467;
                                    }
                                }
                                break;
                            }
                            CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.sendMessage(v605);
                        }
                        1231862244 - 1183335131;
                        CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1b30de0ef = 8\u0105ja.dwgYX0tRM7_r1EXt((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e);
                        -1570928151 ^ 2138760603;
                        while (true) {
                            v608 = 6775970667745357127L - -4991669180670086582L;
                            cfr_temp_40 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v608;
                            v609 = cfr_temp_40 == 0L ? 0 : (cfr_temp_40 < 0L ? -1 : 1);
                            if (v609 == false) continue;
                            if (v609 == (16434755 ^ 16434754)) break;
                            v609 = -1294513874 - 1833949479;
                        }
                        CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa161e30a22 = 8\u0105ja.29x0Qgm99hDNrRxo((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1f1704866);
                        v610 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                        block469: while (true) {
                            switch ((int)v610) {
                                case -495624045: {
                                    break block469;
                                }
                                case 227096590: {
                                    v610 = (5120274111247098347L - -6976472903030266859L) / (2645678387714736674L ^ 3366849674716578034L);
                                    continue block469;
                                }
                            }
                            break;
                        }
                        v611 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                        if (true) ** GOTO lbl2300
                        block470: while (true) {
                            v611 = v612 / (-8014107321459007784L - -7323575170012464516L);
lbl2300:
                            // 2 sources

                            switch ((int)v611) {
                                case -1269028289: {
                                    v612 = 3608660579077716947L - -7677918195898056734L;
                                    continue block470;
                                }
                                case -495624045: {
                                    break block470;
                                }
                                case 555039061: {
                                    v612 = 5318293091010454L ^ -5308690439598488591L;
                                    continue block470;
                                }
                            }
                            break;
                        }
                        v613 = new StringBuilder();
                        var18_5 = new byte[176438943 ^ 176438972];
                        var18_5[-1179383828 ^ -1179383822] = 335898575 ^ 335898615;
                        var18_5[409219756 ^ 409219754] = 1310290669 ^ 1310290650;
                        v614 = 1744523748 ^ 1744523668;
                        var18_5[1235909176 ^ 1235909170] = v614;
                        var18_5[1380219598 ^ 1380219591] = 836331219 ^ 836331193;
                        v615 = -1068956497 ^ -1068956493;
                        var18_5[v615] = 1656889487 ^ 1656889595;
                        var18_5[-483440244 ^ -483440234] = -2104265308 ^ -2104265267;
                        var18_5[-965920442 ^ -965920435] = 1258743990 ^ 1258744025;
                        v616 = -1513506784 ^ -1513506769;
                        var18_5[v616] = -2139284611 ^ -2139284708;
                        v617 = -1695015261 ^ -1695015295;
                        var18_5[v617] = 631058085 ^ 631058115;
                        var18_5[1194922146 ^ 1194922150] = -1054711107 ^ -1054711139;
                        var18_5[-775695590 ^ -775695603] = -714327640 ^ -714327599;
                        var18_5[969056661 ^ 969056660] = 1139602948 ^ 1139603004;
                        var18_5[-299350531 ^ -299350539] = 973919141 ^ 973919172;
                        v618 = -1314430290 ^ -1314430276;
                        var18_5[v618] = 1499407319 ^ 1499407294;
                        var18_5[1762270139 ^ 1762270137] = -96898836 ^ -96898862;
                        var18_5[1447906403 ^ 1447906415] = -89006538 ^ -89006522;
                        var18_5[1792953117 ^ 1792953107] = -350851534 ^ -350851490;
                        var18_5[937456375 ^ 937456343] = -1381266531 ^ -1381266499;
                        var18_5[-1204467837 ^ -1204467814] = 235884028 ^ 235883931;
                        var18_5[2055176533 ^ 2055176522] = 1393610261 ^ 1393610287;
                        var18_5[1053502086 ^ 1053502098] = 2137885300 ^ 2137885214;
                        v619 = 1366084404 ^ 1366084385;
                        var18_5[v619] = -448031829 ^ -448031784;
                        var18_5[-1438334782 ^ -1438334766] = -29146881 ^ -29146995;
                        var18_5[1281770285 ^ 1281770286] = 1160567116 ^ 1160567154;
                        v620 = 878537453 ^ 878537450;
                        v621 = 1781558200 ^ 1781558262;
                        var18_5[v620] = v621;
                        v622 = -775227567 ^ -775227529;
                        var18_5[-751128694 ^ -751128681] = v622;
                        var18_5[-289183724 ^ -289183737] = 995842412 ^ 995842313;
                        var18_5[980194063 ^ 980194068] = 1564199159 ^ 1564199057;
                        v623 = 63637288 ^ 63637288;
                        var18_5[v623] = -57093867 ^ -57093837;
                        v624 = -54089097 ^ -54089094;
                        var18_5[v624] = -240069130 ^ -240069245;
                        v625 = -17655135 ^ -17655077;
                        var18_5[-1170285822 ^ -1170285804] = v625;
                        var18_5[-888175188 ^ -888175191] = 2128600714 ^ 2128600748;
                        v626 = -1867752373 ^ -1867752365;
                        var18_5[v626] = 796444402 ^ 796444370;
                        var18_5[141873909 ^ 141873876] = -1355316142 ^ -1355316108;
                        var18_5[1570154917 ^ 1570154932] = 1850339288 ^ 1850339254;
                        v627 = new String(var18_5, "UTF-8");
                        v628 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                        if (true) ** GOTO lbl2366
                        block471: while (true) {
                            v628 = v629 / (-3530197951879966043L ^ 3332141220300486318L);
lbl2366:
                            // 2 sources

                            switch ((int)v628) {
                                case -1209962990: {
                                    v629 = -301252944097126051L - -400834279277063847L;
                                    continue block471;
                                }
                                case -495624045: {
                                    break block471;
                                }
                                case 301786695: {
                                    v629 = -225800438025524263L ^ 2659544019898513357L;
                                    continue block471;
                                }
                                case 1557026412: {
                                    v629 = 8826617033614076906L ^ 162791689100518143L;
                                    continue block471;
                                }
                            }
                            break;
                        }
                        v630 = v613.append(v627).append(e4wV.YXwhAXryZbC3UCh0((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1f1704866));
                        while (true) {
                            block588: {
                                if ((v631 = (cfr_temp_41 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-25146260231259997L - 6282995298659057324L)) == 0L ? 0 : (cfr_temp_41 < 0L ? -1 : 1)) == false) continue;
                                if (v631 != (-713170329 ^ -713170330)) break block588;
                                v632 = 0QZ8.SELweHz5Hut8idk4((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1f1704866);
                                v633 = -1271334771 - 1313434825;
                                v634 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                if (true) ** GOTO lbl2403
                            }
                            v631 = 1959341193 ^ -113403367;
                        }
                        block473: while (true) {
                            v634 = v635 / (-7624149994852754238L - 3475376318771218461L);
lbl2403:
                            // 2 sources

                            switch ((int)v634) {
                                case -495624045: {
                                    break block473;
                                }
                                case -18490371: {
                                    v635 = 2290932664195662641L ^ 6453014690513672480L;
                                    continue block473;
                                }
                            }
                            break;
                        }
                        v636 = v630.append(v632);
                        v637 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                        if (true) ** GOTO lbl2415
                        block474: while (true) {
                            v638 = -5674004271388822202L ^ -3299993413008546224L;
                            v637 = v639 / v638;
lbl2415:
                            // 2 sources

                            switch ((int)v637) {
                                case -495624045: {
                                    break block474;
                                }
                                case 844939601: {
                                    v639 = 4609610007374584332L >>> "\u0000\u0000".length();
                                    continue block474;
                                }
                            }
                            break;
                        }
                        v640 = v636.toString();
                        v641 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                        if (true) ** GOTO lbl2429
                        block475: while (true) {
                            v641 = v642 / (-814910778936880762L ^ -4789281613527590406L);
lbl2429:
                            // 2 sources

                            switch ((int)v641) {
                                case -495624045: {
                                    break block475;
                                }
                                case 77330005: {
                                    v642 = -6204945653512698856L ^ -7438359000787713684L;
                                    continue block475;
                                }
                                case 1248317166: {
                                    v642 = -7154169358472557010L - 7421382382928840570L;
                                    continue block475;
                                }
                            }
                            break;
                        }
                        CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1e3ecbb97 = 0QZ8.1d9kfLKTTP4TVZYm((String)v640);
                        v643 = 1368063930 - -1636562095;
                        if (CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1b61d8ff9 == null) break block589;
                        while (true) {
                            if ((v644 = (cfr_temp_42 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-770297137404716300L ^ 8634874126041069251L)) == 0L ? 0 : (cfr_temp_42 < 0L ? -1 : 1)) == false) continue;
                            v645 = -588212112 ^ -588212111;
                            if (v644 == v645) break;
                            v644 = -2099924384 - 1366988159;
                        }
                        v646 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                        block477: while (true) {
                            switch ((int)v646) {
                                case -495624045: {
                                    break block477;
                                }
                                case 1366648854: {
                                    v646 = (-3786589705843466767L ^ -8572248405151846199L) / (-8051104729022770927L ^ 5282453275867488719L);
                                    continue block477;
                                }
                            }
                            break;
                        }
                        v647 = new StringBuilder();
                        970030722 ^ 1810406226;
                        while (true) {
                            block590: {
                                if ((v648 = (cfr_temp_43 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (1068691833416867636L >>> "\u0000\u0000".length())) == 0L ? 0 : (cfr_temp_43 < 0L ? -1 : 1)) == false) continue;
                                if (v648 != (-457220362 ^ 457220361)) break block590;
                                v649 = v647.append(CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa157444164);
                                v650 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                                if (true) ** GOTO lbl2482
                            }
                            v648 = 318570769 - 1410296273;
                        }
                        block479: while (true) {
                            v651 = 2262751046953200840L ^ -4105817297478665159L;
                            v650 = v652 / v651;
lbl2482:
                            // 2 sources

                            switch ((int)v650) {
                                case -1754275499: {
                                    v652 = 8782914377144871645L - -194043873852430053L;
                                    continue block479;
                                }
                                case -495624045: {
                                    break block479;
                                }
                                case -147325073: {
                                    v652 = 4238585111777033872L ^ -6190966862456026264L;
                                    continue block479;
                                }
                                case 1384941777: {
                                    v652 = -5727299946714748503L - -7172525028962089450L;
                                    continue block479;
                                }
                            }
                            break;
                        }
                        var18_5 = new byte[1357306168 ^ 1357306162];
                        v653 = -1423709856 ^ -1423709853;
                        var18_5[v653] = 527599659 ^ 527599619;
                        v654 = 242700753 ^ 242700753;
                        v655 = 590168451 ^ 590168483;
                        var18_5[v654] = v655;
                        v656 = 2016409688 ^ 2016409694;
                        var18_5[v656] = 756215260 - 756215276;
                        var18_5[26878605 ^ 26878597] = 1256709051 ^ -1256709079;
                        var18_5[-5914356 ^ -5914360] = 311166885 ^ 311166851;
                        v657 = -1134701824 ^ -1134701817;
                        var18_5[v657] = -1512961463 - -1512961366;
                        var18_5[-1121376330 ^ -1121376329] = -395250561 ^ -395250599;
                        v658 = -363097797 ^ -363097799;
                        var18_5[v658] = 646505650 ^ 646505610;
                        var18_5[-1626174782 ^ -1626174777] = -1830854801 ^ -1830854899;
                        var18_5[1680152999 ^ 1680153006] = -76857586 ^ 76857472;
                        v659 = new StringBuilder().append(new String(var18_5, "UTF-8"));
                        v660 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                        if (true) ** GOTO lbl2519
                        block480: while (true) {
                            v660 = v661 / (5287313663742488865L ^ -4052694718365009625L);
lbl2519:
                            // 2 sources

                            switch ((int)v660) {
                                case -1305197578: {
                                    v661 = 4540601691509778590L ^ -6111721306923763415L;
                                    continue block480;
                                }
                                case -495624045: {
                                    break block480;
                                }
                                case -462312040: {
                                    v661 = -2651885198802156765L ^ -5384210285431505682L;
                                    continue block480;
                                }
                                case -260003329: {
                                    v661 = 3273000584264286659L ^ -5864188288553063283L;
                                    continue block480;
                                }
                            }
                            break;
                        }
                        v662 = v659.append(CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1b61d8ff9);
                        v663 = 1104822202 ^ 1104822201;
                        var18_5 = new byte[v663];
                        var18_5[-441876947 ^ -441876948] = -62509049 ^ -62508993;
                        var18_5[816557864 ^ 816557866] = 1774522966 ^ 1774523007;
                        var18_5[918258719 ^ 918258719] = -35722159 ^ -35722121;
                        v664 = new String(var18_5, "UTF-8");
                        while (true) {
                            v665 = 272952870337985710L ^ 4695362804683925854L;
                            cfr_temp_44 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v665;
                            v666 = cfr_temp_44 == 0L ? 0 : (cfr_temp_44 < 0L ? -1 : 1);
                            if (v666 == false) continue;
                            if (v666 == (929411498 ^ -929411499)) break;
                            v666 = 2049334112 ^ -2088850361;
                        }
                        v667 = v662.append(v664);
                        while (true) {
                            if ((v668 = (cfr_temp_45 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-6592122347049924711L ^ -9208023573471461671L)) == 0L ? 0 : (cfr_temp_45 < 0L ? -1 : 1)) == false) continue;
                            v669 = -1717067230 ^ 1717067229;
                            if (v668 == v669) break;
                            v668 = -582806840 ^ 1144759723;
                        }
                        v670 = v667.toString();
                        while (true) {
                            if ((v671 = (cfr_temp_46 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-2379567248434826668L ^ 6231831494451705968L)) == 0L ? 0 : (cfr_temp_46 < 0L ? -1 : 1)) == false) continue;
                            if (v671 == (-1709039505 ^ -1709039506)) break;
                            v671 = 112468439 - -865971473;
                        }
                        v672 = v649.append(0QZ8.1d9kfLKTTP4TVZYm((String)v670));
                        while (true) {
                            if ((v673 = (cfr_temp_47 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-8653187034959967142L ^ -2302810654445856836L)) == 0L ? 0 : (cfr_temp_47 < 0L ? -1 : 1)) == false) continue;
                            v674 = -199591281 ^ 199591280;
                            if (v673 == v674) {
                                CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa157444164 = v672.toString();
                                break;
                            }
                            v673 = 1034915569 - 362530987;
                        }
                    }
                    CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.sendMessage(CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa157444164);
                    while (true) {
                        block591: {
                            if ((v675 = (cfr_temp_48 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-2422983910408370612L >>> "\u0000\u0000".length())) == 0L ? 0 : (cfr_temp_48 < 0L ? -1 : 1)) == false) continue;
                            v676 = -873680102 ^ 873680101;
                            if (v675 != v676) break block591;
                            v677 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                            if (true) ** GOTO lbl2597
                        }
                        v675 = -696939918 - -122120650;
                    }
                    block486: while (true) {
                        v677 = v678 / (1385494077624906414L - -8516313265511451741L);
lbl2597:
                        // 2 sources

                        switch ((int)v677) {
                            case -998070602: {
                                v678 = -3191927368274112349L - 7452553938111530512L;
                                continue block486;
                            }
                            case -495624045: {
                                break block486;
                            }
                            case 513870071: {
                                v678 = -4695387743938778389L ^ -332400056183982281L;
                                continue block486;
                            }
                            case 624928917: {
                                v678 = -4790888589390426376L - -2033560252949138668L;
                                continue block486;
                            }
                        }
                        break;
                    }
                    var18_5 = new byte[-1146392034 ^ -1146392022];
                    v679 = 1955335832 ^ 1955335857;
                    v680 = -524711167 ^ -524711113;
                    var18_5[v679] = v680;
                    v681 = -863320442 ^ -863320345;
                    var18_5[-452295863 ^ -452295867] = v681;
                    v682 = -1618051485 ^ -1618051561;
                    var18_5[-1009651366 ^ -1009651378] = v682;
                    var18_5[192956174 ^ 192956167] = 407565033 ^ 407564954;
                    v683 = 1775265702 ^ 1775265760;
                    var18_5[-1453998560 ^ -1453998585] = v683;
                    v684 = -834677828 ^ -834677866;
                    var18_5[v684] = 670084864 ^ 670084917;
                    var18_5[-124870514 ^ -124870493] = 380146264 ^ 380146206;
                    v685 = -1186811318 ^ -1186811272;
                    v686 = -982107375 ^ -982107359;
                    var18_5[v685] = v686;
                    var18_5[-1191505721 ^ -1191505724] = 310192380 ^ 310192322;
                    var18_5[-1768606431 ^ -1768606429] = 1933984196 ^ 1933984250;
                    var18_5[529994673 ^ 529994667] = -1482701417 ^ -1482701397;
                    var18_5[455154666 ^ 455154673] = 1961014027 ^ 1961014124;
                    v687 = -1690715812 ^ -1690715854;
                    var18_5[-1790743642 ^ -1790743628] = v687;
                    var18_5[-576993953 ^ -576993984] = -1777555449 ^ -1777555346;
                    v688 = -1819208190 ^ -1819208124;
                    var18_5[38515852 ^ 38515876] = v688;
                    var18_5[476647593 ^ 476647609] = -730092041 ^ -730092134;
                    var18_5[450495345 ^ 450495326] = -1993214228 ^ -1993214296;
                    var18_5[1379686529 ^ 1379686540] = -1663706479 ^ -1663706369;
                    var18_5[1365313034 ^ 1365313034] = -1365463321 ^ -1365463359;
                    var18_5[600747261 ^ 600747256] = -2049254157 ^ -2049254187;
                    v689 = 668642705 ^ 668642746;
                    var18_5[v689] = -1332612290 ^ -1332612348;
                    var18_5[-1801424489 ^ -1801424511] = 1528841976 ^ 1528841950;
                    var18_5[-2140252852 ^ -2140252824] = 893340059 ^ 893340088;
                    var18_5[2051221662 ^ 2051221638] = -1667460972 ^ -1667460946;
                    v690 = 1642779561 ^ 1642779596;
                    var18_5[-1750481834 ^ -1750481851] = v690;
                    var18_5[-1243135050 ^ -1243135047] = 887733267 ^ 887733299;
                    v691 = -1574613051 ^ -1574613003;
                    var18_5[v691] = 1015500236 ^ 1015500277;
                    var18_5[296951312 ^ 296951319] = -899148844 ^ -899148925;
                    v692 = -1541983973 ^ -1541983979;
                    v693 = 1180563572 ^ 1180563473;
                    var18_5[v692] = v693;
                    var18_5[-1892519300 ^ -1892519317] = -1953724118 ^ -1953724142;
                    var18_5[4910146 ^ 4910193] = -612346159 ^ -612346129;
                    var18_5[-13361136 ^ -13361098] = -416823046 ^ -416823094;
                    var18_5[468668190 ^ 468668171] = -1497671498 ^ -1497671473;
                    v694 = -1633635414 ^ -1633635381;
                    var18_5[-1483633161 ^ -1483633174] = v694;
                    var18_5[-1736098510 ^ -1736098537] = 1237864338 ^ 1237864404;
                    v695 = 644402858 ^ 644402848;
                    var18_5[v695] = -1855927314 - -1855927255;
                    v696 = -335518360 ^ -335518382;
                    var18_5[613821711 ^ 613821740] = v696;
                    var18_5[-1034860363 ^ -1034860355] = 1980335734 ^ 1980335631;
                    var18_5[-1020581445 ^ -1020581470] = 124516562 ^ 124516594;
                    var18_5[2123195220 ^ 2123195221] = -1044028758 ^ -1044028782;
                    v697 = -2022040733 ^ -2022040749;
                    var18_5[464936658 ^ 464936675] = v697;
                    var18_5[-558798185 ^ -558798153] = -2014400594 ^ -2014400565;
                    v698 = 1401074437 ^ 1401074457;
                    var18_5[v698] = -1505255198 ^ -1505255280;
                    var18_5[-1310925955 ^ -1310925981] = 426330111 ^ 426330011;
                    var18_5[-333553733 ^ -333553767] = -510743305 ^ -510743421;
                    v699 = 119356439 ^ 119356475;
                    var18_5[v699] = 710867697 ^ 710867666;
                    var18_5[-61986277 ^ -61986251] = 453440312 ^ 453440382;
                    var18_5[1223907074 ^ 1223907081] = 520209604 ^ -520209594;
                    v700 = -560831691 ^ -560831653;
                    var18_5[-838621104 ^ -838621071] = v700;
                    var18_5[-1930120964 ^ -1930120966] = 2021221184 ^ 2021221239;
                    v701 = 840591879 ^ 840591911;
                    var18_5[-347403160 ^ -347403156] = v701;
                    var18_5[250559907 ^ 250559922] = -2049070446 ^ -2049070339;
                    v702 = new StringBuilder().append(new String(var18_5, "UTF-8"));
                    v703 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                    if (true) ** GOTO lbl2691
                    block487: while (true) {
                        v703 = v704 / (-6570611936119036146L ^ 2347579259030855889L);
lbl2691:
                        // 2 sources

                        switch ((int)v703) {
                            case -495624045: {
                                break block487;
                            }
                            case -438151574: {
                                v704 = 735219301529340975L ^ 8887493849114598358L;
                                continue block487;
                            }
                            case 1845705664: {
                                v704 = 8606468934962979461L - 1677032030247032160L;
                                continue block487;
                            }
                        }
                        break;
                    }
                    while (true) {
                        if ((v705 = (cfr_temp_49 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (1716805895615195254L ^ 6332392154251842369L)) == 0L ? 0 : (cfr_temp_49 < 0L ? -1 : 1)) == false) continue;
                        if (v705 == (922045188 ^ -922045189)) break;
                        v705 = 1091361005 ^ 2110474136;
                    }
                    v706 = 8\u0105ja.mUHYBBPpV50j2CtP((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e);
                    -1491382215 ^ -444668002;
                    while (true) {
                        block592: {
                            if ((v707 = (cfr_temp_50 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (5925469574561154604L ^ -7860640053708971294L)) == 0L ? 0 : (cfr_temp_50 < 0L ? -1 : 1)) == false) continue;
                            if (v707 != (-1750623852 ^ -1750623851)) break block592;
                            v708 = \u0144U\u0179f.5fEW.format(v706);
                            v709 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                            if (true) ** GOTO lbl2720
                        }
                        v707 = 110917218 ^ 1096492266;
                    }
                    block490: while (true) {
                        v710 = 1472667419118435964L ^ -7729530458167066692L;
                        v709 = v711 / v710;
lbl2720:
                        // 2 sources

                        switch ((int)v709) {
                            case -1269481291: {
                                v711 = 570858343950713718L - 7352527882697752493L;
                                continue block490;
                            }
                            case -870850963: {
                                v711 = 652745394097226375L - 879459284480774990L;
                                continue block490;
                            }
                            case -495624045: {
                                break block490;
                            }
                            case 1626977108: {
                                v711 = 5569765854450933174L ^ -6917000528889371632L;
                                continue block490;
                            }
                        }
                        break;
                    }
                    v712 = v702.append(v708);
                    v713 = 570311362 ^ 570311381;
                    var18_5 = new byte[v713];
                    var18_5[1374660711 ^ 1374660719] = 352386561 ^ 352386671;
                    v714 = 1526907370 ^ 1526907367;
                    var18_5[v714] = 1530397961 ^ 1530398001;
                    v715 = -61655166 ^ -61655156;
                    v716 = -1801363020 ^ -1801363044;
                    var18_5[v715] = v716;
                    var18_5[-1393392399 ^ -1393392414] = -1459437716 ^ -1459437811;
                    var18_5[794800061 ^ 794800040] = -795329116 ^ -795329123;
                    v717 = -2042323948 ^ -2042323945;
                    var18_5[v717] = 353293363 ^ 353293377;
                    var18_5[-2023237452 ^ -2023237467] = -1276572095 ^ -1276572043;
                    v718 = -1568257623 ^ -1568257618;
                    v719 = -355416018 ^ -355415989;
                    var18_5[v718] = v719;
                    var18_5[403716121 ^ 403716123] = -2008229257 ^ -2008229360;
                    var18_5[-1081442773 ^ -1081442782] = -980736067 ^ -980736055;
                    v720 = 1982125785 ^ 1982125778;
                    var18_5[v720] = 214548646 ^ 214548614;
                    v721 = 1607996205 ^ 1607996194;
                    v722 = -465654095 ^ -465654121;
                    var18_5[v721] = v722;
                    v723 = 678213336 ^ 678213341;
                    var18_5[v723] = -1359987534 ^ -1359987498;
                    var18_5[312010730 ^ 312010734] = 803961177 ^ 803961144;
                    v724 = -1245559823 ^ -1245559824;
                    var18_5[v724] = 334332379 ^ 334332404;
                    v725 = -845259817 ^ -845259799;
                    var18_5[567093536 ^ 567093546] = v725;
                    var18_5[636663505 ^ 636663517] = 1765820818 ^ 1765820852;
                    v726 = 959841489 ^ 959841495;
                    var18_5[v726] = -301827744 ^ -301827831;
                    var18_5[106066728 ^ 106066746] = 375006821 ^ 375006802;
                    var18_5[-1764776723 ^ -1764776711] = -799210980 ^ -799210965;
                    var18_5[1824464836 ^ 1824464850] = 768595554 ^ 768595543;
                    var18_5[-284300141 ^ -284300157] = -1469541946 ^ -1469541915;
                    var18_5[-482652111 ^ -482652111] = -4019704 ^ -4019660;
                    v727 = new String(var18_5, "UTF-8");
                    while (true) {
                        block593: {
                            if ((v728 = (cfr_temp_51 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-5125492272101745423L - 9209161932624418284L)) == 0L ? 0 : (cfr_temp_51 < 0L ? -1 : 1)) == false) continue;
                            if (v728 != 1535987360 - 1535987361) break block593;
                            var18_5 = new byte[-821836924 ^ -821836921];
                            v729 = 473546528 ^ 473546530;
                            var18_5[v729] = -585455738 ^ -585455624;
                            var18_5[1825706233 ^ 1825706232] = 1102448048 ^ 1102448003;
                            v730 = -615816484 ^ -615816484;
                            var18_5[v730] = -1241690982 ^ -1241690908;
                            v731 = new String(var18_5, "UTF-8");
                            v732 = 1118603302 ^ 1118603303;
                            var18_5 = new byte[v732];
                            var18_5[-1824232336 ^ -1824232336] = 291697620 ^ 291697658;
                            v733 = v712.append(v727).append(8\u0105ja.HDI5RgWorueWpl57((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e.replace(v731, new String(var18_5, "UTF-8"))));
                            v734 = 450668853 ^ 450668854;
                            var18_5 = new byte[v734];
                            v735 = -594300426 ^ -594300466;
                            var18_5[1427733837 ^ 1427733836] = v735;
                            var18_5[-1852477024 ^ -1852477024] = 1574235502 ^ 1574235464;
                            var18_5[1942605304 ^ 1942605306] = 111473036 ^ 111473061;
                            v736 = new String(var18_5, "UTF-8");
                            688592175 - -495305353;
                            v737 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                            if (true) ** GOTO lbl2805
                        }
                        v728 = 2127144020 - -222221820;
                    }
                    block492: while (true) {
                        v737 = v738 / (6583465817499862237L ^ -4948480499277642858L);
lbl2805:
                        // 2 sources

                        switch ((int)v737) {
                            case -495624045: {
                                break block492;
                            }
                            case -329273098: {
                                v738 = 5353660937112815821L ^ 779444991098375921L;
                                continue block492;
                            }
                            case 2089817689: {
                                v738 = -8492572752713481920L ^ 4037744389779578801L;
                                continue block492;
                            }
                        }
                        break;
                    }
                    v739 = v733.append(v736).toString();
                    -1044754528 ^ -1264305239;
                    v740 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                    if (true) ** GOTO lbl2828
                    block493: while (true) {
                        v741 = -2785965177376047318L ^ -8365914951094900500L;
                        v740 = v742 / v741;
lbl2828:
                        // 2 sources

                        switch ((int)v740) {
                            case -1033172531: {
                                v742 = 6959062418199450496L - 2970963932826873978L;
                                continue block493;
                            }
                            case -495624045: {
                                break block493;
                            }
                            case 1484531949: {
                                v742 = -3561522217954177130L - -411612473040970747L;
                                continue block493;
                            }
                        }
                        break;
                    }
                    v743 = 0QZ8.1d9kfLKTTP4TVZYm((String)v739);
                    v744 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                    if (true) ** GOTO lbl2843
                    block494: while (true) {
                        v745 = 2796530287759005180L ^ -3106994142872059863L;
                        v744 = v746 / v745;
lbl2843:
                        // 2 sources

                        switch ((int)v744) {
                            case -1299496947: {
                                v746 = -542407729452489319L ^ -7482048169759093871L;
                                continue block494;
                            }
                            case -495624045: {
                                break block494;
                            }
                            case 2018450830: {
                                v746 = -1465197890204643979L - -8850830278443319936L;
                                continue block494;
                            }
                        }
                        break;
                    }
                    CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.sendMessage(v743);
                    while (true) {
                        block594: {
                            if ((v747 = (cfr_temp_52 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (9160208402816954999L - -863973265749922446L)) == 0L ? 0 : (cfr_temp_52 < 0L ? -1 : 1)) == false) continue;
                            if (v747 != (-829807320 ^ -829807319)) break block594;
                            v748 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                            if (true) ** GOTO lbl2867
                        }
                        v747 = -319972639 ^ 1383931843;
                    }
                    block496: while (true) {
                        v748 = v749 / (7598946872351437267L ^ -8344379562945212062L);
lbl2867:
                        // 2 sources

                        switch ((int)v748) {
                            case -1814778083: {
                                v749 = 19704808598959795L ^ -7537239070482816773L;
                                continue block496;
                            }
                            case -495624045: {
                                break block496;
                            }
                            case 1399388676: {
                                v749 = -5671973838695956576L ^ 3320192596781828947L;
                                continue block496;
                            }
                        }
                        break;
                    }
                    v750 = new StringBuilder();
                    v751 = -512210924 ^ -512210906;
                    var18_5 = new byte[v751];
                    var18_5[1851698429 ^ 1851698392] = -709627796 ^ -709627862;
                    v752 = 215529053 ^ 215529085;
                    var18_5[1616741786 ^ 1616741781] = v752;
                    var18_5[-234769633 ^ -234769610] = 1305180254 ^ 1305180260;
                    var18_5[-375511515 ^ -375511539] = 297578742 ^ 297578703;
                    var18_5[942210466 ^ 942210490] = -2025083378 ^ -2025083342;
                    v753 = -426623619 ^ -426623729;
                    var18_5[835024441 ^ 835024419] = v753;
                    var18_5[1013711200 ^ 1013711204] = 1155192108 ^ 1155192076;
                    var18_5[-3153283 ^ -3153296] = 141954275 ^ 141954189;
                    var18_5[-1845757621 ^ -1845757590] = -1588019567 ^ -1588019541;
                    var18_5[106072921 ^ 106072902] = 1760122143 ^ 1760122225;
                    v754 = 668263563 ^ 668263585;
                    var18_5[v754] = -2068539535 ^ -2068539566;
                    var18_5[-21004609 ^ -21004636] = 1774236473 ^ 1774236504;
                    v755 = -1231324660 ^ -1231324622;
                    var18_5[-1027316791 ^ -1027316744] = v755;
                    v756 = -1826359783 ^ -1826359801;
                    var18_5[v756] = -394219529 ^ -394219630;
                    var18_5[-1300112058 ^ -1300112019] = 1291361855 ^ 1291361807;
                    var18_5[1626023518 ^ 1626023545] = -1036026216 ^ -1036026194;
                    v757 = -784962982 ^ -784963037;
                    var18_5[-2074379109 ^ -2074379117] = v757;
                    v758 = 434416703 ^ 434416761;
                    var18_5[2010445605 ^ 2010445579] = v758;
                    var18_5[1761473812 ^ 1761473792] = -54006144 ^ -54006106;
                    v759 = 1920844455 ^ 1920844510;
                    var18_5[1834209737 ^ 1834209754] = v759;
                    var18_5[308054545 ^ 308054528] = -847059804 ^ -847059771;
                    var18_5[-987603013 ^ -987603047] = -638266250 ^ -638266283;
                    var18_5[2121085492 ^ 2121085502] = 2066307910 ^ -2066307965;
                    v760 = -698836763 ^ -698836762;
                    var18_5[v760] = -1865472327 ^ -1865472377;
                    v761 = -139232796 ^ -139232776;
                    v762 = -146127783 ^ -146127811;
                    var18_5[v761] = v762;
                    v763 = -406683639 ^ -406683648;
                    var18_5[v763] = 306959974 ^ 306959893;
                    var18_5[-712338793 ^ -712338814] = -479274834 ^ -479274858;
                    v764 = 443399334 ^ 443399313;
                    var18_5[97914939 ^ 97914891] = v764;
                    v765 = -2062127948 ^ -2062127932;
                    var18_5[-1651061511 ^ -1651061525] = v765;
                    v766 = -1584547268 ^ -1584547297;
                    v767 = -186593088 ^ -186593034;
                    var18_5[v766] = v767;
                    var18_5[-34691248 ^ -34691237] = 1855667992 ^ -1855668070;
                    var18_5[360028798 ^ 360028754] = 1196089231 ^ 1196089279;
                    var18_5[-633775837 ^ -633775834] = 141014029 ^ 141014059;
                    var18_5[785800077 ^ 785800091] = -91903735 ^ -91903693;
                    var18_5[1923275516 ^ 1923275482] = 857278254 ^ 857278312;
                    var18_5[-419267547 ^ -419267531] = 692733403 ^ 692733359;
                    var18_5[-627783248 ^ -627783247] = 1635888545 ^ 1635888537;
                    var18_5[1288208058 ^ 1288208061] = 1087687369 ^ 1087687326;
                    var18_5[-1013079724 ^ -1013079692] = 1421848524 ^ 1421848504;
                    var18_5[-1130337023 ^ -1130337011] = -1093400615 ^ -1093400648;
                    v768 = -12060334 ^ -12060300;
                    var18_5[2036796732 ^ 2036796732] = v768;
                    v769 = 183920972 ^ 183920993;
                    var18_5[v769] = -2008875659 ^ -2008875725;
                    v770 = -128204874 ^ -128204920;
                    var18_5[553653502 ^ 553653500] = v770;
                    var18_5[1923314204 ^ 1923314177] = 441536842 ^ 441536803;
                    v771 = -1545175629 ^ -1545175677;
                    var18_5[1974440231 ^ 1974440200] = v771;
                    v772 = -1400694023 ^ -1400694116;
                    var18_5[2011469630 ^ 2011469616] = v772;
                    var18_5[-2090799221 ^ -2090799219] = -381601643 ^ -381601630;
                    var18_5[854845735 ^ 854845744] = -1998090080 ^ -1998090112;
                    v773 = 483960522 ^ 483960558;
                    var18_5[v773] = 1391420118 ^ 1391420131;
                    var18_5[1660489248 ^ 1660489273] = -477408776 ^ -477408865;
                    v774 = new String(var18_5, "UTF-8");
                    v775 = 1554807057 ^ 257824560;
                    -1489192489 - -1251221666;
                    while (true) {
                        block595: {
                            if ((v776 = (cfr_temp_53 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-7873678549987700148L ^ 6674094586630318556L)) == 0L ? 0 : (cfr_temp_53 < 0L ? -1 : 1)) == false) continue;
                            if (v776 != (-223757811 ^ -223757812)) break block595;
                            v777 = v750.append(v774);
                            v778 = \u0144U\u0179f.5fEW.format(8\u0105ja.HhF6UH640jkc43fN((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e));
                            v779 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                            if (true) ** GOTO lbl2969
                        }
                        v776 = -301609964 ^ 1655410951;
                    }
                    block498: while (true) {
                        v779 = v780 / (3911902433171617762L ^ 5783231805960362124L);
lbl2969:
                        // 2 sources

                        switch ((int)v779) {
                            case -495624045: {
                                break block498;
                            }
                            case 42337796: {
                                v780 = -7584018369767932289L ^ -2117644864661075987L;
                                continue block498;
                            }
                            case 527909276: {
                                v780 = 5725008352574649418L ^ -8581745348595961461L;
                                continue block498;
                            }
                        }
                        break;
                    }
                    v781 = 989519680 ^ 989519703;
                    var18_5 = new byte[v781];
                    var18_5[-958159476 ^ -958159488] = 1318674002 ^ 1318674036;
                    v782 = 1181797439 ^ 1181797424;
                    var18_5[v782] = -964258873 ^ -964258847;
                    v783 = -128433813 ^ -128433816;
                    v784 = 2009095116 ^ 2009095102;
                    var18_5[v783] = v784;
                    v785 = 1254514241 ^ 1254514281;
                    var18_5[-637874335 ^ -637874321] = v785;
                    var18_5[-1554371353 ^ -1554371345] = -996497864 ^ -996497834;
                    var18_5[1093074988 ^ 1093074981] = 1566371692 ^ 1566371608;
                    var18_5[-988799606 ^ -988799606] = 1932010379 ^ 1932010423;
                    v786 = 1632184649 ^ 1632184616;
                    var18_5[499460261 ^ 499460278] = v786;
                    var18_5[-919811381 ^ -919811379] = 1174819308 ^ 1174819205;
                    var18_5[202431415 ^ 202431399] = -367266610 ^ -367266579;
                    v787 = -1272728710 ^ -1272728713;
                    var18_5[v787] = 795616892 ^ 795616836;
                    v788 = -739830295 ^ -739830280;
                    v789 = 485969279 ^ 485969227;
                    var18_5[v788] = v789;
                    var18_5[1089794917 ^ 1089794931] = -24557501 ^ -24557450;
                    var18_5[367871645 ^ 367871642] = -1845924592 ^ -1845924491;
                    var18_5[-1729388114 ^ -1729388116] = 848185673 ^ 848185646;
                    v790 = 1840521146 ^ 1840521179;
                    var18_5[-1910305039 ^ -1910305035] = v790;
                    var18_5[2007558335 ^ 2007558325] = -1499212335 ^ -1499212305;
                    var18_5[1335965036 ^ 1335965031] = -2131987938 ^ -2131987906;
                    var18_5[937089814 ^ 937089811] = -630570690 ^ -630570662;
                    v791 = 1921241487 ^ 1921241499;
                    var18_5[v791] = 1368812830 ^ 1368812841;
                    var18_5[-147463837 ^ -147463818] = 1146341669 ^ 1146341660;
                    var18_5[925305844 ^ 925305845] = 1365536880 ^ 1365536863;
                    var18_5[415716147 ^ 415716129] = -952866834 ^ -952866855;
                    v792 = v777.append(v778).append(new String(var18_5, "UTF-8"));
                    var18_5 = new byte[-790304558 ^ -790304559];
                    var18_5[1963644976 ^ 1963644978] = -783992733 ^ -783992803;
                    var18_5[-465216019 ^ -465216019] = -294338593 ^ -294338655;
                    v793 = 562657315 ^ 562657296;
                    var18_5[-1300436354 ^ -1300436353] = v793;
                    v794 = new String(var18_5, "UTF-8");
                    var18_5 = new byte[-1079435259 ^ -1079435260];
                    v795 = -552964076 ^ -552964038;
                    var18_5[751423928 ^ 751423928] = v795;
                    v796 = CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e.replace(v794, new String(var18_5, "UTF-8"));
                    while (true) {
                        block596: {
                            if ((v797 = (cfr_temp_54 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-3140851380803769041L ^ 35897600578181385L)) == 0L ? 0 : (cfr_temp_54 < 0L ? -1 : 1)) == false) continue;
                            if (v797 != (-739213938 ^ -739213937)) break block596;
                            v798 = 8\u0105ja.XrKUnNuN0Gvkpq8y((String)v796);
                            v799 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                            if (true) ** GOTO lbl3038
                        }
                        v797 = 1752618679 ^ 342346778;
                    }
                    block500: while (true) {
                        v799 = v800 / (926107816201315329L ^ 3882945455578516770L);
lbl3038:
                        // 2 sources

                        switch ((int)v799) {
                            case -1306019189: {
                                v800 = 3339690107865542086L - 5673668237192875849L;
                                continue block500;
                            }
                            case -495624045: {
                                break block500;
                            }
                            case 691672621: {
                                v800 = 8082250195032425523L ^ -2522570593453115432L;
                                continue block500;
                            }
                        }
                        break;
                    }
                    v801 = v792.append(v798);
                    var18_5 = new byte[1195141001 ^ 1195141002];
                    v802 = -1907107356 ^ -1907107379;
                    var18_5[-1220374134 ^ -1220374136] = v802;
                    var18_5[1980484582 ^ 1980484582] = 789580199 ^ 789580161;
                    v803 = 227353044 ^ 227353068;
                    var18_5[1151353679 ^ 1151353678] = v803;
                    v804 = new String(var18_5, "UTF-8");
                    while (true) {
                        block597: {
                            if ((v805 = (cfr_temp_55 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (1019703623095507567L - 4991724449785793801L)) == 0L ? 0 : (cfr_temp_55 < 0L ? -1 : 1)) == false) continue;
                            if (v805 != (2000279390 ^ 2000279391)) break block597;
                            v806 = v801.append(v804);
                            v807 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                            if (true) ** GOTO lbl3066
                        }
                        v805 = 280814017 - -2036262727;
                    }
                    block502: while (true) {
                        v807 = v808 / (1160292765284276923L ^ -632097903828676970L);
lbl3066:
                        // 2 sources

                        switch ((int)v807) {
                            case -495624045: {
                                break block502;
                            }
                            case -241237401: {
                                v808 = -8007481538481621489L - -481515779890870627L;
                                continue block502;
                            }
                            case 1215041536: {
                                v808 = 3306356128054311448L - 8868349043239181465L;
                                continue block502;
                            }
                            case 1231141292: {
                                v808 = -5426558679737125323L - -6988572744421425784L;
                                continue block502;
                            }
                        }
                        break;
                    }
                    -1684938641 ^ 730089469;
                    v809 = 0QZ8.1d9kfLKTTP4TVZYm((String)v806.toString());
                    v810 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                    if (true) ** GOTO lbl3097
                    block503: while (true) {
                        v811 = -4834447700335166114L - 4881926553009270156L;
                        v810 = v812 / v811;
lbl3097:
                        // 2 sources

                        switch ((int)v810) {
                            case -722458600: {
                                v812 = -5911885817091585920L - 6455158925792448501L;
                                continue block503;
                            }
                            case -495624045: {
                                break block503;
                            }
                            case 1224579280: {
                                v812 = 8724221557566957899L - 3934530480779736512L;
                                continue block503;
                            }
                        }
                        break;
                    }
                    CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.sendMessage(v809);
                    if (CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1134411b4 == null) break block598;
                    if (CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ba622721 == null) break block598;
                    v813 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                    if (true) ** GOTO lbl3124
                    block504: while (true) {
                        v813 = v814 / (8612948997192808584L ^ -132883275545719042L);
lbl3124:
                        // 2 sources

                        switch ((int)v813) {
                            case -1332882882: {
                                v814 = 7340112304597913418L ^ -2745035321377411931L;
                                continue block504;
                            }
                            case -495624045: {
                                break block504;
                            }
                            case -445607368: {
                                v814 = 4793842247881626024L ^ 4254714196762154553L;
                                continue block504;
                            }
                            case 1127621262: {
                                v814 = -6441929813683617654L - 2676522285377238032L;
                                continue block504;
                            }
                        }
                        break;
                    }
                    while (true) {
                        block599: {
                            if ((v815 = (cfr_temp_56 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (6368767294373274142L ^ -6428378785753118163L)) == 0L ? 0 : (cfr_temp_56 < 0L ? -1 : 1)) == false) continue;
                            if (v815 != (2047138623 ^ -2047138624)) break block599;
                            v816 = new StringBuilder();
                            v817 = -1427870942 ^ -1427870972;
                            var18_5 = new byte[v817];
                            v818 = -273486383 ^ -273486389;
                            var18_5[v818] = -1693751584 ^ -1693751671;
                            v819 = 1437906978 ^ 1437907035;
                            var18_5[629994674 ^ 629994685] = v819;
                            var18_5[-2114209214 ^ -2114209187] = -1108538428 ^ -1108538445;
                            var18_5[-2003825836 ^ -2003825831] = 1548406223 ^ 1548406255;
                            v820 = -1957726308 ^ -1957726305;
                            var18_5[v820] = -992296815 ^ -992296785;
                            var18_5[-320384422 ^ -320384424] = -863334357 ^ -863334379;
                            v821 = -656234299 ^ -656234267;
                            var18_5[v821] = -1467353125 ^ -1467353091;
                            var18_5[-1202883865 ^ -1202883872] = 1681561636 ^ 1681561716;
                            var18_5[-1486131668 ^ -1486131657] = 1395451945 ^ 1395451983;
                            var18_5[872895439 ^ 872895431] = -99929459 ^ -99929374;
                            v822 = 33896357 ^ 33896351;
                            var18_5[-1418333698 ^ -1418333732] = v822;
                            var18_5[1904755127 ^ 1904755133] = -2099279473 ^ -2099279386;
                            var18_5[1840451746 ^ 1840451747] = 339103510 ^ 339103534;
                            v823 = -247850678 ^ -247850715;
                            var18_5[1609752788 ^ 1609752799] = v823;
                            v824 = 19136126 ^ 19136019;
                            var18_5[1186421027 ^ 1186421039] = v824;
                            v825 = 1212087210 ^ 1212087178;
                            var18_5[1598957175 ^ 1598957167] = v825;
                            var18_5[1180455993 ^ 1180455964] = 1310318981 ^ 1310319075;
                            var18_5[2076049547 ^ 2076049583] = -1054569289 ^ -1054569327;
                            v826 = -1521608767 ^ -1521608750;
                            var18_5[v826] = -1829521523 ^ -1829521428;
                            v827 = 317842532 ^ 317842461;
                            var18_5[247867166 ^ 247867147] = v827;
                            v828 = -607329867 ^ -607329876;
                            var18_5[v828] = -92659688 ^ -92659585;
                            var18_5[724443806 ^ 724443837] = -614265409 ^ -614265441;
                            v829 = 927851672 - 927851731;
                            var18_5[-1127903403 ^ -1127903420] = v829;
                            v830 = 383504103 ^ 383504015;
                            var18_5[-195126897 ^ -195126888] = v830;
                            var18_5[-1978481652 ^ -1978481656] = 1326893521 ^ 1326893553;
                            v831 = -1870124075 ^ -1870124087;
                            var18_5[v831] = 983934916 ^ 983934896;
                            var18_5[-145656504 ^ -145656482] = -51298523 ^ -51298490;
                            var18_5[-1548585073 ^ -1548585079] = -126918750 ^ -126918763;
                            v832 = -1192608061 - -1192608000;
                            var18_5[-451181383 ^ -451181404] = v832;
                            var18_5[1288349677 ^ 1288349667] = -1753697046 ^ -1753697123;
                            var18_5[962928318 ^ 962928311] = 566491322 ^ 566491328;
                            var18_5[-531781613 ^ -531781625] = -1556384757 ^ -1556384667;
                            var18_5[1354396754 ^ 1354396736] = 1395949866 - 1395949992;
                            var18_5[-744158009 ^ -744157991] = 2752384 ^ -2752461;
                            v833 = -1860956379 ^ -1860956387;
                            var18_5[-312538076 ^ -312538107] = v833;
                            var18_5[947033804 ^ 947033820] = 1164232510 ^ 1164232525;
                            var18_5[2097182203 ^ 2097182206] = 268247301 ^ 268247331;
                            var18_5[1446719260 ^ 1446719260] = 983496748 ^ 983496714;
                            v834 = new String(var18_5, "UTF-8");
                            v835 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                            if (true) ** GOTO lbl3205
                        }
                        v815 = 107066774 ^ 1977662171;
                    }
                    block506: while (true) {
                        v835 = v836 / (-1305165394818932474L ^ -8400428096058593536L);
lbl3205:
                        // 2 sources

                        switch ((int)v835) {
                            case -1664235097: {
                                v836 = -5431064392733688238L ^ -3343929744622801207L;
                                continue block506;
                            }
                            case -495624045: {
                                break block506;
                            }
                            case 723355459: {
                                v836 = -1970012912499428668L ^ 8233375880379989903L;
                                continue block506;
                            }
                            case 942006402: {
                                v836 = 9073273157532891926L - -5470161087339076075L;
                                continue block506;
                            }
                        }
                        break;
                    }
                    v837 = v816.append(v834);
                    while (true) {
                        block600: {
                            if ((v838 = (cfr_temp_57 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-1240719319915659236L - 7675560351486365159L)) == 0L ? 0 : (cfr_temp_57 < 0L ? -1 : 1)) == false) continue;
                            if (v838 != (-972097045 ^ -972097046)) break block600;
                            v839 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                            if (true) ** GOTO lbl3230
                        }
                        v838 = 137701849 ^ -1384740588;
                    }
                    block508: while (true) {
                        v840 = 5859337764783627500L ^ -9056796920410332094L;
                        v839 = v841 / v840;
lbl3230:
                        // 2 sources

                        switch ((int)v839) {
                            case -990164992: {
                                v841 = 2270745792001970976L >>> "\u0000\u0000".length();
                                continue block508;
                            }
                            case -495624045: {
                                break block508;
                            }
                            case -259927164: {
                                v841 = -3578343503649095702L - -225569589899362439L;
                                continue block508;
                            }
                            case 398162911: {
                                v841 = -8362678711551472786L ^ 7844513979323442481L;
                                continue block508;
                            }
                        }
                        break;
                    }
                    v842 = new StringBuilder();
                    var18_5 = new byte[131637142 ^ 131637149];
                    var18_5[516411962 ^ 516411961] = 1750222688 ^ 1750222612;
                    var18_5[-1558627 ^ -1558628] = 1481207439 ^ 1481207546;
                    v843 = 1540922232 ^ 1540922129;
                    var18_5[-1973032396 ^ -1973032388] = v843;
                    v844 = 550713363 ^ 550713470;
                    var18_5[1226233843 ^ 1226233846] = v844;
                    v845 = 1562919822 ^ 1562919913;
                    var18_5[-1176391870 ^ -1176391867] = v845;
                    v846 = -678028886 ^ -678028886;
                    var18_5[v846] = 2120498011 ^ 2120497976;
                    var18_5[1244657431 ^ 1244657427] = 1027549555 ^ 1027549468;
                    v847 = 1142308546 ^ 1142308555;
                    v848 = 1884366670 ^ 1884366632;
                    var18_5[v847] = v848;
                    var18_5[-1023599282 ^ -1023599284] = -1874184735 ^ -1874184814;
                    var18_5[595488976 ^ 595488986] = 930460333 ^ 930460377;
                    v849 = -230639441 ^ -230639376;
                    var18_5[-611279785 ^ -611279791] = v849;
                    v850 = new String(var18_5, "UTF-8");
                    v851 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                    if (true) ** GOTO lbl3267
                    block509: while (true) {
                        v851 = v852 / (-7780439348746816345L - 2719887516733088359L);
lbl3267:
                        // 2 sources

                        switch ((int)v851) {
                            case -495624045: {
                                break block509;
                            }
                            case -398050456: {
                                v852 = 8951028496645863452L ^ 4321478949980023402L;
                                continue block509;
                            }
                            case -112940909: {
                                v852 = -6077042116477954719L - -5880621461785556351L;
                                continue block509;
                            }
                            case 610042631: {
                                v852 = -1901927064629070071L - -6801314196554080379L;
                                continue block509;
                            }
                        }
                        break;
                    }
                    v853 = v842.append(v850);
                    v854 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                    block510: while (true) {
                        switch ((int)v854) {
                            case -495624045: {
                                break block510;
                            }
                            case 466584012: {
                                v854 = (1349815388036385135L - 8698254875648286816L) / (4343847508041315984L - -6058748408716665619L);
                                continue block510;
                            }
                        }
                        break;
                    }
                    v855 = e4wV.2al_94WjzQTnIzkU((String)v853.append((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ba622721).toString());
                    v856 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                    if (true) ** GOTO lbl3294
                    block511: while (true) {
                        v856 = v857 / (3237687349669145697L - 8499979335646310376L);
lbl3294:
                        // 2 sources

                        switch ((int)v856) {
                            case -1460353346: {
                                v857 = -1223273988292439933L ^ 7318306278708674092L;
                                continue block511;
                            }
                            case -1005826313: {
                                v857 = 6464176039938091357L - -6831478429809769373L;
                                continue block511;
                            }
                            case -495624045: {
                                break block511;
                            }
                        }
                        break;
                    }
                    v858 = v837.append(v855);
                    var18_5 = new byte[827848309 ^ 827848303];
                    var18_5[2069921274 ^ 2069921271] = -1173171943 ^ -1173171873;
                    var18_5[-2002757308 ^ -2002757283] = -541411464 ^ -541411514;
                    var18_5[1001667 ^ 1001691] = -44568825 ^ -44568767;
                    v859 = 2090962042 ^ 2090961980;
                    var18_5[-760667652 ^ -760667668] = v859;
                    var18_5[55515750 ^ 55515756] = 1533508741 ^ 1533508774;
                    var18_5[-802198863 ^ -802198876] = 124776996 ^ 124777058;
                    var18_5[-1210101705 ^ -1210101698] = 1519767283 ^ 1519767241;
                    v860 = -1243887043 ^ -1243887016;
                    var18_5[119232232 ^ 119232238] = v860;
                    var18_5[1128428896 ^ 1128428897] = 1630525976 ^ 1630526079;
                    var18_5[344008520 ^ 344008540] = -518799258 ^ -518799325;
                    var18_5[918202040 ^ 918202036] = 358277109 ^ 358277061;
                    v861 = 1561666347 ^ 1561666344;
                    var18_5[v861] = -1699804882 ^ -1699804849;
                    v862 = -861595465 ^ -861595406;
                    var18_5[2115048826 ^ 2115048813] = v862;
                    var18_5[-1228069708 ^ -1228069722] = 199597282 ^ 199597249;
                    var18_5[-1688740886 ^ -1688740868] = -1552476935 ^ -1552476993;
                    var18_5[-792075521 ^ -792075521] = 71145653 ^ 71145609;
                    var18_5[602934497 ^ 602934514] = -1827445032 ^ -1827445013;
                    var18_5[866365252 ^ 866365258] = -505534169 ^ -505534188;
                    v863 = 1544016107 ^ 1544016031;
                    var18_5[434550559 ^ 434550551] = v863;
                    v864 = -910283145 ^ -910283187;
                    var18_5[2120056478 ^ 2120056463] = v864;
                    v865 = 392753906 ^ 392753843;
                    var18_5[1192018496 ^ 1192018507] = v865;
                    v866 = -999395477 ^ -999395474;
                    var18_5[v866] = 1030015438 ^ 1030015399;
                    var18_5[-308888656 ^ -308888654] = 452694174 ^ 452694252;
                    var18_5[-1288799471 ^ -1288799466] = 1786223895 ^ 1786223993;
                    var18_5[717096279 ^ 717096280] = -1758594990 ^ -1758595052;
                    v867 = -1289483797 ^ -1289483793;
                    var18_5[v867] = 1767512938 ^ 1767512846;
                    v868 = new String(var18_5, "UTF-8");
                    while (true) {
                        if ((v869 = (cfr_temp_58 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-5928370523474425722L - 8249316770595707960L)) == 0L ? 0 : (cfr_temp_58 < 0L ? -1 : 1)) == false) continue;
                        if (v869 == (-1020059616 ^ 1020059615)) break;
                        v869 = -1431517205 - -411346987;
                    }
                    v870 = v858.append(v868).append((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1134411b4);
                    var18_5 = new byte[-2127689616 ^ -2127689605];
                    v871 = 407645858 ^ 407645893;
                    var18_5[77858027 ^ 77858025] = v871;
                    var18_5[1510041070 ^ 1510041066] = -1478384949 ^ -1478384982;
                    var18_5[-551718920 ^ -551718913] = 1218885374 ^ 1218885275;
                    v872 = 2079297677 ^ 2079297698;
                    var18_5[-1885893382 ^ -1885893381] = v872;
                    var18_5[-159024852 ^ -159024860] = 1350851919 ^ 1350851873;
                    var18_5[2045750231 ^ 2045750228] = -869157004 ^ -869157114;
                    var18_5[-1534079030 ^ -1534079040] = -1452034956 ^ -1452034998;
                    v873 = 1185592394 ^ 1185592399;
                    v874 = 1475428129 ^ 1475428165;
                    var18_5[v873] = v874;
                    v875 = 1606975398 ^ 1606975407;
                    var18_5[v875] = -574113766 ^ -574113682;
                    v876 = 1030816714 ^ 1030816758;
                    var18_5[-34606819 ^ -34606819] = v876;
                    v877 = 665701943 ^ 665701937;
                    v878 = 643244225 ^ 643244200;
                    var18_5[v877] = v878;
                    v879 = new String(var18_5, "UTF-8");
                    while (true) {
                        if ((v880 = (cfr_temp_59 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-488732091594304932L >>> "\u0000\u0000".length())) == 0L ? 0 : (cfr_temp_59 < 0L ? -1 : 1)) == false) continue;
                        v881 = -1878970745 ^ 1878970744;
                        if (v880 == v881) break;
                        v880 = 1116100436 ^ 966610183;
                    }
                    v882 = v870.append(v879);
                    v883 = 481784763 ^ -1573993231;
                    v884 = 679885412 ^ 82735327;
                    while (true) {
                        v885 = -1620089758469539083L ^ 4895116111223842890L;
                        cfr_temp_60 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v885;
                        v886 = cfr_temp_60 == 0L ? 0 : (cfr_temp_60 < 0L ? -1 : 1);
                        if (v886 == false) continue;
                        if (v886 == (-167561118 ^ -167561117)) break;
                        v886 = 784942928 ^ -1032812618;
                    }
                    v887 = v882.toString();
                    while (true) {
                        block601: {
                            if ((v888 = (cfr_temp_61 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-1151836028443138453L ^ 811474170621299118L)) == 0L ? 0 : (cfr_temp_61 < 0L ? -1 : 1)) == false) continue;
                            v889 = -2033314300 ^ 2033314299;
                            if (v888 != v889) break block601;
                            v890 = 0QZ8.1d9kfLKTTP4TVZYm((String)v887);
                            v891 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                            if (true) ** GOTO lbl3406
                        }
                        v888 = 1747807302 - 323401677;
                    }
                    block516: while (true) {
                        v892 = 2384760712236555250L ^ -2824048631455578195L;
                        v891 = v893 / v892;
lbl3406:
                        // 2 sources

                        switch ((int)v891) {
                            case -495624045: {
                                break block516;
                            }
                            case -126421243: {
                                v893 = -6260040760855476018L ^ -8788680850623320496L;
                                continue block516;
                            }
                            case 1177946503: {
                                v893 = -6072649545536712285L ^ -473779297291170152L;
                                continue block516;
                            }
                            case 1836302304: {
                                v893 = -5270911949417234315L ^ 964023979833147613L;
                                continue block516;
                            }
                        }
                        break;
                    }
                    CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.sendMessage(v890);
                }
                -1782107304 - -574619620;
                if (CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa123343819 == null) break block602;
                v894 = -1081544686 ^ -1081544685;
                var18_5 = new byte[v894];
                var18_5[2000987495 ^ 2000987495] = -876526829 ^ -876526813;
                if (CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa123343819.equalsIgnoreCase(new String(var18_5, "UTF-8"))) break block602;
                -1897104373 - 1530510107;
                while (true) {
                    block603: {
                        if ((v895 = (cfr_temp_62 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (6312493548425626014L ^ -2457708325993932330L)) == 0L ? 0 : (cfr_temp_62 < 0L ? -1 : 1)) == false) continue;
                        if (v895 != (-1816761830 ^ -1816761829)) break block603;
                        v896 = new StringBuilder();
                        var18_5 = new byte[-790937624 ^ -790937611];
                        var18_5[-473485039 ^ -473485028] = -2101859732 ^ -2101859764;
                        var18_5[452624362 ^ 452624363] = -1903517581 ^ -1903517621;
                        v897 = -1289017591 ^ -1289017600;
                        var18_5[v897] = -1098517795 ^ -1098517849;
                        v898 = -1904931799 ^ -1904931790;
                        var18_5[v898] = -711580349 ^ -711580315;
                        var18_5[1447871054 ^ 1447871046] = 1910542099 ^ 1910542204;
                        var18_5[-2084427734 ^ -2084427722] = 1975680652 ^ 1975680746;
                        v899 = 1080316318 ^ 1080316299;
                        var18_5[v899] = 1781692300 ^ 1781692398;
                        var18_5[-2079236725 ^ -2079236724] = 1317000013 ^ 1316999965;
                        v900 = 391332464 ^ 391332438;
                        var18_5[-717268735 ^ -717268714] = v900;
                        var18_5[1817074333 ^ 1817074317] = -2079012969 ^ -2079012871;
                        var18_5[-518149366 ^ -518149346] = -231078258 ^ -231078149;
                        var18_5[915946376 ^ 915946371] = 22712784 ^ 22712767;
                        var18_5[-1296235374 ^ -1296235369] = -1110410887 ^ -1110410913;
                        v901 = -1181759991 ^ -1181759994;
                        var18_5[v901] = -263483513 ^ -263483418;
                        var18_5[-1919541626 ^ -1919541622] = 685279213 ^ 685279104;
                        v902 = 72773350 ^ 72773344;
                        var18_5[v902] = 1879961245 ^ 1879961258;
                        v903 = 763664607 ^ 763664588;
                        var18_5[v903] = -432670640 ^ -432670660;
                        var18_5[-104806624 ^ -104806621] = -2016709063 ^ -2016709113;
                        v904 = -96791213 ^ -96791227;
                        v905 = 1825568949 ^ 1825568960;
                        var18_5[v904] = v905;
                        v906 = -1568925686 ^ -1568925654;
                        var18_5[2010995685 ^ 2010995711] = v906;
                        var18_5[1197620760 ^ 1197620758] = -971979388 ^ -971979294;
                        var18_5[-230394542 ^ -230394560] = -1548001358 ^ -1548001319;
                        var18_5[-1296280811 ^ -1296280811] = -1219922448 ^ -1219922474;
                        v907 = 1694128831 ^ 1694128807;
                        var18_5[v907] = 1219684661 ^ 1219684621;
                        var18_5[1552418883 ^ 1552418898] = -1542382410 ^ -1542382442;
                        var18_5[628223132 ^ 628223126] = 150687170 ^ 150687147;
                        var18_5[90759165 ^ 90759161] = -1970300729 ^ -1970300697;
                        v908 = 640742413 ^ 640742415;
                        var18_5[v908] = 903677062 ^ 903677112;
                        var18_5[-1881244995 ^ -1881245020] = 600933576 ^ 600933618;
                        v909 = new String(var18_5, "UTF-8");
                        v910 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                        if (true) ** GOTO lbl3509
                    }
                    v895 = 0x57711711 ^ 145956061;
                }
                block518: while (true) {
                    v910 = v911 / (-5241771395191620705L ^ 3435811806805823830L);
lbl3509:
                    // 2 sources

                    switch ((int)v910) {
                        case -1593547711: {
                            v911 = -5116572295871519133L - 4252311444834186580L;
                            continue block518;
                        }
                        case -1381458902: {
                            v911 = 7399843768247604344L ^ -4326723770419589246L;
                            continue block518;
                        }
                        case -495624045: {
                            break block518;
                        }
                        case -36338541: {
                            v911 = 1082193489080150461L ^ -4336799979099437203L;
                            continue block518;
                        }
                    }
                    break;
                }
                v912 = v896.append(v909);
                v913 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                if (true) ** GOTO lbl3528
                block519: while (true) {
                    v913 = v914 / (-3606060532212044973L - 4145801997955680697L);
lbl3528:
                    // 2 sources

                    switch ((int)v913) {
                        case -495624045: {
                            break block519;
                        }
                        case 287358007: {
                            v914 = 1788038519996619460L ^ -8472426351129483696L;
                            continue block519;
                        }
                    }
                    break;
                }
                v915 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                block520: while (true) {
                    switch ((int)v915) {
                        case -495624045: {
                            break block520;
                        }
                        case -147921052: {
                            v915 = (4189308230819517830L - 6553400328506073844L) / (-5037690816401173238L ^ -377892422522139292L);
                            continue block520;
                        }
                    }
                    break;
                }
                985555440 ^ 238556926;
                var18_5 = new byte[165790932 ^ 165790943];
                v916 = -744913766 ^ -744913761;
                var18_5[v916] = -187327751 ^ -187327852;
                var18_5[-734259299 ^ -734259308] = -269443296 ^ -269443263;
                var18_5[1111993514 ^ 1111993515] = 1537624369 ^ 1537624388;
                v917 = 1586090290 ^ 1586090296;
                var18_5[v917] = 1261152131 ^ 1261152238;
                v918 = 1473373927 ^ 1473373826;
                var18_5[-986457235 ^ -986457243] = v918;
                var18_5[474937573 ^ 474937569] = 136791121 ^ 136791102;
                var18_5[-1772269710 ^ -1772269712] = -1141935586 ^ -1141935507;
                var18_5[983976756 ^ 983976755] = -1066622992 ^ -1066623100;
                var18_5[108354687 ^ 108354681] = -496465450 ^ -496465527;
                v919 = -1235992013 ^ -1235991984;
                var18_5[-435598146 ^ -435598146] = v919;
                v920 = 1777043009 ^ 1777043010;
                var18_5[v920] = 904287403 ^ 904287455;
                v921 = new StringBuilder().append(new String(var18_5, "UTF-8"));
                while (true) {
                    block604: {
                        if ((v922 = (cfr_temp_63 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (7055562999656431412L ^ -8403466406784147639L)) == 0L ? 0 : (cfr_temp_63 < 0L ? -1 : 1)) == false) continue;
                        if (v922 != (-1025410555 ^ 1025410554)) break block604;
                        v923 = v921.append((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa123343819);
                        1538897260 ^ 392294152;
                        v924 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                        if (true) ** GOTO lbl3581
                    }
                    v922 = 1433343115 ^ -615109425;
                }
                block522: while (true) {
                    v924 = v925 / (-1170014423369643268L ^ 8032778677403579485L);
lbl3581:
                    // 2 sources

                    switch ((int)v924) {
                        case -2093390596: {
                            v925 = 6720724709687250677L ^ -6505889275223067394L;
                            continue block522;
                        }
                        case -2060327205: {
                            v925 = 5086017280410566381L ^ 92564404440057317L;
                            continue block522;
                        }
                        case -495624045: {
                            break block522;
                        }
                        case 1921084318: {
                            v925 = -7155287307584077743L ^ 583644369680717250L;
                            continue block522;
                        }
                    }
                    break;
                }
                -1520314192 - 722600306;
                v926 = v912.append(e4wV.2al_94WjzQTnIzkU((String)v923.toString()));
                var18_5 = new byte[1316991828 ^ 1316991822];
                var18_5[1588658199 ^ 1588658198] = 1991970781 ^ 1991970746;
                v927 = 413116422 ^ 413116476;
                var18_5[-2138219072 ^ -2138219063] = v927;
                var18_5[12729456 ^ 12729460] = 680570310 ^ 680570274;
                var18_5[907479732 ^ 907479729] = 1710807283 ^ 1710807194;
                v928 = 991365447 ^ 991365427;
                var18_5[1245084768 ^ 1245084776] = v928;
                v929 = 1604723038 ^ 1604723018;
                v930 = -698145014 ^ -698144948;
                var18_5[v929] = v930;
                var18_5[-755609968 ^ -755609978] = 1885839102 ^ 1885839037;
                var18_5[-363329259 ^ -363329262] = -1536506621 ^ -1536506515;
                v931 = -828273824 ^ -828273822;
                var18_5[v931] = -204578385 ^ -204578339;
                var18_5[-951815748 ^ -951815753] = 104687468 ^ 104687402;
                var18_5[442054361 ^ 442054344] = -1266502520 ^ -1266502478;
                v932 = -762360921 ^ -762360919;
                var18_5[v932] = 421801775 ^ 421801834;
                v933 = 836393322 ^ 836393305;
                var18_5[-269446922 ^ -269446943] = v933;
                var18_5[-73370533 ^ -73370543] = -452030359 ^ -452030390;
                var18_5[-1533509979 ^ -1533509962] = 114426205 ^ 114426139;
                var18_5[-438781881 ^ -438781857] = 1562338537 ^ 1562338476;
                var18_5[-444690117 ^ -444690122] = 1079409801 ^ 1079409869;
                var18_5[1592291998 ^ 1592291985] = -1664356066 ^ -1664356001;
                v934 = 63323955 ^ 63323955;
                var18_5[v934] = 666389921 ^ 666389917;
                v935 = 2113854861 ^ 2113854859;
                var18_5[v935] = -1901040207 ^ -1901040172;
                var18_5[875753810 ^ 875753822] = 984414415 ^ 984414345;
                v936 = 2187551 ^ 2187533;
                var18_5[v936] = -1037031689 ^ -1037031724;
                var18_5[1570452972 ^ 1570452988] = -400346372 ^ -400346420;
                var18_5[91242722 ^ 91242721] = 1402024150 ^ 1402024119;
                var18_5[1169975514 ^ 1169975503] = -485991596 ^ -485991659;
                var18_5[1048273884 ^ 1048273861] = -746840745 ^ -746840727;
                v937 = new String(var18_5, "UTF-8");
                while (true) {
                    v938 = 1611821337392219460L ^ 6269927758566964864L;
                    cfr_temp_64 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v938;
                    v939 = cfr_temp_64 == 0L ? 0 : (cfr_temp_64 < 0L ? -1 : 1);
                    if (v939 == false) continue;
                    if (v939 == -1978821568 - -1978821567) break;
                    v939 = 1027098599 - 1635183320;
                }
                -305112750 - -1566182825;
                -1905550187 - 1556033277;
                var18_5 = new byte[-875556386 ^ -875556395];
                var18_5[749201654 ^ 749201649] = -557368192 ^ -557368091;
                var18_5[-1890112120 ^ -1890112126] = -316692033 ^ -316692095;
                v940 = 1837926542 ^ 1837926543;
                v941 = 1244370106 ^ 1244370069;
                var18_5[v940] = v941;
                var18_5[1985320256 ^ 1985320258] = -2145026143 ^ -2145026106;
                v942 = -765692875 ^ -765692875;
                var18_5[v942] = 316209839 ^ 316209811;
                v943 = 1048361565 ^ 1048361529;
                var18_5[-1447788694 ^ -1447788689] = v943;
                var18_5[-991926209 ^ -991926212] = 1419811730 ^ 1419811808;
                v944 = 43292742 ^ 43292736;
                var18_5[v944] = 1557720866 ^ 1557720907;
                v945 = 1068619383 ^ 1068619391;
                var18_5[v945] = 1091495297 ^ 1091495407;
                v946 = -389815857 ^ -389815861;
                var18_5[v946] = 77745705 ^ 77745736;
                var18_5[-1757476188 ^ -1757476179] = -142014567 ^ -142014483;
                v947 = v926.append(v937).append((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa123343819).append(new String(var18_5, "UTF-8"));
                while (true) {
                    v948 = -2414632001185369570L ^ 2882886452609373069L;
                    cfr_temp_65 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v948;
                    v949 = cfr_temp_65 == 0L ? 0 : (cfr_temp_65 < 0L ? -1 : 1);
                    if (v949 == false) continue;
                    v950 = -1650010687 ^ 1650010686;
                    if (v949 == v950) break;
                    v949 = -878823638 ^ 1937768606;
                }
                v951 = v947.toString();
                while (true) {
                    if ((v952 = (cfr_temp_66 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (2301835822060551691L ^ 6059138120007517272L)) == 0L ? 0 : (cfr_temp_66 < 0L ? -1 : 1)) == false) continue;
                    if (v952 == (1379007612 ^ -1379007613)) break;
                    v952 = 882613607 - -1157720335;
                }
                v953 = 0QZ8.1d9kfLKTTP4TVZYm((String)v951);
                while (true) {
                    if ((v954 = (cfr_temp_67 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (159486411390875899L ^ -4280846676396757265L)) == 0L ? 0 : (cfr_temp_67 < 0L ? -1 : 1)) == false) continue;
                    if (v954 == (-1274183417 ^ -1274183418)) {
                        CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.sendMessage(v953);
                        break;
                    }
                    v954 = 888840159 ^ -267969798;
                }
            }
            v955 = -2106035605 - -794033731;
            v956 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
            block527: while (true) {
                switch ((int)v956) {
                    case -495624045: {
                        break block527;
                    }
                    case 1827822670: {
                        v956 = (-4665683170691306453L ^ -7159455340089028344L) / (-8873616836388976611L ^ -1759743563886254367L);
                        continue block527;
                    }
                }
                break;
            }
            384917345 - -2056606902;
            v957 = new StringBuilder();
            var18_5 = new byte[-1224098310 ^ -1224098326];
            v958 = -299831463 ^ -299831470;
            var18_5[v958] = -1638604764 ^ -1638604720;
            var18_5[-1955686035 ^ -1955686039] = -1646044529 ^ -1646044474;
            var18_5[-567271421 ^ -567271412] = 693931419 ^ 693931445;
            var18_5[1990861765 ^ 1990861771] = 1084949775 ^ 1084949857;
            var18_5[1103171566 ^ 1103171560] = -1395765062 ^ -1395765028;
            v959 = 1083048172 ^ 1083048089;
            var18_5[-538177540 ^ -538177540] = v959;
            var18_5[1435451858 ^ 1435451866] = 565770654 ^ 565770732;
            v960 = -1503920062 ^ -1503920050;
            var18_5[v960] = -1227404639 ^ -1227404600;
            var18_5[-442726090 ^ -442726095] = -1431977655 ^ -1431977690;
            v961 = -1181454040 ^ -1181454043;
            var18_5[v961] = 2092974059 ^ 2092973956;
            var18_5[1005683549 ^ 1005683548] = -906443856 ^ -906443837;
            var18_5[-48805777 ^ -48805779] = -172309562 ^ -172309597;
            var18_5[866488696 ^ 866488690] = 719810838 ^ 719810935;
            var18_5[-1570033811 ^ -1570033820] = -1724845248 ^ -1724845267;
            var18_5[-57401034 ^ -57401037] = 1282870037 ^ 1282870139;
            var18_5[18734640 ^ 18734643] = 1965393182 ^ 1965393260;
            v962 = new String(var18_5, "UTF-8");
            while (true) {
                if ((v963 = (cfr_temp_68 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-4152199969532907348L ^ 6028563482847001989L)) == 0L ? 0 : (cfr_temp_68 < 0L ? -1 : 1)) == false) continue;
                if (v963 == (1912503316 ^ 1912503317)) break;
                v963 = 40270515 - 256663662;
            }
            v964 = v957.append(v962);
            while (true) {
                if ((v965 = (cfr_temp_69 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-4241855897506040715L ^ 6409726006968120159L)) == 0L ? 0 : (cfr_temp_69 < 0L ? -1 : 1)) == false) continue;
                if (v965 == (-1546782787 ^ -1546782788)) break;
                v965 = -751877834 ^ 116968655;
            }
            var18_5 = new byte[-1962313086 ^ -1962313066];
            v966 = -531409424 ^ -531409416;
            v967 = -1157741277 ^ -1157741216;
            var18_5[v966] = v967;
            var18_5[1958866548 ^ 1958866550] = -125932333 ^ -125932368;
            var18_5[297611863 ^ 297611858] = 412849861 ^ 412849840;
            var18_5[-153387196 ^ -153387178] = 189180479 ^ 189180486;
            var18_5[597710224 ^ 597710208] = 210281178 ^ 210281118;
            var18_5[1057447477 ^ 1057447460] = 1185549072 ^ 1185549169;
            var18_5[-478454957 ^ -478454957] = 1457026244 ^ 1457026282;
            var18_5[0xB8AA8AB ^ 193636517] = 382894519 ^ 382894552;
            var18_5[1132153890 ^ 1132153891] = 802978836 ^ 802978933;
            v968 = -434071996 ^ -434071987;
            var18_5[v968] = -1229641073 ^ -1229640963;
            var18_5[-1108395171 ^ -1108395175] = -335128823 ^ -335128730;
            var18_5[-2088418169 ^ -2088418163] = -232099911 ^ -232099876;
            var18_5[562332896 ^ 562332907] = -1829187760 ^ -1829187791;
            v969 = -1941680285 ^ -1941680274;
            var18_5[v969] = 395095968 ^ 395096009;
            v970 = -582091739 ^ -582091701;
            var18_5[-343739781 ^ -343739779] = v970;
            v971 = 1915252812 ^ 1915252803;
            var18_5[v971] = -86762645 ^ -86762747;
            v972 = 1138312561 ^ 1138312566;
            var18_5[v972] = -2143003639 ^ -2143003523;
            v973 = 871676485 ^ 871676454;
            var18_5[2016842369 ^ 2016842370] = v973;
            v974 = 1114347727 ^ 1114347715;
            var18_5[v974] = -450499279 ^ -450499259;
            var18_5[664166058 ^ 664166073] = 1903815468 ^ 1903815519;
            v975 = v964.append((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e).append(new String(var18_5, "UTF-8")).toString();
            while (true) {
                v976 = -6856164722021649551L ^ 7637515025863049713L;
                cfr_temp_70 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v976;
                v977 = cfr_temp_70 == 0L ? 0 : (cfr_temp_70 < 0L ? -1 : 1);
                if (v977 == false) continue;
                if (v977 == (1972275331 ^ 1972275330)) {
                    if (\u017b\u017c\u0107m.7\u015aCz.getString(v975) != null) {
                        break;
                    }
                    break block573;
                }
                v977 = -1975784082 - -2116915045;
            }
            v978 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
            block531: while (true) {
                switch ((int)v978) {
                    case -896926527: {
                        v978 = (-4163534468537293641L ^ 7053267188754884171L) / (1935250979177389608L ^ -3246490394145866854L);
                        continue block531;
                    }
                    case -495624045: {
                        break block531;
                    }
                }
                break;
            }
            while (true) {
                if ((v979 = (cfr_temp_71 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-3421231834533246615L ^ 763001269036213202L)) == 0L ? 0 : (cfr_temp_71 < 0L ? -1 : 1)) == false) continue;
                if (v979 == (-1228300531 ^ -1228300532)) break;
                v979 = 1752279702 ^ -683839994;
            }
            v980 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
            block533: while (true) {
                switch ((int)v980) {
                    case -573958729: {
                        v980 = (-2303188374870756576L ^ -235511045300348285L) / (7144943432529304156L ^ 718269905838810639L);
                        continue block533;
                    }
                    case -495624045: {
                        break block533;
                    }
                }
                break;
            }
            v981 = new StringBuilder();
            v982 = 2120428759 ^ 2120428743;
            var18_5 = new byte[v982];
            var18_5[938550860 ^ 938550848] = 162229547 ^ 162229570;
            v983 = -2099516763 ^ -2099516760;
            var18_5[v983] = 1658193423 ^ 1658193504;
            v984 = -1729752645 ^ -1729752648;
            v985 = -1516011845 ^ -1516011831;
            var18_5[v984] = v985;
            v986 = 726896296 ^ 726896297;
            var18_5[v986] = -701302253 ^ -701302176;
            var18_5[-217240146 ^ -217240155] = 2072544121 ^ 2072544013;
            var18_5[1906257322 ^ 1906257325] = 1525294478 ^ 1525294561;
            v987 = 641908475 ^ 641908361;
            var18_5[-1537565488 ^ -1537565480] = v987;
            var18_5[-819483451 ^ -819483446] = -1773267488 ^ -1773267506;
            v988 = 1742726425 ^ 1742726428;
            var18_5[v988] = -74035578 ^ -74035480;
            var18_5[2145770852 ^ 2145770854] = 981630579 ^ 981630486;
            var18_5[-1787025012 ^ -1787025016] = -1793689720 ^ -1793689663;
            var18_5[1060375522 ^ 1060375532] = -1176734566 ^ -1176734476;
            var18_5[2003149916 ^ 2003149909] = 1136159457 ^ 1136159372;
            v989 = 207057530 ^ 207057423;
            var18_5[-1145466987 ^ -1145466987] = v989;
            var18_5[389947746 ^ 389947752] = -404218583 ^ -404218552;
            var18_5[922091260 ^ 922091258] = 1890605239 ^ 1890605265;
            v990 = new String(var18_5, "UTF-8");
            1726095924 ^ -912364088;
            v991 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
            if (true) ** GOTO lbl3855
            block534: while (true) {
                v992 = -6727044238842277375L ^ -3245608185349335727L;
                v991 = v993 / v992;
lbl3855:
                // 2 sources

                switch ((int)v991) {
                    case -2020930375: {
                        v993 = -3367553585449431324L >>> "\u0000\u0000".length();
                        continue block534;
                    }
                    case -987336955: {
                        v993 = -628219388206908691L ^ 4742534045746079059L;
                        continue block534;
                    }
                    case -495624045: {
                        break block534;
                    }
                }
                break;
            }
            v994 = v981.append(v990);
            v995 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
            if (true) ** GOTO lbl3876
            block535: while (true) {
                v996 = 8095100456902761396L >>> "\u0000\u0000".length();
                v995 = v997 / v996;
lbl3876:
                // 2 sources

                switch ((int)v995) {
                    case -1753430639: {
                        v997 = -956100628752275179L - 9096849330524386607L;
                        continue block535;
                    }
                    case -495624045: {
                        break block535;
                    }
                    case -114014230: {
                        v997 = -1604773347271168182L ^ 2318486281964221751L;
                        continue block535;
                    }
                }
                break;
            }
            v998 = v994.append((String)CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1ab03ca6e);
            var18_5 = new byte[1316275904 ^ 1316275924];
            var18_5[-159437047 ^ -159437046] = 1642360547 ^ 1642360448;
            v999 = -1266946268 ^ -1266946223;
            var18_5[-1905932919 ^ -1905932916] = v999;
            v1000 = 1322447236 ^ 1322447296;
            var18_5[1085141733 ^ 1085141749] = v1000;
            var18_5[-907806930 ^ -907806942] = 2071701699 ^ 2071701687;
            v1001 = -1439987929 ^ -1439987896;
            var18_5[-1970306122 ^ -1970306126] = v1001;
            v1002 = 1374498502 ^ 1374498502;
            var18_5[v1002] = 1857174680 ^ 1857174710;
            var18_5[-1038134010 ^ -1038134015] = -1742799683 ^ -1742799671;
            var18_5[-1547459442 ^ -1547459456] = 282264329 ^ 282264422;
            v1003 = 1556737257 ^ 1556737252;
            var18_5[v1003] = 1889363017 ^ 1889362976;
            var18_5[-1851364742 ^ -1851364744] = 573918387 ^ 573918416;
            var18_5[-36499329 ^ -36499344] = 1200929190 ^ 1200929224;
            var18_5[1246661042 ^ 1246661049] = -1489161296 ^ -1489161263;
            var18_5[992479283 ^ 992479285] = 1890278813 ^ 1890278899;
            v1004 = -611665759 ^ -611665742;
            var18_5[v1004] = 914910497 ^ 914910546;
            v1005 = -971800341 ^ -971800327;
            var18_5[v1005] = 609858456 ^ 609858529;
            var18_5[-1391603463 ^ -1391603472] = -1021123662 ^ -1021123648;
            var18_5[1336476307 ^ 1336476315] = 682862580 ^ 682862519;
            v1006 = 2086013416 ^ 2086013325;
            var18_5[1124284445 ^ 1124284439] = v1006;
            var18_5[1964152281 ^ 1964152264] = -338177645 ^ -338177550;
            var18_5[1713772117 ^ 1713772116] = -103701580 ^ -103701547;
            v1007 = new String(var18_5, "UTF-8");
            while (true) {
                block605: {
                    if ((v1008 = (cfr_temp_72 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (599751423906429585L - -6105888040364980175L)) == 0L ? 0 : (cfr_temp_72 < 0L ? -1 : 1)) == false) continue;
                    v1009 = -2097439572 - -2097439571;
                    if (v1008 != v1009) break block605;
                    CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa154a4daf0 = \u017b\u017c\u0107m.7\u015aCz.getString(v998.append(v1007).toString());
                    0x12255122 ^ 1649716060;
                    v1010 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                    if (true) ** GOTO lbl3933
                }
                v1008 = 1875505650 ^ -1265387129;
            }
            block537: while (true) {
                v1010 = v1011 / (-4806862723131712285L - 2868844180090664081L);
lbl3933:
                // 2 sources

                switch ((int)v1010) {
                    case -596650457: {
                        v1011 = 6915423933320092202L - 2037541598533503049L;
                        continue block537;
                    }
                    case -495624045: {
                        break block537;
                    }
                    case 929033243: {
                        v1011 = -258883852340772990L ^ 7658533990788594184L;
                        continue block537;
                    }
                    case 2049282509: {
                        v1011 = 3339077148633689758L - 14140130135651107L;
                        continue block537;
                    }
                }
                break;
            }
            v1012 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
            if (true) ** GOTO lbl3951
            block538: while (true) {
                v1012 = v1013 / (-7246362563958971256L ^ 4162301017627516562L);
lbl3951:
                // 2 sources

                switch ((int)v1012) {
                    case -495624045: {
                        break block538;
                    }
                    case 1261138594: {
                        v1013 = 2988679588550436998L ^ -2264364909159016498L;
                        continue block538;
                    }
                }
                break;
            }
            v1014 = new StringBuilder();
            v1015 = 231282890 ^ 231282942;
            var18_5 = new byte[v1015];
            var18_5[336854045 ^ 336854032] = 1715835981 ^ 1715835966;
            var18_5[-1074733463 ^ -1074733441] = 1998064106 ^ 1998064076;
            var18_5[865145629 ^ 865145600] = -570738957 ^ -570739054;
            v1016 = 142037013 ^ 142037021;
            var18_5[v1016] = 346515677 ^ 346515634;
            v1017 = 172415538 ^ 172415540;
            var18_5[v1017] = 255885614 ^ 255885593;
            v1018 = -2009996187 ^ -2009996250;
            var18_5[648519756 ^ 648519781] = v1018;
            var18_5[-26846068 ^ -26846078] = 274732405 ^ 274732289;
            v1019 = -2120475653 ^ -2120475688;
            var18_5[v1019] = 1434072280 ^ 1434072290;
            v1020 = 1119613636 ^ 1119613645;
            var18_5[v1020] = 417522388 ^ 417522362;
            var18_5[-562844415 ^ -562844370] = -786420331 ^ -786420269;
            v1021 = -1708375166 ^ -1708375162;
            v1022 = -1542580294 ^ -1542580326;
            var18_5[v1021] = v1022;
            var18_5[1008147903 ^ 1008147879] = 764704510 ^ 764704452;
            v1023 = -140354702 ^ -140354795;
            var18_5[71651377 ^ 71651370] = v1023;
            var18_5[1031872128 ^ 1031872161] = -751039806 ^ -751039828;
            var18_5[1562668838 ^ 1562668813] = -1713039346 ^ -1713039308;
            var18_5[-2036300497 ^ -2036300509] = 145638752 ^ 145638720;
            v1024 = -1246780980 ^ -1246780942;
            var18_5[-860767307 ^ -860767305] = v1024;
            var18_5[628398028 ^ 628398060] = -1986204425 ^ -1986204526;
            var18_5[1488808243 ^ 1488808214] = 1465010392 ^ 1465010329;
            var18_5[1988176848 ^ 1988176863] = 1401263227 ^ 1401263116;
            v1025 = -1137970990 ^ -1137971002;
            var18_5[v1025] = 1900326954 ^ 1900326980;
            v1026 = -256555429 ^ -256555419;
            var18_5[1022444121 ^ 1022444138] = v1026;
            var18_5[1235177510 ^ 1235177473] = -1963925154 ^ -1963925224;
            var18_5[-1986873253 ^ -1986873278] = -1695991345 ^ -1695991313;
            v1027 = -2941866 ^ -2941867;
            var18_5[v1027] = -1099611212 ^ -1099611254;
            var18_5[-805423890 ^ -805423893] = 1355327245 ^ 1355327275;
            var18_5[-999122216 ^ -999122198] = -1250142673 ^ -1250142615;
            var18_5[2015892575 ^ 2015892545] = 2016667575 ^ 2016667603;
            var18_5[-296247205 ^ -296247183] = -558248873 ^ -558248941;
            var18_5[-1188289289 ^ -1188289301] = 1076952405 ^ 1076952359;
            var18_5[-1311734222 ^ -1311734252] = -698483959 ^ -698483911;
            var18_5[851053665 ^ 851053635] = 1453398883 ^ 1453398807;
            var18_5[560536235 ^ 560536218] = -109303710 ^ -109303772;
            var18_5[-1038469748 ^ -1038469754] = -658682210 ^ -658682134;
            var18_5[1696684371 ^ 1696684407] = -1247192129 ^ -1247192164;
            var18_5[2062131363 ^ 2062131379] = 1237273859 ^ 1237273964;
            var18_5[-2133640458 ^ -2133640463] = 1039084988 ^ 1039085047;
            var18_5[-2039893227 ^ -2039893192] = -983677488 ^ -983677469;
            v1028 = -1575839689 ^ -1575839744;
            var18_5[-1079212639 ^ -1079212655] = v1028;
            v1029 = -1723208210 ^ -1723208254;
            var18_5[v1029] = 2003913711 ^ 2003913676;
            v1030 = -1111348685 ^ -1111348698;
            var18_5[v1030] = -1099202751 ^ -1099202770;
            var18_5[355295424 ^ 355295470] = -1482898571 ^ -1482898640;
            var18_5[603483533 ^ 603483557] = 1102304446 ^ 1102304504;
            var18_5[337684104 ^ 337684114] = -2015227716 ^ -2015227776;
            v1031 = 767900693 ^ 767900775;
            var18_5[1378717249 ^ 1378717264] = v1031;
            v1032 = -1210529028 ^ -1210529133;
            var18_5[1700235283 ^ 1700235264] = v1032;
            var18_5[-1058049975 ^ -1058049962] = -1862443522 ^ -1862443625;
            v1033 = -752683178 ^ -752683171;
            var18_5[v1033] = -934377217 ^ -934377328;
            var18_5[-1989225219 ^ -1989225233] = 1403215090 ^ 1403214984;
            var18_5[-1006915431 ^ -1006915442] = 1643830130 ^ 1643830090;
            var18_5[-368339535 ^ -368339536] = 861016185 ^ 861016129;
            v1034 = -1840131766 ^ -1840131766;
            var18_5[v1034] = 653148367 ^ 653148393;
            v1035 = new String(var18_5, "UTF-8");
            v1036 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
            block539: while (true) {
                switch ((int)v1036) {
                    case -495624045: {
                        break block539;
                    }
                    case 493741374: {
                        v1036 = (-2399851960553516399L - 2474897228483603346L) / (-7648272395001394708L - 4781514227581914072L);
                        continue block539;
                    }
                }
                break;
            }
            v1037 = v1014.append(v1035);
            while (true) {
                block606: {
                    if ((v1038 = (cfr_temp_73 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-8389750644224220889L ^ 9083460772015657852L)) == 0L ? 0 : (cfr_temp_73 < 0L ? -1 : 1)) == false) continue;
                    if (v1038 != (851125999 ^ -851126000)) break block606;
                    v1039 = \u0144U\u0179f.BAaxHy1acFsWt1rb(CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa154a4daf0);
                    v1040 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                    if (true) ** GOTO lbl4059
                }
                v1038 = 62343982 - -1104072974;
            }
            block541: while (true) {
                v1040 = v1041 / (7882692755330155946L ^ -980475697321322627L);
lbl4059:
                // 2 sources

                switch ((int)v1040) {
                    case -495624045: {
                        break block541;
                    }
                    case 1023649369: {
                        v1041 = -8285875449730966260L ^ -1056931508065944641L;
                        continue block541;
                    }
                }
                break;
            }
            v1042 = v1037.append(v1039);
            v1043 = -1466053424 ^ -1466053413;
            var18_5 = new byte[v1043];
            var18_5[1621972645 ^ 1621972642] = 377983229 ^ 377983128;
            v1044 = 628531991 ^ 628532011;
            var18_5[-859712486 ^ -859712486] = v1044;
            var18_5[-859483648 ^ -859483642] = 964851007 ^ 964851030;
            var18_5[-158541359 ^ -158541355] = -403945530 ^ -403945561;
            v1045 = 1346428416 ^ 1346428516;
            var18_5[-195588306 ^ -195588309] = v1045;
            v1046 = -1404398192 ^ -1404398190;
            var18_5[v1046] = 877207518 ^ 877207481;
            v1047 = 58847769 ^ 58847783;
            var18_5[-112904012 ^ -112904002] = v1047;
            var18_5[624710972 ^ 624710975] = 2106799220 ^ 2106799110;
            v1048 = -1901184784 ^ -1901184801;
            var18_5[-154596742 ^ -154596741] = v1048;
            v1049 = -2042772962 ^ -2042772969;
            var18_5[v1049] = -854529498 ^ -854529454;
            var18_5[200449768 ^ 200449760] = -1589688616 ^ -1589688650;
            v1050 = new String(var18_5, "UTF-8");
            while (true) {
                v1051 = 7900754314607572074L ^ -5732533478929791836L;
                cfr_temp_74 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - v1051;
                v1052 = cfr_temp_74 == 0L ? 0 : (cfr_temp_74 < 0L ? -1 : 1);
                if (v1052 == false) continue;
                if (v1052 == (1014108270 ^ 1014108271)) break;
                v1052 = 1187800269 - -590318765;
            }
            v1053 = v1042.append(v1050);
            while (true) {
                if ((v1054 = (cfr_temp_75 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (4700678747198811202L ^ -7155679344107081477L)) == 0L ? 0 : (cfr_temp_75 < 0L ? -1 : 1)) == false) continue;
                if (v1054 == (-556944599 ^ -556944600)) break;
                v1054 = 378331011 ^ -171119136;
            }
            v1055 = 0QZ8.1d9kfLKTTP4TVZYm((String)v1053.toString());
            while (true) {
                if ((v1056 = (cfr_temp_76 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (415020195795325872L ^ -2072290929139426404L)) == 0L ? 0 : (cfr_temp_76 < 0L ? -1 : 1)) == false) continue;
                if (v1056 == (307656743 ^ -307656744)) {
                    CRACKME_ba6e8403_ff92_4b51_a94e_a3d189299aa1936ebd88.sendMessage(v1055);
                    break;
                }
                v1056 = 858628213 ^ -87825388;
            }
        }
        v1057 = -1461207931 ^ 1729637668;
        return (boolean)(1885056063 ^ 1885056063);
    }

    private static /* synthetic */ String I8joxuwu29-CQxkK(String string) {
        if (CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d == (0x690EC8F3B206E761L ^ 0xC4FC8D30DCBD2197L)) {
            if ((0x79FAD5D6 ^ 0x1C6CCEF1 ^ (-4912073544107056379L == -4912073544107056378L ? -1101230317 : -1990279938 - 157203711)) != 0) {
                int cfr_ignored_1 = 0x87439B1F ^ 0x87439B1E;
            }
        } else {
            int cfr_ignored_2 = 0xDC727270 ^ 0xDC727270;
        }
        int cfr_ignored_3 = 0xFA029F29 ^ 0xE253F803;
        byte[] byArray = new byte[0x9A62E846 ^ 0x9A62E845];
        byArray[6356771275966996812L == 6356771275966996813L ? -719797059 : 0x803920DF ^ 0x803920DF] = 0x66D9C4FA ^ 0x66D9C484;
        byArray[0x45D1CC4D ^ 0x45D1CC4F] = 0xC877D9AB ^ 0xC877D9D5;
        byArray[0x1F048D3A ^ 0x1F048D3B] = 0xBD653A7F ^ 0xBD653A4C;
        String string2 = new String(byArray, "UTF-8");
        byArray = new byte[0xE5BB9447 ^ 0xE5BB9446];
        byArray[0xC2D1B2CA ^ 0xC2D1B2CA] = 0x245D80C ^ 0x245D822;
        String string3 = new String(byArray, "UTF-8");
        while (true) {
            long l;
            long l2;
            if ((l2 = (l = CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (3438298838828943452L - 5313307385723590278L)) == 0L ? 0 : (l < 0L ? -1 : 1)) == false) {
                continue;
            }
            if (l2 == 299253253 - 299253254) {
                String CRACKME_68db3539_7fd9_4150_89a0_044651ed7341647af09e;
                return CRACKME_68db3539_7fd9_4150_89a0_044651ed7341647af09e.replace(string2, string3);
            }
            l2 = 0xF23AB2D2 ^ 0xA4F6B192;
        }
    }

    /*
     * Handled impossible loop by adding 'first' condition
     * WARNING - void declaration
     */
    private static /* synthetic */ boolean YN0A3wPuKrD1xhei(String[] stringArray, String string) {
        String[] CRACKME_7070c7fc_ca11_427d_8b7f_d1a56eca795c231ecb15;
        void CRACKME_7070c7fc_ca11_427d_8b7f_d1a56eca795cea9d5d9e;
        if (CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d == 4661179950646847164L >>> "\u0000\u0000".length()) {
            int n = 0xD474FA11 ^ 0xAB8B05EE;
            if ((0x58EA3743 ^ 0xF99A08A4 ^ n) != 0) {
                int cfr_ignored_3 = 0xC12C8932 ^ 0xC12C8933;
            }
        } else {
            int n = 0xA8D188DA ^ 0xA8D188DA;
        }
        long l = CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
        boolean bl = true;
        block5: while (true) {
            long l2;
            if (!bl || (bl = false) || !true) {
                l = l2 / (0x91F349B3001C99D8L ^ 0xE2E930AFE4B1BB19L);
            }
            switch ((int)l) {
                case -1686407092: {
                    l2 = -7304188420585940567L - 7020624411567285948L;
                    continue block5;
                }
                case -495624045: {
                    break block5;
                }
                case 1474239485: {
                    l2 = -7195329737585694923L - -1903050290277317441L;
                    continue block5;
                }
            }
            break;
        }
        String string2 = CRACKME_7070c7fc_ca11_427d_8b7f_d1a56eca795cea9d5d9e.toLowerCase();
        String string3 = CRACKME_7070c7fc_ca11_427d_8b7f_d1a56eca795c231ecb15[0x36AAC5F6 ^ 0x36AAC5F6];
        int cfr_ignored_4 = 0x25F7595E ^ 0x49CEA3CF;
        while (true) {
            long l3;
            long l4;
            if ((l4 = (l3 = CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (0xE6C2AE1E16B5443AL ^ 0x5A4BDA7AD41DD378L)) == 0L ? 0 : (l3 < 0L ? -1 : 1)) == false) continue;
            int n = 0xB7E3830E ^ 0xB7E3830F;
            if (l4 == n) break;
            l4 = 0xD8303633 ^ 0x7826416E;
        }
        String string4 = string3.toLowerCase();
        while (true) {
            long l5;
            long l6;
            if ((l6 = (l5 = CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (732000685631267638L - 598496496685467057L)) == 0L ? 0 : (l5 < 0L ? -1 : 1)) == false) continue;
            if (l6 == 1474057291 - 1474057292) {
                return string2.startsWith(string4);
            }
            l6 = 0x4C45A2BF ^ 0xD167D01F;
        }
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender var1_1, @NotNull Command var2_2, @NotNull String var3_3, @NotNull String[] var4_4) {
        block44: {
            block43: {
                if (\u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d == (-4234547177967030108L == -4234547177967030107L ? 5232282043097079592L : -5973543835288452256L >>> "\u0000\u0000".length())) {
                    if ((1318682848 ^ 675989414 ^ -1349683272 - 797800377) != 0) {
                        -628891911 ^ -628891912;
                    }
                } else {
                    v0 = -2761999076046891432L == -2761999076046891431L ? -1575536309 : 1190136961 ^ 1190136961;
                }
                if (((void)CRACKME_a8d605c3_feea_4121_9abe_97d0ee47f04bb75f533c).length == (7572627187436051655L == 7572627187436051656L ? 1070140998 : 876647042 ^ 876647043)) break block43;
                v1 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                if (true) ** GOTO lbl130
            }
            -1013905528 ^ 1175573111;
            var7_5 = new byte[-924777414 ^ -924777419];
            var7_5[1425163821 ^ 1425163814] = -1646743950 ^ -1646744058;
            var7_5[-1610128314 ^ -1610128308] = -1506819896 ^ -1506819927;
            var7_5[930529778 ^ 930529781] = -325437242 ^ -325437271;
            var7_5[-2139216185 ^ -2139216183] = 1529740970 ^ 1529740996;
            var7_5[249871437 ^ 249871428] = -3073363716392700025L == -3073363716392700024L ? 2118244481 : 812255208 ^ 812255109;
            var7_5[8417599110243107149L == 8417599110243107150L ? -1547679322 : 257244351 ^ 257244343] = -921990282 ^ -921990396;
            var7_5[-1785916721 ^ -1785916726] = 6216560389676395148L == 6216560389676395149L ? 1165832388 : -841918788 ^ -841918766;
            var7_5[-1399019996 ^ -1399020000] = -6188611428977417290L == -6188611428977417289L ? 912635758 : 1594847309 ^ 1594847236;
            var7_5[1846996536 ^ 1846996539] = -1724875271 ^ -1724875381;
            var7_5[933842003 ^ 933842014] = -2755146233292454458L == -2755146233292454457L ? 472254362 : 380171354 ^ 380171317;
            var7_5[-2061015929 ^ -2061015929] = 385245151 ^ 385245098;
            var7_5[8521820061236403892L == 8521820061236403893L ? 2112766942 : -174445634 ^ -174445636] = -4245807540886567680L == -4245807540886567679L ? 2026835860 : 1521417240 ^ 1521417341;
            var7_5[606321726 ^ 606321727] = -1864033176 ^ -1864033253;
            var7_5[5739043626407990502L == 5739043626407990503L ? 1828781317 : -763672967 ^ -763672971] = 204704047 ^ 204704070;
            var7_5[2123244970 ^ 2123244972] = 1384889118 ^ 1384889208;
            v2 = new String(var7_5, "UTF-8");
            v3 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
            block28: while (true) {
                switch ((int)v3) {
                    case -2017170191: {
                        v3 = (-6046046502483050753L ^ 982463911368318888L) / (1315257661880281439L - 5388897351878185388L);
                        continue block28;
                    }
                    case -495624045: {
                        break block28;
                    }
                }
                break;
            }
            var5_6 = \u017b\u017c\u0107m.7\u015aCz.getConfigurationSection(v2);
            if (CRACKME_a8d605c3_feea_4121_9abe_97d0ee47f04b454e374e == null) break block44;
            v4 = -4385242810923318115L == -4385242810923318114L ? -1131433543 : 474189764 ^ 474189764;
            v5 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
            if (true) ** GOTO lbl66
        }
        v6 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
        block29: while (true) {
            switch ((int)v6) {
                case -495624045: {
                    break block29;
                }
                case 1265795131: {
                    v6 = (1853694139755625552L ^ -3361241169788054318L) / (3192046555877485505L ^ -8258315382628742766L);
                    continue block29;
                }
            }
            break;
        }
        1776275633 ^ -1469887924;
        return new ArrayList<String>();
        block30: while (true) {
            v5 = v7 / (-6385200345643882008L ^ 9124220002496049737L);
lbl66:
            // 2 sources

            switch ((int)v5) {
                case -947721578: {
                    v7 = 3852958036634722255L ^ -253374521704728075L;
                    continue block30;
                }
                case -495624045: {
                    break block30;
                }
                case 652145097: {
                    v7 = -3110684362371145269L ^ 4886900852475987956L;
                    continue block30;
                }
            }
            break;
        }
        v8 = CRACKME_a8d605c3_feea_4121_9abe_97d0ee47f04b454e374e.getKeys((boolean)v4).stream();
        while (true) {
            if ((v9 = (cfr_temp_0 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-4749111909132248052L - 3402813106560756459L)) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) continue;
            if (v9 == (-1943899437 ^ -1943899438)) break;
            v9 = 1003697927 ^ 1179065683;
        }
        v10 = (Function<String, String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, I8joxuwu29-CQxkK(java.lang.String ), (Ljava/lang/String;)Ljava/lang/String;)();
        while (true) {
            if ((v11 = (cfr_temp_1 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-6034895669050766183L == -6034895669050766182L ? 2656970947922263905L : -3022072456789348722L - 2865458029271667817L)) == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1)) == false) continue;
            if (v11 == (1894061562 ^ 1894061563)) break;
            v11 = 832481780 ^ -825589937;
        }
        v12 = v8.map(v10);
        while (true) {
            block45: {
                if ((v13 = (cfr_temp_2 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d - (-3145555355949524105L ^ -5971364320636868612L)) == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1)) == false) continue;
                if (v13 != (-207588410 ^ 207588409)) break block45;
                v14 = v12.filter((Predicate<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, YN0A3wPuKrD1xhei(java.lang.String[] java.lang.String ), (Ljava/lang/String;)Z)((String[])CRACKME_a8d605c3_feea_4121_9abe_97d0ee47f04bb75f533c));
                1143725469 ^ -2045129660;
                v15 = Collectors.toList();
                v16 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
                if (true) ** GOTO lbl107
            }
            v13 = -1926951589 - -2050381354;
        }
        block34: while (true) {
            v16 = v17 / (-6858566669471612017L ^ -2307404415602660038L);
lbl107:
            // 2 sources

            switch ((int)v16) {
                case -1877814522: {
                    if (3377211972306721610L == 3377211972306721611L) {
                        v17 = -423088396228140868L;
                        continue block34;
                    }
                    v17 = 6771426779560149392L >>> "\u0000\u0000".length();
                    continue block34;
                }
                case -495624045: {
                    break block34;
                }
                case 357691174: {
                    v17 = -7270559693538005486L - -5177974965627266036L;
                    continue block34;
                }
                case 1350184663: {
                    if (-2077371599038025065L == -2077371599038025064L) {
                        v17 = -1000351143674250369L;
                        continue block34;
                    }
                    v17 = -250322424405852760L ^ 6535425661424217801L;
                    continue block34;
                }
            }
            break;
        }
        return v14.collect(v15);
        block35: while (true) {
            v1 = v18 / (-7980708528828689560L >>> "\u0000\u0000".length());
lbl130:
            // 2 sources

            switch ((int)v1) {
                case -495624045: {
                    break block35;
                }
                case -111415432: {
                    if (-233434051900314930L == -233434051900314929L) {
                        v18 = -3040404142987673077L;
                        continue block35;
                    }
                    v18 = 5603415868930963202L - 6711478995584376527L;
                    continue block35;
                }
            }
            break;
        }
        v19 = \u0144U\u0179f.CRACKME_43f13ca8_ac08_427a_b8c4_d73f346d427d_bd98a34d;
        if (true) ** GOTO lbl144
        block36: while (true) {
            v19 = v20 / (3080553978122318461L ^ -4707713930687444161L);
lbl144:
            // 2 sources

            switch ((int)v19) {
                case -1273830488: {
                    v20 = 4658950871288552199L - -2875060825373709037L;
                    continue block36;
                }
                case -495624045: {
                    break block36;
                }
                case 219785938: {
                    v20 = 8332604917303118321L - -461799262515937761L;
                    continue block36;
                }
            }
            break;
        }
        return new ArrayList<String>();
    }
}
