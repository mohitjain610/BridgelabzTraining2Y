package org.gla.Searching;

import java.util.Arrays;
import java.util.Scanner;

public class SearchChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        boolean[] present = new boolean[n + 2];

        for (int num : arr) {
            if (num > 0 && num <= n + 1) {
                present[num] = true;
            }
        }

        int missing = 1;

        while (missing <= n + 1 && present[missing]) {
            missing++;
        }

        System.out.print("Enter target for Binary Search: ");
        int target = sc.nextInt();

        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;
        int index = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                index = mid;
                break;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println("First missing positive integer: " + missing);
        System.out.println("Sorted array: " + Arrays.toString(arr));
        System.out.println("Index of target: " + index);

        sc.close();
    }
}