//package book_01stackandlist;
//
/// **
// * @Description
// * @Author Scurry
// * @Date 2022-11-09 18:47
// */
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 最大值减去最小值小于或者等于num的子数组的数量
// * 给定数组arr[N] 和整数num
// * max(arr[i...j])-min(arr[i...j]) < =num
// * 要求时间复杂度O（N）
// *
// *arr[1 3 21 2  2 1 ]  3
// */
//public class ChildArrNums {
//
//
//    public int getChildNum(int[] arr,int index) {
//
//
//        if (index == arr.length) {
//            // 假设数组的长度是5
//            // 最后一条走到这里index就是5
//            if (index == 0) {
//                return 0;
//            }
//            if (index == 1) {
//                return 1;
//            }
//
//        }else {
//            boolean notExist = false;
//            // 不在数组里面
//            int childNum = getChildNum(arr, index + 1);
//            notExist = true;
//            int childNum1 = getChildNum(arr, index + 1);
//            return childNum +childNum1;
//        }
//
//
//        return 0;
//    }
//
//}
