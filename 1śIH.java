/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  C327
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  zNb\u015b
 */
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

class 1\u015bIH
extends BukkitRunnable {
    final /* synthetic */ Player Q\u017c\u0143\u017c;
    final /* synthetic */ C327 I\u00f3CS;
    static long CRACKME_868ab6c7_6429_4eca_8f87_264654d8da52_1b003ad7 = -939802055771981568L;
    private static String[] CRACKME_BITCH = new String[20];

    static {
        1\u015bIH.CRACKME_BITCH[0] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u287f\u281f\u281b\u281b\u281b\u280b\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2819\u281b\u281b\u281b\u283f\u283b\u283f\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[1] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u287f\u280b\u2800\u2800\u2800\u2800\u2800\u2840\u2820\u2824\u2812\u2882\u28c9\u28c9\u28c9\u28d1\u28d2\u28d2\u2812\u2812\u2812\u2812\u2812\u2812\u2812\u2800\u2800\u2810\u2812\u281a\u283b\u283f\u283f\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[2] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u280f\u2800\u2800\u2800\u2800\u2860\u2814\u2809\u28c0\u2814\u2812\u2809\u28c0\u28c0\u2800\u2800\u2800\u28c0\u2840\u2808\u2809\u2811\u2812\u2812\u2812\u2812\u2808\u2809\u2809\u2809\u2801\u2802\u2800\u2808\u2819\u28bf\u28ff\u28ff\u28ff\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[3] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u2807\u2800\u2800\u2800\u2814\u2801\u2820\u2816\u2821\u2814\u280a\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2810\u2844\u2800\u2800\u2800\u2800\u2800\u2800\u2844\u2800\u2800\u2800\u2800\u2809\u2832\u2884\u2800\u2800\u2800\u2808\u28ff\u28ff\u28ff\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[4] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u280b\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u280a\u2800\u2880\u28c0\u28e4\u28e4\u28e4\u28c0\u2800\u2800\u2800\u28b8\u2800\u2800\u2800\u2800\u2800\u281c\u2800\u2800\u2800\u2800\u28c0\u2840\u2800\u2808\u2803\u2800\u2800\u2800\u2838\u28ff\u28ff\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[5] = "\u28ff\u28ff\u28ff\u28ff\u287f\u2825\u2810\u2802\u2800\u2800\u2800\u2800\u2844\u2800\u2830\u28ba\u28ff\u28ff\u28ff\u28ff\u28ff\u28df\u2800\u2808\u2810\u28a4\u2800\u2800\u2800\u2800\u2800\u2800\u2880\u28e0\u28f6\u28fe\u28ef\u2800\u2800\u2809\u2802\u2800\u2820\u2824\u2884\u28c0\u2819\u28bf\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[6] = "\u28ff\u287f\u280b\u2821\u2810\u2808\u28c9\u282d\u2824\u2824\u2884\u2840\u2808\u2800\u2808\u2801\u2809\u2801\u2860\u2800\u2800\u2800\u2809\u2810\u2820\u2814\u2800\u2800\u2800\u2800\u2800\u2832\u28ff\u283f\u281b\u281b\u2813\u2812\u2802\u2800\u2800\u2800\u2800\u2800\u2800\u2820\u2849\u28a2\u2819\u28ff";
        1\u015bIH.CRACKME_BITCH[7] = "\u28ff\u2800\u2880\u2801\u2800\u280a\u2800\u2800\u2800\u2800\u2800\u2808\u2801\u2812\u2802\u2800\u2812\u280a\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2847\u2800\u2800\u2800\u2800\u2800\u2880\u28c0\u2860\u2812\u2812\u2802\u2800\u2808\u2800\u2847\u28ff";
        1\u015bIH.CRACKME_BITCH[8] = "\u28ff\u2800\u28b8\u2800\u2800\u2800\u2880\u28c0\u2860\u280b\u2813\u2824\u28c0\u2840\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2804\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2808\u2822\u2824\u2840\u2800\u2800\u2800\u2800\u2800\u2800\u28a0\u2800\u2800\u2800\u2860\u2800\u2847\u28ff";
        1\u015bIH.CRACKME_BITCH[9] = "\u28ff\u2840\u2818\u2800\u2800\u2800\u2800\u2800\u2818\u2844\u2800\u2800\u2800\u2808\u2811\u2866\u2884\u28c0\u2800\u2800\u2810\u2812\u2801\u28b8\u2800\u2800\u2820\u2812\u2804\u2800\u2800\u2800\u2800\u2800\u2880\u2807\u2800\u28c0\u2840\u2800\u2800\u2880\u28be\u2846\u2800\u2808\u2840\u280e\u28f8\u28ff";
        1\u015bIH.CRACKME_BITCH[10] = "\u28ff\u28ff\u28c4\u2848\u2822\u2800\u2800\u2800\u2800\u2818\u28f6\u28c4\u2840\u2800\u2800\u2847\u2800\u2800\u2808\u2809\u2812\u2822\u2864\u28c0\u2840\u2800\u2800\u2800\u2800\u2800\u2810\u2826\u2824\u2812\u2801\u2800\u2800\u2800\u2800\u28c0\u28b4\u2801\u2800\u28b7\u2800\u2800\u2800\u28b0\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[11] = "\u28ff\u28ff\u28ff\u28c7\u2802\u2800\u2800\u2800\u2800\u2800\u2800\u2808\u2882\u2800\u2808\u2839\u2864\u28c0\u2800\u2800\u2800\u2800\u2800\u2847\u2800\u2800\u2809\u2809\u2809\u28b1\u2812\u2812\u2812\u2812\u2896\u2812\u2812\u2802\u2819\u280f\u2800\u2818\u2840\u2800\u28b8\u2800\u2800\u2800\u28ff\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[12] = "\u28ff\u28ff\u28ff\u28ff\u28e7\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2811\u2804\u2830\u2800\u2800\u2801\u2810\u2832\u28e4\u28f4\u28c4\u2840\u2800\u2800\u2800\u2800\u28b8\u2800\u2800\u2800\u2800\u28b8\u2800\u2800\u2800\u2800\u28a0\u2800\u28e0\u28f7\u28f6\u28ff\u2800\u2800\u28b0\u28ff\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[13] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28e7\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2801\u2880\u2800\u2800\u2800\u2800\u2800\u2859\u280b\u2819\u2813\u2832\u28a4\u28e4\u28e4\u28e4\u28fe\u28e6\u28e4\u28e4\u28e4\u28fe\u28e6\u28e4\u28e4\u28f6\u28ff\u28ff\u28ff\u28ff\u285f\u28b9\u2800\u2800\u28b8\u28ff\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[14] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28e7\u2840\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2811\u2800\u2884\u2800\u2870\u2801\u2800\u2800\u2800\u2800\u2800\u2808\u2809\u2801\u2808\u2809\u283b\u280b\u2809\u281b\u289b\u2809\u2809\u28b9\u2801\u2880\u2887\u280e\u2800\u2800\u28b8\u28ff\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[15] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28e6\u28c0\u2808\u2822\u2884\u2849\u2802\u2804\u2840\u2800\u2808\u2812\u2822\u2804\u2800\u2880\u28c0\u28c0\u28f0\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2840\u2800\u2880\u28ce\u2800\u283c\u280a\u2800\u2800\u2800\u2818\u28ff\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[16] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28f7\u28c4\u2840\u2809\u2822\u2884\u2848\u2811\u2822\u2884\u2840\u2800\u2800\u2800\u2800\u2800\u2800\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2809\u2801\u2800\u2800\u2880\u2800\u2800\u2800\u2800\u2800\u28bb\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[17] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28f7\u28e6\u28c0\u2848\u2811\u2822\u2884\u2840\u2808\u2811\u2812\u2824\u2804\u28c0\u28c0\u2800\u2809\u2809\u2809\u2809\u2800\u2800\u2800\u28c0\u2840\u2824\u2802\u2801\u2800\u2880\u2806\u2800\u2800\u28b8\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[18] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28f7\u28e6\u28c4\u2840\u2801\u2809\u2812\u2802\u2824\u2824\u28c0\u28c0\u28c9\u2849\u2809\u2809\u2809\u2809\u2809\u2801\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u2800\u28f8\u28ff\u28ff";
        1\u015bIH.CRACKME_BITCH[19] = "\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28f6\u28f6\u28f6\u28f6\u28e4\u28e4\u28e4\u28c0\u28e4\u28e4\u28e4\u28f6\u28fe\u28ff\u28ff\u28ff\u28ff\u28ff";
    }

    /*
     * Handled impossible loop by adding 'first' condition
     */
    public void run() {
        1\u015bIH CRACKME_bc4cad81_3783_48d5_bf1c_66f295d8688778638b7c;
        long l = 0x43EB717FEC596327L ^ 0xF04574F9A11CE564L;
        if (CRACKME_868ab6c7_6429_4eca_8f87_264654d8da52_1b003ad7 == l) {
            int n = 0x84633DDA ^ 0x84633DDB;
        } else {
            int cfr_ignored_1 = 0x9A1AE097 ^ 0x9A1AE097;
        }
        Player player = CRACKME_bc4cad81_3783_48d5_bf1c_66f295d8688778638b7c.Q\u017c\u0143\u017c;
        int n = 0xC9C1F4F3 ^ 0xC9C1F4FE;
        byte[] byArray = new byte[n];
        byArray[0x19D32E23 ^ 0x19D32E27] = 0xF3852014 ^ 0xF385207D;
        byArray[0x4876E084 ^ 0x4876E08C] = 0xE92D5455 ^ 0xE92D5426;
        int n2 = 0x4E433B4D ^ 0x4E433B4C;
        byArray[n2] = 0x6784B060 ^ 0x6784B00C;
        byArray[0x9A5D5455 ^ 0x9A5D545F] = 0x7A81BDF8 ^ 0x7A81BD8D;
        int n3 = 0x286A2DAE ^ 0x286A2DAC;
        byArray[n3] = 0xAA7E7E59 ^ 0xAA7E7E38;
        byArray[0x51FD2C4D ^ 0x51FD2C4A] = 0xB32978E1 ^ 0xB32978BE;
        byArray[0x713B7675 ^ 0x713B7679] = 0xB8A85543 ^ 0xB8A85527;
        byArray[0xE7F280A1 ^ 0xE7F280A8] = 0xA407CC87 ^ 0xA407CCE8;
        int n4 = 0x5416774F ^ 0x5416774F;
        byArray[n4] = 0xA1FA5789 ^ 0xA1FA57F9;
        int n5 = 0x2B8CC356 ^ 0x2B8CC35D;
        byArray[n5] = 0xE23450E3 ^ 0xE234508D;
        int n6 = 0xCF14797F ^ 0xCF147911;
        byArray[0x2B66EDC0 ^ 0x2B66EDC5] = n6;
        byArray[0x2F5A6469 ^ 0x2F5A646A] = 0x5E7B75E6 ^ 0x5E7B759F;
        int n7 = 0xC3BFA106 ^ 0xC3BFA100;
        int n8 = 0x7A61295F ^ 0x7A612938;
        byArray[n7] = n8;
        String string = new String(byArray, "UTF-8");
        long l2 = CRACKME_868ab6c7_6429_4eca_8f87_264654d8da52_1b003ad7;
        boolean bl = true;
        block5: while (true) {
            long l3;
            if (!bl || (bl = false) || !true) {
                l2 = l3 / (0xE46D703A9F72B578L ^ 0xD0EC7B893903B349L);
            }
            switch ((int)l2) {
                case -1580783154: {
                    l3 = 0xD4D0FD0652C1264CL ^ 0x34950DEBC312B36FL;
                    continue block5;
                }
                case -1125488666: {
                    l3 = 0xF5B0C81A2FDB21A5L ^ 0x21E19DA21B4C2A8CL;
                    continue block5;
                }
                case 1921480960: {
                    break block5;
                }
            }
            break;
        }
        player.removeMetadata(string, (Plugin)zNb\u015b.Y\u017btq);
    }

    /*
     * Handled impossible loop by adding 'first' condition
     * WARNING - void declaration
     */
    1\u015bIH(C327 c327, Player player) {
        void CRACKME_89ce33a4_c9ec_4c87_a81a_3d7510b4b8b7c0074517;
        if (CRACKME_868ab6c7_6429_4eca_8f87_264654d8da52_1b003ad7 == (0xE9E31340F0C3A16FL ^ 0xA4A409C625577E8AL)) {
            int cfr_ignored_3 = 0xC27206FC ^ 0xC27206FD;
        } else {
            int cfr_ignored_4 = 0x7A5CA0DE ^ 0x7A5CA0DE;
        }
        int cfr_ignored_5 = 0xD001A043 ^ 0x3D6EAF3F;
        int cfr_ignored_6 = -500668973 - -127078744;
        long l = CRACKME_868ab6c7_6429_4eca_8f87_264654d8da52_1b003ad7;
        boolean bl = true;
        block11: while (true) {
            long l2;
            if (!bl || (bl = false) || !true) {
                l = l2 / (6651079193301139581L - 1501842014242469126L);
            }
            switch ((int)l) {
                case 257878559: {
                    l2 = 5499394108235475032L >>> "\u0000\u0000".length();
                    continue block11;
                }
                case 1902414234: {
                    l2 = 0x702CD2CE6BDA2FDDL ^ 0x305E35699E0A8854L;
                    continue block11;
                }
                case 1921480960: {
                    break block11;
                }
            }
            break;
        }
        CRACKME_89ce33a4_c9ec_4c87_a81a_3d7510b4b8b7cc187d95.I\u00f3CS = CRACKME_89ce33a4_c9ec_4c87_a81a_3d7510b4b8b7c0074517;
        long l3 = CRACKME_868ab6c7_6429_4eca_8f87_264654d8da52_1b003ad7;
        boolean bl2 = true;
        block12: while (true) {
            long l4;
            if (!bl2 || (bl2 = false) || !true) {
                l3 = l4 / (-6265889055722571360L - -4099952152965007998L);
            }
            switch ((int)l3) {
                case -859578927: {
                    l4 = -3299285135645358416L - -7331918423555312051L;
                    continue block12;
                }
                case -470610154: {
                    l4 = -7634046660474060518L - 8737109380432243364L;
                    continue block12;
                }
                case 101766134: {
                    l4 = 0xAC1CFD9FFFC14328L ^ 0xC1208828C1DC935L;
                    continue block12;
                }
                case 1921480960: {
                    break block12;
                }
            }
            break;
        }
        CRACKME_89ce33a4_c9ec_4c87_a81a_3d7510b4b8b7cc187d95.Q\u017c\u0143\u017c = player;
        while (true) {
            long l5;
            long l6;
            if ((l6 = (l5 = CRACKME_868ab6c7_6429_4eca_8f87_264654d8da52_1b003ad7 - (0x5AD538095FFDCBF5L ^ 0xB4CB23F90B17BBE4L)) == 0L ? 0 : (l5 < 0L ? -1 : 1)) == false) {
                continue;
            }
            int n = 0xE0554028 ^ 0xE0554029;
            if (l6 == n) {
                1\u015bIH CRACKME_89ce33a4_c9ec_4c87_a81a_3d7510b4b8b7cc187d95;
                return;
            }
            l6 = 0x783D8714 ^ 0x3F43D5F1;
        }
    }
}
