package services;

import models.Transaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyticsService {
    public Map<String, Double> getSpendingByCategory(List<Transaction> transactions) {
        Map<String, Double> analytics = new HashMap<>();
        for (Transaction t : transactions) {
            if (t.getType().equals("WITHDRAWAL") || t.getType().equals("TRANSFER")) {
                String cat = (t.getCategory() == null || t.getCategory().isEmpty()) ? "Other" : t.getCategory();
                analytics.put(cat, analytics.getOrDefault(cat, 0.0) + t.getAmount());
            }
        }
        return analytics;
    }
    
    public String getTopCategory(Map<String, Double> analytics) {
        if (analytics.isEmpty()) return "None";
        String top = "None";
        double max = 0;
        for (Map.Entry<String, Double> e : analytics.entrySet()) {
            if (e.getValue() > max) { max = e.getValue(); top = e.getKey(); }
        }
        return top + " (₹" + max + ")";
    }
}
