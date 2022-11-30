package dao.impl;

import io.vavr.control.Either;
import modelo.Reader;

import java.util.List;

public interface DaoReadersImpl {
    Either<String, List<Reader>> getReaders();

    Either<String, Boolean> saveReaderList(List<Reader> readerList);
}
