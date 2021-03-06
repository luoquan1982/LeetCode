import java.util.ArrayList;
import java.util.List;

/**
 * @Author luoquan
 * @Email 37629327@qq.com luo.quan@aliyun.com
 * @Description
 * @Date 2019/3/2 15:31
 */
public class DisappearedNumbersLuoQuan {

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            nums[index] = Math.abs(nums[index]) * -1;
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                result.add(i + 1);
        }
        return result;
    }
}
