package ru.chessfactory.pgn.analysis.read;

import chesspresso.Chess;
import chesspresso.pgn.PGNReader;
import chesspresso.pgn.PGNSyntaxError;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class PGNTokenEscapeReader extends PGNReader {
    public PGNTokenEscapeReader(InputStream in, String name) {
        super(in, name);
    }

    public PGNTokenEscapeReader(Reader reader, String name) {
        super(reader, name);
    }

    @Override
    protected int getChar() throws IOException {

        if (m_pushedBack) {
            m_pushedBack = false;
            return m_lastChar;
        }
        int ch = get();
        while (ch == '\n' || ch == '\r' || ch == TOK_PGN_ESCAPE || ch == TOK_LINE_COMMENT) {
            while ((ch == '\n' || ch == '\r') && ch >= 0) {
                ch = get();
            }
            if (ch == TOK_PGN_ESCAPE) {
                //http://rpm.pbone.net/index.php3/stat/45/idpl/19488088/numer/3/nazwa/Games::Chess::PGN
                //Игнорируем %, в случае если речь идет о вещах типа: [%clk:
                //По дефолту должно быть в начале строки, а строка на [ заканчиваться не может
                if (m_lastChar == '[') {
                    return ch;
                }

                do {
                    ch = get();
                } while (ch != '\n' && ch != '\r' && ch >= 0);
            } else if (ch == TOK_LINE_COMMENT) {
                do {
                    ch = get();
                } while (ch != '\n' && ch != '\r' && ch >= 0);
            } else {
                m_pushedBack = true;
                m_lastChar = ch;
                return '\n';
            }
        }
        if (ch < 0) ch = TOK_EOF;
        m_lastChar = ch;
        return ch;
    }

    @Override
    protected void parseMovetextSection() throws PGNSyntaxError, IOException {
        m_logger.trace("Entering parseMovetextSection()");

        boolean needsMoveNumber = true;
        int level = 0;
        while (!isLastTokenResult()) {
            m_logger.trace("parseMovetextSection(): !isLastTokenResult()");

            if (getLastToken() == TOK_LINE_BEGIN) {
                m_logger.trace("parseMovetextSection(): TOK_LINE_BEGIN");
                level++;
                m_curGame.getPosition().undoMove();
                needsMoveNumber = true;
                getNextToken();
            } else if (getLastToken() == TOK_LINE_END) {
                m_logger.trace("parseMovetextSection(): TOK_LINE_END");
                level--;
                if (level >= 0) {
                    m_curGame.goBackToMainLine();
                    needsMoveNumber = true;
                } else {
                    syntaxError("Unexpected variation end");
                }
                getNextToken();
            } else if (getLastToken() == TOK_COMMENT_BEGIN) {
                m_logger.trace("parseMovetextSection(): TOK_COMMENT_BEGIN");
                m_curGame.addComment(getLastTokenAsString());
                //TODO: Если последним ходом были черные, то тогда нужно указание следюущего хода
                needsMoveNumber = (m_curGame.getPosition().getToPlay() == Chess.WHITE);
                getNextToken();
            } else if (isNAGStart(getLastToken())) {
                m_logger.trace("parseMovetextSection(): isNAGStart()");
                parseNAG();
            } else {
                m_logger.trace("parseMovetextSection(): parseHalfMove()");
                parseHalfMove(needsMoveNumber);
                needsMoveNumber = (m_curGame.getPosition().getToPlay() == Chess.WHITE);
                getNextToken();
            }
        }
    }
}
