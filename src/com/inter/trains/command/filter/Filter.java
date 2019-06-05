package com.inter.trains.command.filter;


public interface Filter {

    Filter addFilter(Filter filter);

    int doFilter(int preFilterResult);

}
