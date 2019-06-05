package com.inter.trains.command.filter;


public interface Filter {

    Filter addFilter(Filter filter);

    /*
     *  return :
     *          1   match to request condition
     *          0   pending, need to filter in next.
     *         -1   not match to request condition
     */
    int doFilter(int preFilterResult);

}
