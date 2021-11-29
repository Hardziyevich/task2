package by.hardziyevich.task2.validator;

import java.util.ArrayList;
import java.util.List;


/**
 * Class representing Monad design pattern.
 *
 */
public class ValidatorData {

    public String object;
    public boolean flagNull = false;
    public List<Boolean> flags = new ArrayList<>();


    public ValidatorData(String object) {
        if (object != null) {
            this.object = object;
        } else {
            flagNull = true;
            flags.add(!flagNull);
        }
    }

    public static ValidatorData of(String data) {
        return new ValidatorData(data);
    }

    public ValidatorData isDigit(String matcher) {
        if (!flagNull) {
            flags.add(object.matches(matcher));
        }
        return this;
    }

    /**
     * It isn`t allow using without isDigit
     * @param ifNull if can`t parse using that param.
     * @return double which was parsed
     */
    public double getDouble(double ifNull) {
        return flags.stream().allMatch(x -> x == Boolean.TRUE) ? Double.parseDouble(object) : ifNull;
    }

    /**
     * Preferable than getDouble(double ifNull). More safe.
     * @param matcher regular expression which is checking out data
     * @param ifNull if can`t parse using that param.
     * @return double which was parsed
     */
    public double getDouble(String matcher, double ifNull) {
        isDigit(matcher);
        return flags.stream().allMatch(x -> x == Boolean.TRUE) ? Double.parseDouble(object) : ifNull;
    }

    /**
     * It isn`t allow using without isDigit
     * @param ifNull if can`t parse using that param.
     * @return integer which was parsed
     */
    public int getInteger(int ifNull) {
        return flags.stream().allMatch(x -> x == Boolean.TRUE) ? Integer.parseInt(object) : ifNull;
    }

    /**
     * Preferable than getInteger(double ifNull). More safe.
     * @param matcher regular expression which is checking out data
     * @param ifNull if can`t parse using that param.
     * @return integer which was parsed
     */
    public int getInteger(String matcher, int ifNull) {
        isDigit(matcher);
        return flags.stream().allMatch(x -> x == Boolean.TRUE) ? Integer.parseInt(object) : ifNull;
    }

}
