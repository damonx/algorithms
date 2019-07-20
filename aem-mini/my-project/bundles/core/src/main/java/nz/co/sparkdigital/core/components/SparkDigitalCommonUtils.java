package nz.co.sparkdigital.core.components;

import com.adobe.cq.sightly.SightlyWCMMode;
import com.google.gson.JsonSyntaxException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

/**
 * Spark Digital utility class which provides static methods commonly used by other classes.
 *
 */
public class SparkDigitalCommonUtils {
    private static final Logger LOG = LoggerFactory.getLogger(SparkDigitalCommonUtils.class);

    private static final List<String> URL_PREFIXES = Arrays.asList("http", "https", ".html", "dam");
    private static final String DATA_SUFFIX_HTML_TEMPLATE = "<sup>%s</sup>";

    /**
     * Utility classes, which are collections of static members, are not meant to be instantiated.
     * It should not have public constructors.
     *
     */
    private SparkDigitalCommonUtils() {
        throw new Error("Do not instantiate me!");
    }

    /**
     * The method returns the simple date format for the date that is passed as a parameter.
     *
     * @param contentDateFromDialog - date content authored in the dialog properties.
     * @return contentDateFormatted
     */
    public static String getContentDateFormatted(final GregorianCalendar contentDateFromDialog) {
        String contentDateFormatted = null;
        if (null != contentDateFromDialog) {
            final String contentDateSuffix = getContentDateSuffix(contentDateFromDialog.get(Calendar.DAY_OF_MONTH));
            final SimpleDateFormat dateFormat = new SimpleDateFormat("dd'" + contentDateSuffix + "' MMM yyyy");
            dateFormat.setCalendar(contentDateFromDialog);
            contentDateFormatted = dateFormat.format(contentDateFromDialog.getTime());
        }
        return contentDateFormatted;
    }

    /**
     * The method returns the suffix of the date passed.
     *
     * @param day - int day of month
     * @return - returns the suffix
     */
    public static String getContentDateSuffix(final int day) {
        switch (day) {
            case 1:
            case 21:
            case 31:
                return String.format(DATA_SUFFIX_HTML_TEMPLATE, "st");

            case 2:
            case 22:
                return String.format(DATA_SUFFIX_HTML_TEMPLATE, "nd");

            case 3:
            case 23:
                return String.format(DATA_SUFFIX_HTML_TEMPLATE, "rd");

            default:
                return String.format(DATA_SUFFIX_HTML_TEMPLATE, "th");
        }
    }



    /**
     * This method converts the CQ page path to its corresponding URL after performing various
     * checks and validations.
     *
     * @param path - CQ page path
     * @return corresponding URL
     */
    public static String convertPathToUrl(final String path, final SightlyWCMMode wcmMode) {
        String url = path;
        boolean urlPrefixPresent = false;

        if (StringUtils.isNotEmpty(path)) {
            urlPrefixPresent = URL_PREFIXES.stream().anyMatch(urlPrefix -> StringUtils.containsIgnoreCase(path, urlPrefix));
            if (!urlPrefixPresent && !wcmMode.isDisabled()) {
                url = path + ".html";
            }
        }
        return url;
    }


    /**
     * This is a common method to for any date in specified date format.If only single date is to be
     * formatted then send 2nd Calendar date parameter as null
     *
     * @param sparkPage- object of SparkLabPageProperties class
     * @return -the formatted date in specified date format
     * @throws ParseException
     */
    public static String getFormattedDate(final Calendar calStartDate, final Calendar calEndDate, final String monthFormat) throws ParseException {

        String formattedDate = null;
        final SimpleDateFormat monFormat = new SimpleDateFormat(monthFormat);


        LOG.debug("startDate : " + calStartDate);
        LOG.debug("endDate : " + calEndDate);

        if (null != calStartDate && null != calEndDate) {
            final Date startDate = calStartDate.getTime();
            final Date endDate = calEndDate.getTime();

            if (startDate.compareTo(endDate) == -1) {
                // greater
                final StringBuilder dateBuilder = new StringBuilder();

                final int startDay = calStartDate.get(Calendar.DAY_OF_MONTH);
                final int endDay = calEndDate.get(Calendar.DAY_OF_MONTH);

                final String startMonth = calStartDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
                final String endMonth = calEndDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

                final int startYear = calStartDate.get(Calendar.YEAR);
                final int endYear = calEndDate.get(Calendar.YEAR);

                final GregorianCalendar gcalStartDate = new GregorianCalendar(startYear, calStartDate.get(Calendar.MONTH), startDay);
                final GregorianCalendar gcalEndDate = new GregorianCalendar(endYear, calEndDate.get(Calendar.MONTH), endDay);

                if (startYear == endYear) {
                    // same year
                    if (startMonth.equalsIgnoreCase(endMonth)) {
                        // same month
                        if (startDay != endDay) {
                            dateBuilder.append(startDay + getContentDateSuffix(startDay));
                            dateBuilder.append(" - ");
                            dateBuilder.append(endDay + getContentDateSuffix(endDay)).append(" ");
                            dateBuilder.append(monFormat.format(startDate)).append(" ");
                            dateBuilder.append(startYear);
                            formattedDate = dateBuilder.toString();

                        } else {
                            formattedDate = getContentDateFormatted(gcalStartDate);
                        }

                    } else {
                        // diff months
                        dateBuilder.append(startDay + getContentDateSuffix(startDay)).append(" ");
                        dateBuilder.append(monFormat.format(startDate));
                        dateBuilder.append(" - ");
                        dateBuilder.append(endDay + getContentDateSuffix(endDay)).append(" ");
                        dateBuilder.append(monFormat.format(endDate)).append(" ");
                        dateBuilder.append(startYear);
                        formattedDate = dateBuilder.toString();

                    }
                } else { // diff years
                    dateBuilder.append(getContentDateFormatted(gcalStartDate));
                    dateBuilder.append(" - ");
                    dateBuilder.append(getContentDateFormatted(gcalEndDate));
                    formattedDate = dateBuilder.toString();
                }

            } else {
                final GregorianCalendar gcalStartDate = new GregorianCalendar(calStartDate.get(Calendar.YEAR), calStartDate.get(Calendar.MONTH), calStartDate.get(Calendar.DAY_OF_MONTH));
                formattedDate = getContentDateFormatted(gcalStartDate);
            }

        } else if (null != calStartDate) {
            final GregorianCalendar gcalStartDate = new GregorianCalendar(calStartDate.get(Calendar.YEAR), calStartDate.get(Calendar.MONTH), calStartDate.get(Calendar.DAY_OF_MONTH));
            formattedDate = getContentDateFormatted(gcalStartDate);

        }
        LOG.debug("formatted Date : " + formattedDate);
        return formattedDate;
    }

