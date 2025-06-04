class Solution:
    def _lastSubstring(self, s: str) -> str:
        i = 0
        j = 1
        k = 0
        while j + k < len(s):
            if s[i + k] == s[j + k]:
                k += 1
            elif s[i + k] > s[j + k]:
                j = j + k + 1
                k = 0
            else:
                i = max(i + k + 1, j)
                j = i + 1
                k = 0
        return s[i:]

    def answerString(self, word: str, numFriends: int) -> str:
        #uh, that question is weird, not the way how i am normally doing, as a daily
        if numFriends == 1:
            return word
        s = self._lastSubstring(word)
        sz = len(word) - numFriends + 1
        return s[:min(len(s), sz)]
