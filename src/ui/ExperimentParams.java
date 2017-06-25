package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chris on 6/12/2017.
 */
public enum ExperimentParams {
    deadlockDetectionProtocol, deadlockResolutionProtocol, topology, arrivalRate, priorityProtocol, numPages, detectionInterval, maxActiveTrans , updateRate;

    public static List<ExperimentParams> getXAxisParams(){
        List<ExperimentParams> xAxisParams = new ArrayList<>();
        xAxisParams.addAll(Arrays.asList(arrivalRate, numPages, detectionInterval , maxActiveTrans, updateRate));
        return xAxisParams;
    }

    public static List<ExperimentParams> getSeriesParams(){
        List<ExperimentParams> seriesParams = new ArrayList<>();
        seriesParams.addAll(Arrays.asList(deadlockDetectionProtocol, deadlockResolutionProtocol, priorityProtocol));
        return seriesParams;
    }

    private static List<ExperimentParams> intTypeExpParam = Arrays.asList(arrivalRate, numPages, detectionInterval, maxActiveTrans);



    public static boolean isIntType(ExperimentParams expParam) {
        return intTypeExpParam.contains(expParam);
    }
}
