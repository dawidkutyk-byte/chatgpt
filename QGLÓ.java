/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.scheduler.BukkitRunnable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
 */
import org.bukkit.scheduler.BukkitRunnable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

class QGL\u00d3
extends BukkitRunnable {
    final /* synthetic */ int JnC\u0141;
    final /* synthetic */ int[] thW\u017c;
    final /* synthetic */ BossBar K6S0;
    static long CRACKME_89de226f_a567_49f8_a032_66b44921c17f_b5de2a37 = -1975361245953933785L;
    final /* synthetic */ String q\u0106WV;
    private static String[] CRACKME_BITCH = new String[20];
    final /* synthetic */ LegacyComponentSerializer h\u0179bp;
    final /* synthetic */ int[] sHFX;
    final /* synthetic */ Audience V1Zg;

    static {
        QGL\u00d3.CRACKME_BITCH[0] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u287f\u281f\u281b\u281b\u281b\u280b\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2819\u281b\u281b\u281b\u283f\u283b\u283f\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[1] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u287f\u280b\u2800\u2800\u2800\u2800\u2800\u2840\u2820\u2824\u2812\u2882\u28c9\u28c9\u28c9\u28d1\u28d2\u28d2\u2812\u2812\u2812\u2812\u2812\u2812\u2812\u2800\u2800\u2810\u2812\u281a\u283b\u283f\u283f\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[2] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u280f\u2800\u2800\u2800\u2800\u2860\u2814\u2809\u28c0\u2814\u2812\u2809\u28c0\u28c0\u2800\u2800\u2800\u28c0\u2840\u2808\u2809\u2811\u2812\u2812\u2812\u2812\u2808\u2809\u2809\u2809\u2801\u2802\u2800\u2808\u2819\u28bf\u28ff\u28ff\u28ff\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[3] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u2807\u2800\u2800\u2800\u2814\u2801\u2820\u2816\u2821\u2814\u280a\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2810\u2844\u2800\u2800\u2800\u2800\u2800\u2800\u2844\u2800\u2800\u2800\u2800\u2809\u2832\u2884\u2800\u2800\u2800\u2808\u28ff\u28ff\u28ff\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[4] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u280b\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u280a\u2800\u2880\u28c0\u28e4\u28e4\u28e4\u28c0\u2800\u2800\u2800\u28b8\u2800\u2800\u2800\u2800\u2800\u281c\u2800\u2800\u2800\u2800\u28c0\u2840\u2800\u2808\u2803\u2800\u2800\u2800\u2838\u28ff\u28ff\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[5] = "\u28ff\u28ff\u28ff\u28ff\u287f\u2825\u2810\u2802\u2800\u2800\u2800\u2800\u2844\u2800\u2830\u28ba\u28ff\u28ff\u28ff\u28ff\u28ff\u28df\u2800\u2808\u2810\u28a4\u2800\u2800\u2800\u2800\u2800\u2800\u2880\u28e0\u28f6\u28fe\u28ef\u2800\u2800\u2809\u2802\u2800\u2820\u2824\u2884\u28c0\u2819\u28bf\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[6] = "\u28ff\u287f\u280b\u2821\u2810\u2808\u28c9\u282d\u2824\u2824\u2884\u2840\u2808\u2800\u2808\u2801\u2809\u2801\u2860\u2800\u2800\u2800\u2809\u2810\u2820\u2814\u2800\u2800\u2800\u2800\u2800\u2832\u28ff\u283f\u281b\u281b\u2813\u2812\u2802\u2800\u2800\u2800\u2800\u2800\u2800\u2820\u2849\u28a2\u2819\u28ff";
        QGL\u00d3.CRACKME_BITCH[7] = "\u28ff\u2800\u2880\u2801\u2800\u280a\u2800\u2800\u2800\u2800\u2800\u2808\u2801\u2812\u2802\u2800\u2812\u280a\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2847\u2800\u2800\u2800\u2800\u2800\u2880\u28c0\u2860\u2812\u2812\u2802\u2800\u2808\u2800\u2847\u28ff";
        QGL\u00d3.CRACKME_BITCH[8] = "\u28ff\u2800\u28b8\u2800\u2800\u2800\u2880\u28c0\u2860\u280b\u2813\u2824\u28c0\u2840\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2804\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2808\u2822\u2824\u2840\u2800\u2800\u2800\u2800\u2800\u2800\u28a0\u2800\u2800\u2800\u2860\u2800\u2847\u28ff";
        QGL\u00d3.CRACKME_BITCH[9] = "\u28ff\u2840\u2818\u2800\u2800\u2800\u2800\u2800\u2818\u2844\u2800\u2800\u2800\u2808\u2811\u2866\u2884\u28c0\u2800\u2800\u2810\u2812\u2801\u28b8\u2800\u2800\u2820\u2812\u2804\u2800\u2800\u2800\u2800\u2800\u2880\u2807\u2800\u28c0\u2840\u2800\u2800\u2880\u28be\u2846\u2800\u2808\u2840\u280e\u28f8\u28ff";
        QGL\u00d3.CRACKME_BITCH[10] = "\u28ff\u28ff\u28c4\u2848\u2822\u2800\u2800\u2800\u2800\u2818\u28f6\u28c4\u2840\u2800\u2800\u2847\u2800\u2800\u2808\u2809\u2812\u2822\u2864\u28c0\u2840\u2800\u2800\u2800\u2800\u2800\u2810\u2826\u2824\u2812\u2801\u2800\u2800\u2800\u2800\u28c0\u28b4\u2801\u2800\u28b7\u2800\u2800\u2800\u28b0\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[11] = "\u28ff\u28ff\u28ff\u28c7\u2802\u2800\u2800\u2800\u2800\u2800\u2800\u2808\u2882\u2800\u2808\u2839\u2864\u28c0\u2800\u2800\u2800\u2800\u2800\u2847\u2800\u2800\u2809\u2809\u2809\u28b1\u2812\u2812\u2812\u2812\u2896\u2812\u2812\u2802\u2819\u280f\u2800\u2818\u2840\u2800\u28b8\u2800\u2800\u2800\u28ff\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[12] = "\u28ff\u28ff\u28ff\u28ff\u28e7\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2811\u2804\u2830\u2800\u2800\u2801\u2810\u2832\u28e4\u28f4\u28c4\u2840\u2800\u2800\u2800\u2800\u28b8\u2800\u2800\u2800\u2800\u28b8\u2800\u2800\u2800\u2800\u28a0\u2800\u28e0\u28f7\u28f6\u28ff\u2800\u2800\u28b0\u28ff\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[13] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28e7\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2801\u2880\u2800\u2800\u2800\u2800\u2800\u2859\u280b\u2819\u2813\u2832\u28a4\u28e4\u28e4\u28e4\u28fe\u28e6\u28e4\u28e4\u28e4\u28fe\u28e6\u28e4\u28e4\u28f6\u28ff\u28ff\u28ff\u28ff\u285f\u28b9\u2800\u2800\u28b8\u28ff\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[14] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28e7\u2840\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2811\u2800\u2884\u2800\u2870\u2801\u2800\u2800\u2800\u2800\u2800\u2808\u2809\u2801\u2808\u2809\u283b\u280b\u2809\u281b\u289b\u2809\u2809\u28b9\u2801\u2880\u2887\u280e\u2800\u2800\u28b8\u28ff\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[15] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28e6\u28c0\u2808\u2822\u2884\u2849\u2802\u2804\u2840\u2800\u2808\u2812\u2822\u2804\u2800\u2880\u28c0\u28c0\u28f0\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2840\u2800\u2880\u28ce\u2800\u283c\u280a\u2800\u2800\u2800\u2818\u28ff\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[16] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28f7\u28c4\u2840\u2809\u2822\u2884\u2848\u2811\u2822\u2884\u2840\u2800\u2800\u2800\u2800\u2800\u2800\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2801\u2800\u2800\u2880\u2800\u2800\u2800\u2800\u2800\u28bb\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[17] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28f7\u28e6\u28c0\u2848\u2811\u2822\u2884\u2840\u2808\u2811\u2812\u2824\u2804\u28c0\u28c0\u2800\u2809\u2809\u2809\u2809\u2800\u2800\u2800\u28c0\u2840\u2824\u2802\u2801\u2800\u2880\u2806\u2800\u2800\u28b8\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[18] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28f7\u28e6\u28c4\u2840\u2801\u2809\u2812\u2802\u2824\u2824\u28c0\u28c0\u28c9\u2849\u2809\u2809\u2809\u2809\u2809\u2801\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u28f8\u28ff\u28ff";
        QGL\u00d3.CRACKME_BITCH[19] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28f6\u28f6\u28f6\u28f6\u28e4\u28e4\u28e4\u28c0\u28e4\u28e4\u28e4\u28f6\u28fe\u28ff\u28ff\u28ff\u28ff\u28ff";
    }

    /*
     * Exception decompiling
     */
    public void run() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 24[SWITCH]
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
     * Unable to fully structure code
     */
    QGL\u00d3(int[] var1_1, int[] var2_2, BossBar var3_3, Audience var4_4, int var5_5, LegacyComponentSerializer var6_6, String var7_7) {
        if (QGL\u00d3.CRACKME_89de226f_a567_49f8_a032_66b44921c17f_b5de2a37 == (-8669602385412336613L ^ 1064997943248549514L)) {
            -1805505764 ^ -1805505763;
        } else {
            399138054 ^ 399138054;
        }
        CRACKME_05a1852b_6855_4908_8e90_ce5a4f3a8c905e6ef6d9.sHFX = var1_1;
        v0 = QGL\u00d3.CRACKME_89de226f_a567_49f8_a032_66b44921c17f_b5de2a37;
        if (true) ** GOTO lbl19
        block14: while (true) {
            v0 = v1 / (-7888693889795254765L - -3883726378017771382L);
lbl19:
            // 2 sources

            switch ((int)v0) {
                case -1479033305: {
                    break block14;
                }
                case -1391576904: {
                    v1 = 7174935581867649472L ^ -1341676413259074590L;
                    continue block14;
                }
                case 937495715: {
                    v1 = -933032533786903696L - -1519627384865481069L;
                    continue block14;
                }
                case 1988444475: {
                    v1 = 3295947988112096391L - -6672424480853077859L;
                    continue block14;
                }
            }
            break;
        }
        CRACKME_05a1852b_6855_4908_8e90_ce5a4f3a8c905e6ef6d9.thW\u017c = var2_2;
        v2 = QGL\u00d3.CRACKME_89de226f_a567_49f8_a032_66b44921c17f_b5de2a37;
        block15: while (true) {
            switch ((int)v2) {
                case -1479033305: {
                    break block15;
                }
                case 1200144228: {
                    v2 = (-3136423490509592336L - -3561690002482135664L) / (-891284738490805159L - -7085723310913631524L);
                    continue block15;
                }
            }
            break;
        }
        CRACKME_05a1852b_6855_4908_8e90_ce5a4f3a8c905e6ef6d9.K6S0 = var3_3;
        CRACKME_05a1852b_6855_4908_8e90_ce5a4f3a8c905e6ef6d9.V1Zg = var4_4;
        while (true) {
            block21: {
                if ((v3 = (cfr_temp_0 = QGL\u00d3.CRACKME_89de226f_a567_49f8_a032_66b44921c17f_b5de2a37 - (-4122961289284919248L ^ -1806914655208694010L)) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1)) == false) {
                    continue;
                }
                if (v3 != (-1019403173 ^ 1019403172)) break block21;
                CRACKME_05a1852b_6855_4908_8e90_ce5a4f3a8c905e6ef6d9.JnC\u0141 = var5_5;
                CRACKME_05a1852b_6855_4908_8e90_ce5a4f3a8c905e6ef6d9.h\u0179bp = var6_6;
                v4 = 1561299054 ^ -631639719;
                CRACKME_05a1852b_6855_4908_8e90_ce5a4f3a8c905e6ef6d9.q\u0106WV = var7_7;
                v5 = QGL\u00d3.CRACKME_89de226f_a567_49f8_a032_66b44921c17f_b5de2a37;
                if (true) ** GOTO lbl69
            }
            v3 = -699129066 - -405387236;
        }
        block17: while (true) {
            v5 = (4765001450432419032L >>> "\u0000\u0000".length()) / v6;
lbl69:
            // 2 sources

            switch ((int)v5) {
                case -1479033305: {
                    break block17;
                }
                case 796650417: {
                    v6 = 5799646511479182505L ^ -5525180571803613103L;
                    continue block17;
                }
            }
            break;
        }
        super();
    }
}
