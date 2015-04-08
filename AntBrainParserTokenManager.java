/* AntBrainParserTokenManager.java */
/* Generated By:JavaCC: Do not edit this line. AntBrainParserTokenManager.java */
import java.io.PrintStream ;
import java.io.StringReader;

/** Token Manager. */
@SuppressWarnings("unused")public class AntBrainParserTokenManager implements AntBrainConstants {

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0){
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0){
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0(){
   switch(curChar)
   {
      case 10:
         return jjStopAtPos(0, 29);
      case 65:
         return jjMoveStringLiteralDfa1_0(0x800L);
      case 68:
         return jjMoveStringLiteralDfa1_0(0x40L);
      case 70:
         return jjMoveStringLiteralDfa1_0(0xa7c200L);
      case 72:
         return jjMoveStringLiteralDfa1_0(0x400400L);
      case 76:
         return jjMoveStringLiteralDfa1_0(0x1001000L);
      case 77:
         return jjMoveStringLiteralDfa1_0(0x100108L);
      case 80:
         return jjMoveStringLiteralDfa1_0(0x20L);
      case 82:
         return jjMoveStringLiteralDfa1_0(0x2082000L);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x4L);
      case 84:
         return jjMoveStringLiteralDfa1_0(0x80L);
      case 85:
         return jjMoveStringLiteralDfa1_0(0x10L);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x100008L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x1001404L);
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x800L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x2002020L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x200L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x10L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0xee8100L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x14040L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x80L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x80020L);
      case 101:
         if ((active0 & 0x8000L) != 0L)
         {
            jjmatchedKind = 15;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active0, 0xa20800L);
      case 102:
         return jjMoveStringLiteralDfa3_0(active0, 0x1001000L);
      case 103:
         return jjMoveStringLiteralDfa3_0(active0, 0x2002000L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x14200L);
      case 109:
         return jjMoveStringLiteralDfa3_0(active0, 0x400010L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x4L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x40040L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x100488L);
      case 118:
         return jjMoveStringLiteralDfa3_0(active0, 0x100L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 72:
         return jjMoveStringLiteralDfa4_0(active0, 0x800000L);
      case 77:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000L);
      case 87:
         return jjMoveStringLiteralDfa4_0(active0, 0x20000L);
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x810L);
      case 100:
         if ((active0 & 0x40000L) != 0L)
            return jjStopAtPos(3, 18);
         break;
      case 101:
         if ((active0 & 0x100L) != 0L)
            return jjStopAtPos(3, 8);
         else if ((active0 & 0x400L) != 0L)
            return jjStopAtPos(3, 10);
         else if ((active0 & 0x400000L) != 0L)
            return jjStopAtPos(3, 22);
         return jjMoveStringLiteralDfa4_0(active0, 0x14000L);
      case 104:
         return jjMoveStringLiteralDfa4_0(active0, 0x2002000L);
      case 107:
         if ((active0 & 0x8L) != 0L)
         {
            jjmatchedKind = 3;
            jjmatchedPos = 3;
         }
         else if ((active0 & 0x80000L) != 0L)
            return jjStopAtPos(3, 19);
         return jjMoveStringLiteralDfa4_0(active0, 0x100020L);
      case 110:
         if ((active0 & 0x80L) != 0L)
            return jjStopAtPos(3, 7);
         break;
      case 112:
         if ((active0 & 0x40L) != 0L)
            return jjStopAtPos(3, 6);
         else if ((active0 & 0x200L) != 0L)
            return jjStopAtPos(3, 9);
         break;
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x4L);
      case 116:
         if ((active0 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 24;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active0, 0x1000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa5_0(active0, 0x1000L);
      case 85:
         return jjMoveStringLiteralDfa5_0(active0, 0x20L);
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x200000L);
      case 100:
         if ((active0 & 0x800L) != 0L)
            return jjStopAtPos(4, 11);
         break;
      case 101:
         if ((active0 & 0x4L) != 0L)
            return jjStopAtPos(4, 2);
         return jjMoveStringLiteralDfa5_0(active0, 0x100000L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x20000L);
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x14000L);
      case 111:
         return jjMoveStringLiteralDfa5_0(active0, 0x800000L);
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x10L);
      case 116:
         if ((active0 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 25;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x2000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa6_0(active0, 0x2000L);
      case 100:
         if ((active0 & 0x4000L) != 0L)
         {
            jjmatchedKind = 14;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active0, 0x10000L);
      case 104:
         return jjMoveStringLiteralDfa6_0(active0, 0x1000L);
      case 107:
         if ((active0 & 0x10L) != 0L)
            return jjStopAtPos(5, 4);
         break;
      case 109:
         return jjMoveStringLiteralDfa6_0(active0, 0x800000L);
      case 112:
         if ((active0 & 0x20L) != 0L)
            return jjStopAtPos(5, 5);
         break;
      case 114:
         if ((active0 & 0x100000L) != 0L)
            return jjStopAtPos(5, 20);
         return jjMoveStringLiteralDfa6_0(active0, 0x200000L);
      case 116:
         return jjMoveStringLiteralDfa6_0(active0, 0x20000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 87:
         return jjMoveStringLiteralDfa7_0(active0, 0x10000L);
      case 101:
         if ((active0 & 0x800000L) != 0L)
            return jjStopAtPos(6, 23);
         return jjMoveStringLiteralDfa7_0(active0, 0x1000L);
      case 104:
         return jjMoveStringLiteralDfa7_0(active0, 0x22000L);
      case 107:
         return jjMoveStringLiteralDfa7_0(active0, 0x200000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 70:
         return jjMoveStringLiteralDfa8_0(active0, 0x20000L);
      case 97:
         return jjMoveStringLiteralDfa8_0(active0, 0x1000L);
      case 101:
         return jjMoveStringLiteralDfa8_0(active0, 0x202000L);
      case 105:
         return jjMoveStringLiteralDfa8_0(active0, 0x10000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa9_0(active0, 0x2000L);
      case 100:
         if ((active0 & 0x1000L) != 0L)
            return jjStopAtPos(8, 12);
         break;
      case 111:
         return jjMoveStringLiteralDfa9_0(active0, 0x20000L);
      case 114:
         if ((active0 & 0x200000L) != 0L)
            return jjStopAtPos(8, 21);
         break;
      case 116:
         return jjMoveStringLiteralDfa9_0(active0, 0x10000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x2000L) != 0L)
            return jjStopAtPos(9, 13);
         break;
      case 104:
         return jjMoveStringLiteralDfa10_0(active0, 0x10000L);
      case 111:
         return jjMoveStringLiteralDfa10_0(active0, 0x20000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 70:
         return jjMoveStringLiteralDfa11_0(active0, 0x10000L);
      case 100:
         if ((active0 & 0x20000L) != 0L)
            return jjStopAtPos(10, 17);
         break;
      default :
         break;
   }
   return jjStartNfa_0(9, active0);
}
private int jjMoveStringLiteralDfa11_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(9, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(10, active0);
      return 11;
   }
   switch(curChar)
   {
      case 111:
         return jjMoveStringLiteralDfa12_0(active0, 0x10000L);
      default :
         break;
   }
   return jjStartNfa_0(10, active0);
}
private int jjMoveStringLiteralDfa12_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(10, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(11, active0);
      return 12;
   }
   switch(curChar)
   {
      case 111:
         return jjMoveStringLiteralDfa13_0(active0, 0x10000L);
      default :
         break;
   }
   return jjStartNfa_0(11, active0);
}
private int jjMoveStringLiteralDfa13_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(11, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(12, active0);
      return 13;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x10000L) != 0L)
            return jjStopAtPos(13, 16);
         break;
      default :
         break;
   }
   return jjStartNfa_0(12, active0);
}
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 9;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3fe000000000000L & l) != 0L)
                     { jjCheckNAddStates(0, 2); }
                  else if (curChar == 59)
                  {
                     if (kind > 28)
                        kind = 28;
                     { jjCheckNAdd(3); }
                  }
                  if ((0x3f000000000000L & l) != 0L)
                  {
                     if (kind > 26)
                        kind = 26;
                  }
                  else if ((0x3c0000000000000L & l) != 0L)
                  {
                     if (kind > 27)
                        kind = 27;
                  }
                  break;
               case 1:
                  if ((0x3c0000000000000L & l) != 0L && kind > 27)
                     kind = 27;
                  break;
               case 2:
                  if (curChar != 59)
                     break;
                  kind = 28;
                  { jjCheckNAdd(3); }
                  break;
               case 3:
                  if ((0x3ff000100000000L & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  { jjCheckNAdd(3); }
                  break;
               case 4:
                  if ((0x3fe000000000000L & l) != 0L)
                     { jjCheckNAddStates(0, 2); }
                  break;
               case 5:
                  if ((0x3ff000000000000L & l) != 0L && kind > 27)
                     kind = 27;
                  break;
               case 6:
               case 8:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAdd(5); }
                  break;
               case 7:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  kind = 28;
                  jjstateSet[jjnewStateCnt++] = 3;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 9 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   5, 6, 7, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, "\123\145\156\163\145", "\115\141\162\153", 
"\125\156\155\141\162\153", "\120\151\143\153\125\160", "\104\162\157\160", "\124\165\162\156", 
"\115\157\166\145", "\106\154\151\160", "\110\145\162\145", "\101\150\145\141\144", 
"\114\145\146\164\101\150\145\141\144", "\122\151\147\150\164\101\150\145\141\144", "\106\162\151\145\156\144", 
"\106\157\145", "\106\162\151\145\156\144\127\151\164\150\106\157\157\144", 
"\106\157\145\127\151\164\150\106\157\157\144", "\106\157\157\144", "\122\157\143\153", "\115\141\162\153\145\162", 
"\106\157\145\115\141\162\153\145\162", "\110\157\155\145", "\106\157\145\110\157\155\145", "\114\145\146\164", 
"\122\151\147\150\164", null, null, null, "\12", };
protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      jjmatchedPos = -1;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100000000L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

    /** Constructor. */
    public AntBrainParserTokenManager(SimpleCharStream stream){

      if (SimpleCharStream.staticFlag)
            throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");

    input_stream = stream;
  }

  /** Constructor. */
  public AntBrainParserTokenManager (SimpleCharStream stream, int lexState){
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Reinitialise parser. */
  public void ReInit(SimpleCharStream stream)
  {
    jjmatchedPos = jjnewStateCnt = 0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  private void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 9; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }

  /** Reinitialise parser. */
  public void ReInit(SimpleCharStream stream, int lexState)
  {
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Switch to specified lex state. */
  public void SwitchTo(int lexState)
  {
    if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x3ffffffdL, 
};
static final long[] jjtoSkip = {
   0x2L, 
};
    protected SimpleCharStream  input_stream;

    private final int[] jjrounds = new int[9];
    private final int[] jjstateSet = new int[2 * 9];

    
    protected char curChar;
}
