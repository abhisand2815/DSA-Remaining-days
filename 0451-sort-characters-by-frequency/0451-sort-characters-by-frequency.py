from collections import Counter

class Solution:
    def frequencySort(self, s: str) -> str:
        freq = Counter(s)

        sorted_chars = sorted(freq, key=freq.get, reverse=True)

        result = ""

        for ch in sorted_chars:
            result += ch * freq[ch]

        return result