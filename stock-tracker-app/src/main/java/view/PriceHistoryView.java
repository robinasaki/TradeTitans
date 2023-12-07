package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.view_price_history.PriceHistoryViewModel;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.*;
import org.jfree.data.xy.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.TreeMap;
import java.util.Map;
import java.text.SimpleDateFormat;

public class PriceHistoryView extends JPanel {
    public final String viewName = "price_history";

    private PriceHistoryViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    public PriceHistoryView(PriceHistoryViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;

        initView();

        viewModel.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("state")) {
                updatePriceHistory();
            }
        });
    }

    private void initView() {
        setLayout(new BorderLayout());

        updatePriceHistory();

        JButton backButton = new JButton("Back to Holdings");
        backButton.addActionListener(new BackButtonListener());
        backButton.setPreferredSize(new Dimension(200, 50));
        add(backButton, BorderLayout.SOUTH);
    }

    private void updatePriceHistory() {
        // clear panel
        removeAll();

        // get data from state
        String portfolioName = viewModel.getState().getPortfolioName();
        String assetName = viewModel.getState().getAssetName();
        TreeMap<Date, Double> priceHistory = viewModel.getState().getPriceHistory();

        // create plot
        XYSeries series = new XYSeries(assetName);
        for (Map.Entry<Date, Double> entry : priceHistory.entrySet()) {
            series.add(entry.getKey().getTime(), entry.getValue());
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                assetName + " Price History",
                "Date",
                "Price",
                dataset,
                false,
                true,
                false
        );
        XYPlot plot = (XYPlot) chart.getPlot();

        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        dateAxis.setDateFormatOverride(new SimpleDateFormat("MMMM-d-yyyy"));
        dateAxis.setAutoRange(true);
        dateAxis.setVerticalTickLabels(true);
        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
        numberAxis.setAutoRange(true);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        plot.setRenderer(renderer);
        JPanel chartJPanel = new JPanel();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        //chartPanel.setPreferredSize(new Dimension(700, 500));
        add(chartPanel, BorderLayout.CENTER);

    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewManagerModel.setActiveView("holdings");
        }
    }
}
