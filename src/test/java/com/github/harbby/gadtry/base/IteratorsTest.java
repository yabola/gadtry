/*
 * Copyright (C) 2018 The Harbby Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.harbby.gadtry.base;

import com.github.harbby.gadtry.collection.mutable.MutableList;
import com.github.harbby.gadtry.collection.mutable.MutableSet;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IteratorsTest
{
    @Test
    public void ofCreateIteratorTest()
    {
        String[] number = new String[] {"1", "2", "3"};

        Iterator<String> iterator = Iterators.of(number);
        List<String> list = MutableList.copy(() -> iterator);

        Assert.assertArrayEquals(number, list.toArray(new String[0]));
    }

    @Test
    public void createEmptyIteratorTest()
    {
        Assert.assertFalse(Iterators.empty().hasNext());
        try {
            Iterators.empty().next();
            Assert.fail();
        }
        catch (NoSuchElementException ignored) {
        }
    }

    @Test
    public void isEmptyTest()
    {
        List list = new ArrayList();
        Assert.assertTrue(Iterators.isEmpty(list));
    }

    @Test
    public void iteratorIsEmptyTest()
    {
        Iterator<?> iterator = Iterators.empty();
        Assert.assertTrue(Iterators.isEmpty(iterator));
    }

    @Test
    public void getLastTestByIterator()
    {
        List<String> list = Arrays.asList("1", "2", "3");
        String last = Iterators.getLast(list.iterator());
        Assert.assertEquals(list.get(list.size() - 1), last);

        try {
            Iterators.getLast(Iterators.empty());
            Assert.fail();
        }
        catch (NoSuchElementException ignored) {
        }
    }

    @Test
    public void getLastTestDefaultValueByIterator()
    {
        String last = Iterators.getLast(new ArrayList<>(), "-1");
        Assert.assertEquals("-1", last);
    }

    @Test
    public void getLastTestDefaultValueByIterator2()
    {
        String last = Iterators.getLast(MutableSet.of("123"), "-1");
        Assert.assertEquals("123", last);
    }

    @Test
    public void TestIterableGetLastByList()
    {
        List<String> list = Arrays.asList("1", "2", "3");
        String last = Iterators.getLast(list);
        Assert.assertEquals(list.get(list.size() - 1), last);

        try {
            Iterators.getLast(new ArrayList<>());
            Assert.fail();
        }
        catch (NoSuchElementException e) {
        }
    }

    @Test
    public void TestIterableGetLastDefaultValueByList()
    {
        List<String> list = new ArrayList<>();
        String last = Iterators.getLast(list, "-1");
        Assert.assertEquals("-1", last);
    }

    @Test
    public void iteratorSizeTestReturn3()
    {
        List<String> list = Arrays.asList("1", "2", "3");
        Assert.assertEquals(3, Iterators.size(list.iterator()));
    }

    @Test
    public void iteratorMapTest()
    {
        List<String> list = Arrays.asList("1", "2", "3");
        Iterator iterator = Iterators.map(list.iterator(), x -> Integer.parseInt(x) + 1);
        Assert.assertEquals(new Integer[] {2, 3, 4}, MutableList.copy(() -> iterator).toArray(new Integer[0]));
    }

    @Test
    public void iteratorMapperReduceTest()
    {
        List<String> list = Arrays.asList("1", "2", "3");
        int sum = Iterators.reduce(list.iterator(), x -> Integer.parseInt(x), (x, y) -> x + y);
        Assert.assertEquals(6, sum);
    }

    @Test
    public void limit()
    {
        List<String> list = Arrays.asList("1", "2", "3");
        List<String> limit = MutableList.copy(() -> Iterators.limit(list.iterator(), 1));
        Assert.assertEquals(Arrays.asList("1"), limit);
    }
}