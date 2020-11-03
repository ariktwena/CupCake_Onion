package domain.bottom;

import core.Bottom;
import core.Topping;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface BottomRepository {
    ArrayList<Bottom> getAllBottomsFromDB() throws DBexception;

    Bottom getBottomById(int bottom_id) throws DBexception;
}
