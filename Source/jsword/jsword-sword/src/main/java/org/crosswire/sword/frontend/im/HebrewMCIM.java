package org.crosswire.sword.frontend.im;

/**
 * Title: Keyboard mapping for Michigan-Claremont Hebrew input
 * Description:
 * Copyright:    Copyright (c) 2001 CrossWire Bible Society under the terms of the GNU GPL
 * Company:
 * @author Troy A. Griffitts
 * @version 1.0
 */

import java.util.Hashtable;

public class HebrewMCIM extends SWInputMethod {

    char subst[] = new char[255];
    Hashtable subst2[] = new Hashtable[12];
    Hashtable multiChars = new Hashtable();

    public HebrewMCIM(String name) {
        super(name);
        init();
    }

    @Override
    public String translate(char in) {

        char retVal = 0;
        StringBuilder retString = new StringBuilder();
        if (getState() > 1) {
            if (getState() >= subst2.length) { // serious issue with internal
                                               // structure
                setState(0);
                retString.append(in);
                return retString.toString();
            }
            Integer find = (Integer) subst2[getState()].get(Integer.valueOf(in));
            if (find != null)
                retVal = (char) find.intValue();
            else
                retVal = in;

            setState(0);
            retString.append(retVal);
            return retString.toString();
        }

        if (in >= subst.length) {
            setState(0);
            retString.append(in);
            return retString.toString();
        }
        retVal = subst[in];

        if (retVal == 0) {
            setState(0);
            retString.append(in);
            return retString.toString();
        }
        if (retVal > 100) {
            setState(1);
            retString.append(retVal);
            return retString.toString();
        }
        if (retVal == 50) { // multiChar
            setState(1);
            Integer[] chars = (Integer[]) multiChars.get(Integer.valueOf(in));
            if (chars != null) {
                for (int i = 0; i < chars.length; i++)
                    retString.append((char) chars[i].intValue());
                return retString.toString();
            }
        }
        setState(retVal);
        return null;
    }

