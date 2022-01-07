package com.telegram.services;

import com.telegram.dto.*;
import com.telegram.model.MyPortfolio;
import com.telegram.model.Stock;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface StockService {
    Stock getStockByTicker(String Ticker);
    StocksDto getStocksByTickers(TickersDto tickers);
    StocksPricesDto getPrices(FigiesDto figiesDto);
    StocksPricesDto getPricesProfile(PortfolioDto portfolioDto);
    PortfolioDto getPortfolioDto() throws ExecutionException, InterruptedException;
    List<MyPortfolio> getPortfolioDtoPrice(PortfolioDto portfolioDto, StocksPricesDto stocksPricesDto);
    ResultsDto getResultsProfile(PortfolioDto portfolioDto, StocksPricesDto stocksPricesDto);
}
