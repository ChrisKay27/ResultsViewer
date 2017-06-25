package database;

import ui.ExperimentParams;
import utils.Range;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 6/19/2017.
 */
public class QueryParams {

    private ExperimentParams XAxisParam;
    private ExperimentParams seriesParam;

    private Range xAxisRange = new Range(0,500);

    private Map<ExperimentParams, String> paramValues = new HashMap<>();

    public QueryParams(ExperimentParams XAxisParam, ExperimentParams seriesParam) {
        this.XAxisParam = XAxisParam;
        this.seriesParam = seriesParam;
    }

    public ExperimentParams getXAxisParam() {
        return XAxisParam;
    }

    public void setXAxisParam(ExperimentParams XAxisParam) {
        this.XAxisParam = XAxisParam;
    }

    public ExperimentParams getSeriesParam() {
        return seriesParam;
    }

    public void setSeriesParam(ExperimentParams seriesParam) {
        this.seriesParam = seriesParam;
    }

    public Map<ExperimentParams, String> getParamValues() {
        return paramValues;
    }

    public void setParamValues(Map<ExperimentParams, String> paramValues) {
        this.paramValues = paramValues;
    }

    public Range getxAxisRange() {
        return xAxisRange;
    }

    public void setxAxisRange(Range xAxisRange) {
        this.xAxisRange = xAxisRange;
    }
    public void setxAxisRange(int xMin, int xMax) {
        xAxisRange.setxMin(xMin);
        xAxisRange.setxMax(xMax);
    }

}
