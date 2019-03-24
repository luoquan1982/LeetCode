import java.util.Arrays;

/**
 * @Author: Json Wan
 * Created at: 2019/2/24.
 * Description:
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
Example:
Given array nums = [-1, 2, 1, -4], and target = 1.
The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 ˼·����ռ�ΪCn3���ؼ���������Ե���n3���Ĵ����ҳ��⣻
 n2logn��˼·��
 ��1��nlogn����
 ��2������nums[i]����ʣ����������ӽ�target-nums[i]��������
 ��3������ʣ�����nums[j]������ʣ�����������ӽ�target-nums[i]-nums[j]��һ����
 ϰ�ã�
 ��1���������������ҳ���Ϊ��ֵ��������������ʱ�临�Ӷ���O(n)������O(nlogn)����������β������ų�����
 ��2�����������ҳ���Ϊ��ֵ��������������ʱ�临�Ӷ���O(nlogn)��ԭ������������������ҪO(nlogn)��ʱ�䡣
 */
public class wanxu_pursue_0016 {

    //��best˼·��࣬�Ҿ��ò������ţ�����ʱ�临�Ӷ��ƺ���n2��Ϊʲô�أ�
    //ʵ���������ţ���Ϊ�������������ҳ���Ϊ��ֵ��������������ʱ�临�Ӷ���O(n)������O(nlogn)����������β������ų�����ÿ�ų�һ�μ�¼һ����̾���
    public int best2ThreeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE, result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return sum;
                }
                if (diff > Math.abs(target - sum)) {
                    diff = Math.abs(target - sum);
                    result = sum;
                }
                if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }
    //��ʵ����ⷨӦ�ò������ţ���ײ��õı������Ƕ��֣������������ʱ������Ӧ�û��
    public int bestThreeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length;i++){
            //�Ż�ϸ��1��ͬ�������ֱ����ظ�����
            if(i>0 && nums[i]==nums[i-1]) continue;

            int temp = twoSum(nums, target, nums[i], i+1, res);
            if(temp==target) return target;

            if(Math.abs(res-target)>Math.abs(temp-target)) res = temp;
        }
        return res;
    }

    private int twoSum(int[]nums, int target, int x1, int start, int res){
        int end = nums.length-1;
        while(start<end){
            int x2 = nums[start];
            int x3 = nums[end];
            int sum = x1+x2+x3;
            if(sum==target) return target;
            if(sum>target) end--;
            else start++;
            if(Math.abs(sum-target)<Math.abs(res-target)) res = sum;
        }
        return res;
    }

    //AC��0.8409���㷨Ӧ��������ʱ�临�Ӷ���//��ʵ���ǣ����Ÿ��Ӷ���O(n2)
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int distance=Integer.MAX_VALUE;
        int l=nums.length;
        for(int i=0;i<l-2;i++){
            int d=twoSumClosest(nums,i+1,target-nums[i]);
            if(Math.abs(d)<Math.abs(distance)){
                distance=d;
                if(distance==0){
                    break;
                }
            }
        }
        return target-distance;
    }

    private int twoSumClosest(int[] nums,int start, int target) {
        int distance=Integer.MAX_VALUE;
        int l=nums.length;
        for(int i=start;i<l-1;i++){
            int d=oneSumClosest(nums,i+1,target-nums[i]);
            if(Math.abs(d)<Math.abs(distance)){
                distance=d;
                if(distance==0){
                    break;
                }
            }
        }
        return distance;
    }
    //���ö��ֲ����Ż�
    private int oneSumClosest(int[] nums,int start, int target) {
        int distance=Integer.MAX_VALUE;
        int len=nums.length;
        //���ֵķ�ʽ
        int l=start,r=len-1;
        int m;
        while(l<r){
            m=l+(r-l)/2;
            if(nums[m]==target){
                return 0;
            }else if(nums[m]<target){
                l=m+1;
            }else{
                r=m-1;
            }
        }
        distance=target-nums[l];
        if(l-1>=start){
            if(Math.abs(target-nums[l-1])<Math.abs(distance)){
                distance=target-nums[l-1];
            }
        }
        if(l+1<=len-1){
            if(Math.abs(target-nums[l+1])<Math.abs(distance)){
                distance=target-nums[l+1];
            }
        }
//        �����ķ�ʽ
//        for(int i=start;i<len;i++){
//            int d=target-nums[i];
//            if(Math.abs(d)<Math.abs(distance)){
//                distance=d;
//                if(distance==0){
//                    break;
//                }
//            }
//        }
        return distance;
    }

    public static void main(String[] args) {
        wanxu_pursue_0016 instance=new wanxu_pursue_0016();
        System.out.println(instance.threeSumClosest(new int[]{-1, 2, 1, -4},1)==2);
        System.out.println(instance.threeSumClosest(new int[]{-1, 2, 1, -4,1,2},1)==1);
        System.out.println(instance.threeSumClosest(new int[]{1,2,3,4,5,6,7,8,9},10)==10);
    }
}