    private void init() {
        for (int i = 0; i < 255; i++)
            subst[i] = 0;

        subst[')'] = 1488;
        subst['B'] = 1489;
        subst['G'] = 1490;
        subst['D'] = 1491;
        subst['H'] = 1492;
        subst['W'] = 1493;
        subst['Z'] = 1494;
        subst['X'] = 1495;
        subst['+'] = 1496;
        subst['Y'] = 1497;

        subst['k'] = 1498; // finals
        subst['m'] = 1501;
        subst['n'] = 1503;
        subst['c'] = 1509;

        subst['P'] = 1508;
        subst['K'] = 1499;
        subst['L'] = 1500;
        subst['M'] = 1502;
        subst['N'] = 1504;
        subst['S'] = 1505;
        subst['('] = 1506;
        subst['p'] = 1507;
        subst['C'] = 1510;
        subst['Q'] = 1511;
        subst['R'] = 1512;
        subst['#'] = 1513;

        // special multiChars
        subst['&'] = 50;
        subst['$'] = 50;

        multiChars.put(Integer.valueOf('&'), new Integer[] {
                Integer.valueOf(1513), Integer.valueOf(1474)
        });
        multiChars.put(Integer.valueOf('$'), new Integer[] {
                Integer.valueOf(1513), Integer.valueOf(1473)
        });

        subst['T'] = 1514;

        // VOWELS
        subst['A'] = 1463;
        subst['F'] = 1464;
        subst['E'] = 1462;
        subst['"'] = 1461;
        subst['I'] = 1460;
        subst['O'] = 1465;
        subst['U'] = 1467;

        // OTHER DIACRITICS
        subst['.'] = 1468;
        subst['-'] = 1470;
        subst[','] = 1471;

        // Compound input
        for (int i = 2; i < 12; i++)
            subst2[i] = new Hashtable();

        // CANTILLATION

        subst[':'] = 2;
        subst2[2].put(Integer.valueOf('A'), Integer.valueOf(1458));
        subst2[2].put(Integer.valueOf('E'), Integer.valueOf(1457));
        subst2[2].put(Integer.valueOf('F'), Integer.valueOf(1459));

        /*
         * Telisha qetana is postpositive as in '04' above. However, Michigan #
         * code '24' is for a medial telisha. Graphically, there is no #
         * difference.
         */
        subst['2'] = 5;
        subst2[5].put(Integer.valueOf('4'), Integer.valueOf(1449));

        /*
         * Note Michigan encoding distinguishes between medial metheg '35'
         * (occuring # on the left of the vowel), and the ordinary meteg '95'
         * (occuring on the # right of the vowel). It is also used for silluq.
         */
        subst['3'] = 6;
        subst2[6].put(Integer.valueOf('3'), Integer.valueOf(1433));
        subst2[6].put(Integer.valueOf('5'), Integer.valueOf(1469));

        /*
         * The Michigan code of telisha gedola in medial position. Graphically,
         * # there is no difference.
         */
        subst['4'] = 7;
        subst2[7].put(Integer.valueOf('4'), Integer.valueOf(1440));

        subst['6'] = 8;
        subst2[8].put(Integer.valueOf('0'), Integer.valueOf(1451));
        subst2[8].put(Integer.valueOf('1'), Integer.valueOf(1436));

        subst['1'] = 4;
        subst2[4].put(Integer.valueOf('0'), Integer.valueOf(1434));

        /*
         * In the poetic books, prepositive dehi occurs; it's unclear whether #
         * tipeha also occurs in the poetic books. Otherwise, we could simply #
         * check for what book in the Tanach we are in. Michigan uses the same #
         * code for each.
         */

        subst2[4].put(Integer.valueOf('3'), Integer.valueOf(1430));

        /*
         * This is the poetic accent mugrash, which also includes rebia, but is
         * # encoded separately as '81' in the Michigan text.
         */
        subst2[4].put(Integer.valueOf('1'), Integer.valueOf(1437));
        subst2[4].put(Integer.valueOf('4'), Integer.valueOf(1440));

        subst['0'] = 3;
        subst2[3].put(Integer.valueOf('0'), Integer.valueOf(1475));
        subst2[3].put(Integer.valueOf('1'), Integer.valueOf(1426));

        /*
         * According to BHS, zarqa and sinnor are both postpositive. However, #
         * the Michigan encoding uses one code for both. The Unicode zarqa #
         * (0x0598) is definitely NOT postpositive. And further, the shape of #
         * the symbol is different in BHS and Uniocde. This needs further #
         * research to determine what's going on here. For now, we follow BHS #
         * and use the postpositive Unicode zinor or both accents.
         */

        subst2[3].put(Integer.valueOf('2'), Integer.valueOf(1454));

        /*
         * Pashta is postpositive, and the Unicode equivalent reflects # this.
         * However, there is a poetic equivalent -- azla legarmeh -- # which is
         * not postpositive, but no equivalent code point exists in # Unicode.
         * The Michigan encoding does not distinguish between the two, #
         * although it could be algorithmically determined.
         */

        subst2[3].put(Integer.valueOf('3'), Integer.valueOf(1433));
        subst2[3].put(Integer.valueOf('4'), Integer.valueOf(1449));
        subst2[3].put(Integer.valueOf('5'), Integer.valueOf(1472));

        /*
         * This is the Unicode Hebrew *accent*; there is also another Hebrew #
         * *punctuation* called GERSHAYIM 0x05F4. I'm using the more #
         * traditional rounded marks, rather than the alternate straight #
         * marks.
         */

        subst2[8].put(Integer.valueOf('2'), Integer.valueOf(1438));

        // Also known as azla
        subst2[8].put(Integer.valueOf('3'), Integer.valueOf(1448));
        subst2[8].put(Integer.valueOf('4'), Integer.valueOf(1452));
        subst2[8].put(Integer.valueOf('5'), Integer.valueOf(1427));

        subst['8'] = 9;
        subst2[9].put(Integer.valueOf('0'), Integer.valueOf(1428));
        subst2[9].put(Integer.valueOf('1'), Integer.valueOf(1431));

        /*
         * Note, this accent is actually sinnorit, but it does not exist as a #
         * separate glyph in the Unicode standard. The 'ZINOR' Unicode accent #
         * is postpositive, while sinnorit is not. ZARQA is as close as I can #
         * get to this.
         */
        subst2[9].put(Integer.valueOf('2'), Integer.valueOf(1432));

        /*
         * The Unicode form does not match the form used by BHS, but the names #
         * are the same.
         */
        subst2[9].put(Integer.valueOf('3'), Integer.valueOf(1441));
        subst2[9].put(Integer.valueOf('4'), Integer.valueOf(1439));
        subst2[9].put(Integer.valueOf('5'), Integer.valueOf(1429));

        subst['7'] = 10;
        subst2[10].put(Integer.valueOf('0'), Integer.valueOf(1444));
        subst2[10].put(Integer.valueOf('1'), Integer.valueOf(1445));
        subst2[10].put(Integer.valueOf('2'), Integer.valueOf(1446));
        subst2[10].put(Integer.valueOf('3'), Integer.valueOf(1430)); // also '13', '73'
                                                             // also is used for
                                                             // majela
        subst2[10].put(Integer.valueOf('4'), Integer.valueOf(1443));
        subst2[10].put(Integer.valueOf('5'), Integer.valueOf(1469)); // this is silluq;
                                                             // should appear to
                                                             // the left of the
                                                             // vowel

        subst['9'] = 11;
        subst2[11].put(Integer.valueOf('1'), Integer.valueOf(1435));
        subst2[11].put(Integer.valueOf('2'), Integer.valueOf(1425));
        subst2[11].put(Integer.valueOf('3'), Integer.valueOf(1450));
        subst2[11].put(Integer.valueOf('4'), Integer.valueOf(1447));
        subst2[11].put(Integer.valueOf('5'), Integer.valueOf(1469)); // should appear to
                                                             // the right of the
                                                             // vowel

    }
}

