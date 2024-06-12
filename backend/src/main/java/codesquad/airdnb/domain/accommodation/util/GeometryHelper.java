package codesquad.airdnb.domain.accommodation.util;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import static codesquad.airdnb.global.env.Constants.SRID;

public class GeometryHelper {
    public static Point createPoint(Double coordinateX, Double coordinateY) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(coordinateX, coordinateY));
        point.setSRID(SRID);
        return point;
    }
}