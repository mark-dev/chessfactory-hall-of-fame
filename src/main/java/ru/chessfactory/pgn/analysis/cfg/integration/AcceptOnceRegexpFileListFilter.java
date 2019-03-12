package ru.chessfactory.pgn.analysis.cfg.integration;

import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.filters.RegexPatternFileListFilter;

import java.io.File;

public class AcceptOnceRegexpFileListFilter extends AcceptOnceFileListFilter<File> {
    private RegexPatternFileListFilter delegate;

    public AcceptOnceRegexpFileListFilter(String regexp) {
        //TODO: bounded queue super(1000)
        delegate = new RegexPatternFileListFilter(regexp);
    }

    @Override
    public boolean accept(File file) {
        return delegate.accept(file) && super.accept(file);
    }
}
