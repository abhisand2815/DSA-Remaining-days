import java.util.*;

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);

        List<List<String>> result = new ArrayList<>();
        String prefix = "";

        for (char c : searchWord.toCharArray()) {
            prefix += c;

            List<String> suggestions = new ArrayList<>();

            for (String product : products) {
                if (product.startsWith(prefix)) {
                    suggestions.add(product);

                    if (suggestions.size() == 3) {
                        break;
                    }
                }
            }

            result.add(suggestions);
        }

        return result;
    }
}