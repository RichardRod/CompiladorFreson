package lexico;


public class Estados {

    //atributos
    public static final int q01 = 1;
    public static final int q02 = 2;
    public static final int q03 = 3;
    public static final int q04 = 4;
    public static final int q05 = 5;
    public static final int q06 = 6;
    public static final int q07 = 7;
    public static final int q08 = 8;
    public static final int q09 = 9;
    public static final int q10 = 10;
    public static final int q11 = 11;
    public static final int q12 = 12;
    public static final int q13 = 13;
    public static final int q14 = 14;
    public static final int q15 = 15;
    public static final int q16 = 16;
    public static final int q17 = 17;
    public static final int q18 = 18;
    public static final int q19 = 19;
    public static final int q20 = 20;
    public static final int q21 = 21;
    public static final int q22 = 22;
    public static final int q23 = 23;
    public static final int q24 = 24;
    public static final int q25 = 25;
    public static final int q26 = 26;
    public static final int ACP = 27;
    public static final int ERR = 28;

    public static int[][] tablaEstados =
            {
                //   (_)                                                                                          ( )
                //  (a-z) (0-9) (.)  (") (+,-) (*,/) (<,>) (|)  (&)  (!)  (=)  (;)  (,)  (()  ())  ({)  (})  ($)  (\t) (otro)
                //  (A-Z)                                                                                         (\n)
                    {q06, q01, ERR, q04, q09, q10, q07, q11, q13, q15, q17, q19, q20, q21, q22, q23, q24, q25, ACP, ERR}, //q00
                    {ACP, q01, q02, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q01
                    {ERR, q03, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}, //q02
                    {ACP, q03, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q03
                    {q05, q05, q05, q05, q05, q05, q05, q05, q05, q05, q05, q05, q05, q05, q05, q05, q05, ERR, q05, q05}, //q04
                    {q05, q05, q05, q26, q05, q05, q05, q05, q05, q05, q05, q05, q05, q05, q05, q05, q05, ERR, q05, q05}, //q05
                    {q06, q06, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q06
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, q08, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q07
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q08
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q09
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q10
                    {ERR, ERR, ERR, ERR, ERR, ERR, ERR, q12, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}, //q11
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q12
                    {ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, q14, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}, //q13
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q14
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, q16, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q15
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q16
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, q18, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q17
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q18
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q19
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q20
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q21
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q22
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q23
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q24
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q25
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP},
                    {ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP, ACP}, //q27 ACP

            }; //fin de la tabla de estados
}