/*
 * 
 * 
 * # CANTILLION MARKS
 * 
 * my $ETNAHTA = '&#1425;'; # officially the Unicode name for this symbol was
 * "SEGOL." However, that is # not a unique name, conflicting with the vowel of
 * the same name. Further, # the position of the symbol is different. I have
 * changed the name of the # accent to "SEGOLTA," the traditional name for this
 * accent. my $SEGOLTA = '&#1426;'; my $SHALSHELET = '&#1427;'; my $ZAQEF_QATAN
 * = '&#1428;'; my $ZAQEF_GADOL = '&#1429;'; my $TIPEHA = '&#1430;'; my $REVIA =
 * '&#1431;'; my $ZARQA = '&#1432;'; my $PASHTA = '&#1433;'; my $YETIV =
 * '&#1434;'; my $TEVIR = '&#1435;'; my $GERESH = '&#1436;'; my $GERESH_MUQDAM =
 * '&#1437;'; my $GERSHAYIM = '&#1438;'; my $QARNEY_PARA = '&#1439;'; my
 * $TELISHA_GEDOLA = '&#1440;'; my $PAZER = '&#1441;'; my $MUNAH = '&#1443;'; my
 * $MAHAPAKH = '&#1444;'; my $MERKHA = '&#1445;'; my $MERKHA_KEFULA = '&#1446;';
 * my $DARGA = '&#1447;'; my $QADMA = '&#1448;'; my $TELISHA_QETANA = '&#1449;';
 * my $YERAH_BEN_YOMO = '&#1450;'; my $OLE = '&#1451;'; my $ILUY = '&#1452;'; my
 * $DEHI = '&#1453;'; my $ZINOR = '&#1454;'; # HEBREW MARK my $MASORA_CIRCLE =
 * '&#1455;'; # HEBREW EXTENDED-A points and punctuation my $SHEVA = '&#1456;';
 * my $HATAF_SEGOL = '&#1457;'; my $HATAF_PATAH = '&#1458;'; my $HATAF_QAMATS =
 * '&#1459;'; my $HIRIQ = '&#1460;'; my $TSERE = '&#1461;'; my $SEGOL =
 * '&#1462;'; # furtive Patah is not a distinct character my $PATAH = '&#1463;';
 * my $QAMATS = '&#1464;'; my $HOLAM = '&#1465;'; my $QUBUTS = '&#1467;'; # also
 * used as shuruq # falls within the base letter my $DAGESH_OR_MAPIQ =
 * '&#1468;'; # also used as siluq my $METAG = '&#1469;'; my $MAQAF = '&#1470;';
 * my $RAFE = '&#1471;'; # Also used for legarmeh # may be treated as spacing
 * punctuation, not as a point my $PASEQ = '&#1472;'; my $SHIN_DOT = '&#1473;';
 * my $SIN_DOT = '&#1474;'; my $SOF_PASUQ = '&#1475;'; # HEBREW MARK my
 * $UPPER_DOT = '&#1476;'; # HEBREW LETTERS based on ISO 8859-8 # aleph # x
 * (alef symbol - 2135) my $ALEF = '&#1488;'; # x (bet symbol - 2136) my $BET =
 * '&#1489;'; # x (gimel symbol - 2137) my $GIMEL = '&#1490;'; # x (dalet symbol
 * - 2138) my $DALET = '&#1491;'; my $HE = '&#1492;'; my $VAV = '&#1493;'; my
 * $ZAYIN = '&#1494;'; my $HET = '&#1495;'; my $TET = '&#1496;'; my $YOD =
 * '&#1497;'; my $FINAL_KAF = '&#1498;'; my $KAF = '&#1499;'; my $LAMED =
 * '&#1500;'; my $FINAL_MEM = '&#1501;'; my $MEM = '&#1502;'; my $FINAL_NUN =
 * '&#1503;'; my $NUN = '&#1504;'; my $SAMEKH = '&#1505;'; my $AYIN = '&#1506;';
 * my $FINAL_PE = '&#1507;'; my $PE = '&#1508;'; my $FINAL_TSADI = '&#1509;'; #
 * also known as zade my $TSADI = '&#1510;'; my $QOF = '&#1511;'; my $RESH =
 * '&#1512;'; my $SHIN = '&#1513;'; my $TAV = '&#1514;'; # Yiddish digraphs #
 * Hebrew Ligature # tsvey vovn my $DOUBLE_VAV = '&#1520;'; my $VAV_YOD =
 * '&#1521;'; # tsvey yudn my $DOUBLE_YOD = '&#1522;';
 * 
 * # Additional punctuation my $PUNCT_GERESH = '&#1523;'; my $PUNCT_GERSHAYIM =
 * '&#1524;'; # Reserved: 0x05F5" # x (hebrew point judeo-spanish varika - FB1E)
 * #my $JUDEO_SPANISH_VARIKA = pack("U",0xFB1E); # UTF-8 OxFB1E
 * 
 * ############################# # End of Unicode 2.0 Hebrew #
 * #############################
 * 
 * # A hash whose key is a Michagan code, and whose value is a Unicode #
 * equvalent
 * 
 * char subst[] = new char [255]; subst[')'] = 1488; 'B' => $BET, 'G' => $GIMEL,
 * 'D' => $DALET, 'H' => $HE, 'W' => $VAV, 'Z' => $ZAYIN, 'X' => $HET, '+' =>
 * $TET, 'Y' => $YOD, 'K' => $KAF, 'L' => $LAMED, 'M' => $MEM, 'N' => $NUN, 'S'
 * => $SAMEKH, '(' => $AYIN, 'P' => $PE, 'C' => $TSADI, 'Q' => $QOF, 'R' =>
 * $RESH, '#' => $SHIN, # the letter shin without a point '&' => ($SHIN .
 * $SIN_DOT), '$' => ($SHIN . $SHIN_DOT), # ' 'T' => $TAV, # VOWELS 'A' =>
 * $PATAH, 'F' => $QAMATS, 'E' => $SEGOL, '"' => $TSERE, 'I' => $HIRIQ, 'O' =>
 * $HOLAM, 'U' => $QUBUTS, ':' => $SHEVA, ':A' => $HATAF_PATAH, ':E' =>
 * $HATAF_SEGOL, ':F' => $HATAF_QAMATS, # OTHER DIACRITICS '.' =>
 * $DAGESH_OR_MAPIQ, '-' => $MAQAF, ',' => $RAFE, # CANTILLATION '00' =>
 * $SOF_PASUQ, '01' => $SEGOLTA, # According to BHS, zarqa and sinnor are both
 * postpositive. However, # the Michigan encoding uses one code for both. The
 * Unicode zarqa # (0x0598) is definitely NOT postpositive. And further, the
 * shape of # the symbol is different in BHS and Uniocde. This needs further #
 * research to determine what's going on here. For now, we follow BHS # and use
 * the postpositive Unicode zinor or both accents. '02' => $ZINOR, # Pashta is
 * postpositive, and the Unicode equivalent reflects # this. However, there is a
 * poetic equivalent -- azla legarmeh -- # which is not postpositive, but no
 * equivalent code point exists in # Unicode. The Michigan encoding does not
 * distinguish between the two, # although it could be algorithmically
 * determined. '03' => $PASHTA, '04' => $TELISHA_QETANA, '05' => $PASEQ, '10' =>
 * $YETIV, # In the poetic books, prepositive dehi occurs; it's unclear whether
 * # tipeha also occurs in the poetic books. Otherwise, we could simply # check
 * for what book in the Tanach we are in. Michigan uses the same # code for
 * each. '13' => $TIPEHA, # also $DEHI # This is the poetic accent mugrash,
 * which also includes rebia, but is # encoded separately as '81' in the
 * Michigan text. '11' => $GERESH_MUQDAM, '14' => $TELISHA_GEDOLA, # Telisha
 * qetana is postpositive as in '04' above. However, Michigan # code '24' is for
 * a medial telisha. Graphically, there is no # difference. '24' =>
 * $TELISHA_QETANA, '33' => $PASHTA, # The Michigan code of telisha gedola in
 * medial position. Graphically, # there is no difference. '44' =>
 * $TELISHA_GEDOLA, '60' => $OLE, '61' => $GERESH, # This is the Unicode Hebrew
 * *accent*; there is also another Hebrew # *punctuation* called GERSHAYIM
 * 0x05F4. I'm using the more # traditional rounded marks, rather than the
 * alternate straight # marks. '62' => $GERSHAYIM, # Also known as azla '63' =>
 * $QADMA, '64' => $ILUY, '65' => $SHALSHELET, '80' => $ZAQEF_QATAN, '81' =>
 * $REVIA, # Note, this accent is actually sinnorit, but it does not exist as a
 * # separate glyph in the Unicode standard. The 'ZINOR' Unicode accent # is
 * postpositive, while sinnorit is not. ZARQA is as close as I can # get to
 * this. '82' => $ZARQA, # The Unicode form does not match the form used by BHS,
 * but the names # are the same. '83' => $PAZER, '84' => $QARNEY_PARA, '85' =>
 * $ZAQEF_GADOL, # Note Michigan encoding distinguishes between medial metheg
 * '35' (occuring # on the left of the vowel), and the ordinary meteg '95'
 * (occuring on the # right of the vowel). It is also used for silluq. '35' =>
 * $METAG, '70' => $MAHAPAKH, '71' => $MERKHA, '72' => $MERKHA_KEFULA, '73' =>
 * $TIPEHA, # also '13', '73' also is used for majela '74' => $MUNAH, '75' =>
 * $METAG, # this is silluq; should appear to the left of the vowel '91' =>
 * $TEVIR, '92' => $ETNAHTA, '93' => $YERAH_BEN_YOMO, '94' => $DARGA, '95' =>
 * $METAG, # should appear to the right of the vowel
 * 
 * # Not used by the Michigan Encoding # $UPPER_DOT = '05C4'; );
 * 
 * # declare other variables my (@bhsLines,
 * 
 * @bhsVerse,
 * 
 * @entity_line) = ();
 * 
 * my ($i, $verse, $word, $character) = 0;
 * 
 * my ($element, $saveGuttural) = "";
 * 
 * # read in a line while (<>) { # Process one verse # iterate over every
 * character and change to XML decimal entity CHAR: for ( $i = 0; ($i <
 * scalar(@bhsVerse)); $i++) { # find and convert final kaf, mem, nun, pe, tsade
 * ( # if final form $bhsVerse[$i] =~ /[KMNPC]/ ) && ( ( # whitespace or
 * $bhsVerse[$i+1] =~ /[ \-?]/ ) || ( # EOL or $i == ( scalar(@bhsVerse) - 1 ) )
 * || ( # sof pasuq or ( $bhsVerse[$i+1] =~ /0/ ) && ( $bhsVerse[$i+2] =~ /0/ )
 * ) || ( # one accent followed by white, eol or ( ( $bhsVerse[$i+1] =~ /\d/ )
 * && ( $bhsVerse[$i+2] =~ /\d/ ) ) && ( ( $bhsVerse[$i+3] =~ /[ \-?]/ ) || ( $i
 * == ( scalar(@bhsVerse) - 1 ) ) ) ) || ( # two accents followed by white, eol
 * ( ( $bhsVerse[$i+1] =~ /\d/ ) && ( $bhsVerse[$i+2] =~ /\d/ ) && (
 * $bhsVerse[$i+3] =~ /\d/ ) && ( $bhsVerse[$i+4] =~ /\d/ ) ) && ( (
 * $bhsVerse[$i+5] =~ /[ \-?]/ ) || ( $i == ( scalar(@bhsVerse) - 1 ) ) ) ) || (
 * # followed by a vowel and white, eol, sof pasuq ( $bhsVerse[$i+1] =~ /[:F]/ )
 * && ( # followed by ( $bhsVerse[$i+2] =~ /[ \-?]/ ) || # whitespace or ( $i ==
 * ( scalar(@bhsVerse) - 1 ) ) || # eol or ( # sof pasuq ( $bhsVerse[$i+2] =~
 * /0/ ) && ( $bhsVerse[$i+3] =~ /0/ ) ) ) ) ) # end of what follows after final
 * letter && do { $bhsVerse[$i] =~ /K/ && eval { push @entity_line,$FINAL_KAF; }
 * && next CHAR; $bhsVerse[$i] =~ /M/ && eval { push @entity_line,$FINAL_MEM; }
 * && next CHAR; $bhsVerse[$i] =~ /N/ && eval { push @entity_line,$FINAL_NUN; }
 * && next CHAR; $bhsVerse[$i] =~ /P/ && eval { push @entity_line,$FINAL_PE; }
 * && next CHAR; $bhsVerse[$i] =~ /C/ && eval { push @entity_line,$FINAL_TSADI;
 * } && next CHAR; }; # find and convert "furtive patach" ( $bhsVerse[$i] =~ /A/
 * ) && # If the letter is a patach ( $bhsVerse[$i-1] =~ /[)HX(]/ ) && # and is
 * preceeded by a guttural ( ( $bhsVerse[$i-2] =~ /[AEFOU]/ ) || # and is
 * preceeded by a vowel ( ( $bhsVerse[$i-2] =~ /\./ ) && # or by suruq (
 * $bhsVerse[$i-3] =~ /W/ ) ) || # ( ( $bhsVerse[$i-2] =~ /W/ ) && # or by holem
 * (written plene) ( $bhsVerse[$i-3] =~ /O/ ) ) || # ( ( $bhsVerse[$i-2] =~ /Y/
 * ) && # or by hiriq-yod ( $bhsVerse[$i-3] =~ /I/ ) ) ) && do { $saveGuttural =
 * pop @entity_line; # snip off the gutteral push @entity_line,$PATAH; # push on
 * the patach push @entity_line,$saveGuttural; # push back on the gutteral next
 * CHAR; }; # convert cantillation # since we have previously dealt with all
 * other cases of # numbers, two digit patterns are all we have to search for
 * $bhsVerse[$i] =~ /\d/ && $bhsVerse[$i+1] =~ /\d/ && do { push
 * @entity_line,$Michigan2XMLentity{"$bhsVerse[$i]$bhsVerse[$i+1]"}; $i++; #
 * accents are two digits long, so advance past the 2nd digit next CHAR; }; #
 * convert katef vowels, which are two characters long $bhsVerse[$i] =~ /:/ &&
 * $bhsVerse[$i+1] =~ /[AEF]/ && do { push
 * @entity_line,$Michigan2XMLentity{"$bhsVerse[$i]$bhsVerse[$i+1]"}; $i++; next
 * CHAR; }; # convert everything else push
 * @entity_line,$Michigan2XMLentity{"$bhsVerse[$i]"}; } # end CHAR # print the
 * line to standard output with XML character-level encoding # each character
 * has the following format: # <c id="1kg1.verse#.word#.character#">&#1234;</c>
 * 
 * # set up the verse element $word = 1; $character = 1; print
 * "<verse>\n<word>\n"; # print each character element # if there is a space,
 * then close the word entity, open a new word # entity, increment the word
 * number, reset the character number to # zero. foreach $element (@entity_line)
 * { if ( $element =~ " " ) { $word++; $character = 1; print
 * "</word>\n<word>\n"; next; } print
 * "<c id=\"1kg1.$verse.$word.$character\">$element</c>\n"; $character++; } #
 * close the verse element print "</word></verse>\n"; # reinitialize variables
 * 
 * @bhsVerse = ();
 * 
 * @entity_line = ();
 * 
 * @bhsLines = (); } # end while # close the XML document print "</body>\n";
 */
