package stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liangze
 * @create 2020-07-30 下午5:30
 */
public class StreamTest {


    /**
     * list转map
     * @param list
     * @return
     */
    public Map<String,Test> list2Map(List<Test> list){
        //根据name为k 实体为v
        Map<String,Test> result = list.stream().collect(Collectors.toMap(Test::getName, a -> a,(k1,k2)->k1));
        return result;
    }

    /**
     * 流处理去重并求和
     * @param list
     * @return
     */
    public List<Test> listDistinctAndSum(List<Test> list){
        //list去重  根据name去重 重复时age累加
        List<Test> result = list.stream().collect(Collectors.toMap(Test::getName, a -> a, (o1, o2) -> {
            o1.setAge(o1.getAge() + o2.getAge());
            return o1;
        })).values().stream().collect(Collectors.toList());
        return result;

    }

    /**
     * list排序 升序
     * @param list
     * @return
     */
    public List<Test> listSortAsc(List<Test> list){
        List<Test> result = list.stream().sorted(Comparator.comparing(Test::getAge)).collect(Collectors.toList());
        return result;
    }

    /**
     * list排序 降序
     * @param list
     * @return
     */
    public List<Test> listSortDesc(List<Test> list){
        List<Test> result = list.stream().sorted(Comparator.comparing(Test::getAge).reversed()).collect(Collectors.toList());
        return result;
    }

    /**
     * map根据k转list
     * @param map
     * @return
     */
    public List map2ListByKey(Map map){
        return new ArrayList(map.keySet());

    }

    /**
     * map根据v转list
     * @param map
     * @return
     */
    public List map2ListByValue(Map map){
        return new ArrayList(map.values());

    }

    /**
     * map转化为list
     * @param map
     * @return
     */
    public List<Test> map2ListByObject(Map<String,Integer> map){
        return map.entrySet().stream().map(e -> new Test(e.getKey(),e.getValue())).collect(Collectors.toList());
    }



    /**
     * 流处理筛选   筛选的是符合条件的
     * @param list
     * @return
     */
    public List<Test> listFilter(List<Test> list){
        List<Test> result = list.stream()
                .filter((Test test) ->  "name".equals(test.getName()))
                .collect(Collectors.toList());
        return result;

    }

    public static void main(String[] args) {
        StreamTest test = new StreamTest();
        List<Test> list =new ArrayList<>();
        list.add(new Test("a",12));
        list.add(new Test("b",13));
        System.out.println(test.listSortDesc(list));
    }

}
