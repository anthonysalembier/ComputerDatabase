package com.excilys.util;


/**
 * @inv
 *     getPage() >= 1
 *     1 <= getProperties().size()
 *     getSize() >= 0
 *     getSort() != null
 */
public interface Page {
    /**
     * Default property on which apply the sorting
     */
    String DEFAULT_PROPERTY = "ID";
    /**
     * Default size
     */
    int DEFAULT_SIZE = 10;

    boolean isPrevious();

    /**
     * @return Current page
     */
    int getPage();

    /**
     * @param page Current page
     * @pre page >= 1
     */
    void setPage(int page);

    /**
     * @return Number entities by page
     */
    int getSize();

    /**
     * @param size Number entities
     * @pre size >= 0
     */
    void setSize(int size);

    /**
     * @return Offset for this page
     */
    int getOffset();

    /**
     * @return Current sort
     */
    Sort getSort();

    /**
     * @param sort Current sort
     * @pre sort != null
     */
    void setSort(Sort sort);

    /**
     * Returns the first {@link Page}.
     *
     * @return first page
     */
    Page getFirst();

    /**
     * Returns the {@link Page} requesting the next {@link Page}.
     *
     * @return next page
     */
    Page getNext();

    /**
     * @return Properties on which apply the sort
     */
    String getProperties();

    /**
     * @param properties Properties for the sort
     * @pre |properties| > 0
     */
    void setProperties(String... properties);

    enum Sort {
        ASC, DESC
    }
}