    /**
     * The method returns the simple date format for the date that is passed as a parameter.
     *
     * @param contentDateFromDialog - date content authored in the dialog properties.
     * @return contentDateFormatted
     */
    public static String checkIfNull(final String object, final String alternative) {
        final String returnStr = (null == object) ? alternative : object;
        return returnStr;
    }


    /**
     * Gets the node from the resource being passed as parameter.
     *
     * @param resource Resource that needs to be adapted to a node
     * @return Node, resource adapts to node
     */
    public static Node getNodeFromResource(final Resource resource) {
        return resource.adaptTo(Node.class);
    }

    /**
     * Fetches and returns the values of multiField property.
     *
     * <p>Don't use this. If you want to, or are changing something that does, then speak
     * to Phil about his thoughts on how this should be implemented. I would have made the
     * change I want to, if I wasn't under the duress of trying to solve a P2 production
     * incident.</p>
     *
     * @param currentProperty represents property object.
     * @return Value array.
     * @throws RepositoryException if any
     */
    @Deprecated
    public static Value[] getJsonValues(final Property currentProperty) throws RepositoryException {
        Value[] value;
        if (currentProperty.isMultiple()) {
            value = currentProperty.getValues();
        } else {
            value = new Value[1];
            value[0] = currentProperty.getValue();
        }
        return value;
    }

    /**
     * Returns the value(s) of the specified property from the specified content node as a String.
     *
     * <p>Given the above method (getJsonValues()) exists, do not use this to read a bunch of JSON
     * from the repository and then later deserialise it into a Object. Hint: that should be all
     * part of the utility method. A method signature for this might be:
     * <pre>
     * public static &lt;T&gt; List&lt;T&gt; getValues(final Node node, final String propertyName, final Class&lt;T&gt; clasz)
     * </pre>
     * </p>
     *
     * @param node The content node we want to extract a list of values of a property from.
     * @param propertyName The name of the property whose values we want.
     * @return A (possibly empty) list of values associated with the property.
     * @throws RepositoryException If there was a problem reading the repository.
     */
    public static List<String> getValues(final Node node, final String propertyName) throws RepositoryException {
        final List<String> valueList = new ArrayList<>();

        if (node.hasProperty(propertyName)) {
            final Property property = node.getProperty(propertyName);

            if (property.isMultiple()) {
                final Value[] values = property.getValues();
                for (final Value value : values) {
                    valueList.add(value.getString());
                }
            } else {
                valueList.add(property.getString());
            }
        }
        return valueList;
    }


    /**
     * Converts an array of string to lower case.
     *
     * @param strings to be converted to lower case
     * @return An array of lower-cased strings or empty string array if parameter array is null or
     *         empty;
     */
    public static String[] convertStringArrayToLowerCase(final String[] strings) {
        if (ArrayUtils.isEmpty(strings)) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        return Arrays.stream(strings).map(StringUtils::lowerCase).toArray(String[]::new);
    }


    /**
     * Maps specified JCR property values to a list of objects.
     *
     * @param node JCR Node
     * @param propertyName
     * @param clazz the type to which the given property will be mapped
     * @return an immutable List of populated objects
     */
    public static <T> List<T> mapPropertiesToObjects(final Node node, final String propertyName, final Class<T> clazz) {
        List<T> populatedObjects = new ArrayList<>();
        try {
            populatedObjects = getValues(node, propertyName).stream().map(value -> JsonUtil.convertToObject(value, clazz)).collect(Collectors.toList());
        } catch (final IllegalStateException | RepositoryException | JsonSyntaxException exp) {
            LOG.error("SparkDigitalCommonUtils - An {} occurred in mapPropertiesToObjects method", exp.getClass().getSimpleName(), exp);
        }
        return Collections.unmodifiableList(populatedObjects);
    }
}
