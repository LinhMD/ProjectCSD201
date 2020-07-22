import org.jetbrains.annotations.Contract;

import java.util.Arrays;

public class MyStrMatcher {
	public static int indexOf_BF(String s, String pattern){
		int lengthS = s.length();
		int lengthP = pattern.length();
		int i , j;
		for (i = 0; i < lengthS - lengthP + 1; i++){
			j = 0;
			while (j < lengthP){
				if(s.charAt(i + j) != pattern.charAt(j)) break;
				j++;
				if (j == lengthP) return i;
			}
		}
		return -1;
	}

	public static int lastIndexOf_BF(String s, String pattern){
		int lengthS = s.length();
		int lengthP = pattern.length();
		int i , j;
		for (i = lengthS - lengthP; i >= 0; i--){
			j = 0;
			while (j < lengthP){
				if(s.charAt(i + j) != pattern.charAt(j)) break;
				j++;
				if (j == lengthP) return i;
			}
		}
		return -1;
	}

	public static int[] computeFailKMP(char[] p){
		int length = p.length;
		int[] fail = new int[length];
		fail[0] = 0;
		int j = 1, k = 0;
		while (j < length){
			if(p[j] == p[k])
				fail[j] = k++ + 1;
			else if(k > 0)  k = fail[k - 1];
			else fail[j] = 0;
			j++;
		}
		return fail;
	}

	@Contract(pure = true)
	public static int indexOf_KMP(char[] s, char[]p){
		int lengthS = s.length;
		int lengthP = p.length;
		int[] prefixes = computeFailKMP(p);
		int j = 0, k = 0;
		while(j < lengthS){
			if(s[j] == p[k]){
				if(k == lengthP - 1) return j - lengthP + 1;
			} else if(k > 0) k = prefixes[k - 1];
			j++;
			k++;
		}
		return -1;
	}

	public static int indexOf_KMP(String s, String p){
		return indexOf_KMP(s.toCharArray(), p.toCharArray());
	}

	public static void main(String[] args) {
		String s1 = "nanh na nan nanana na bamana kanananaha kakaka";
		String s2 = "bamana";
		String s3 = "na";
		System.out.println(indexOf_BF(s1, s2));
		System.out.println(indexOf_BF(s1, s3));
		System.out.println(lastIndexOf_BF(s1, s3));
		System.out.println(Arrays.toString(computeFailKMP(s1.toCharArray())));
		System.out.println(indexOf_KMP(s1, s2));
	}
}
