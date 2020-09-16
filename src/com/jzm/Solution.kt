package com.jzm

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if(l1 == null) return l2
        if(l2 == null) return l1

        var preHeader = ListNode(-1)
        var pre = preHeader

        var localL1 = l1
        var localL2 = l2

        while(localL1 != null && localL2 != null) {
            if(localL1.`val` < localL2.`val`) {
                pre.next = localL1
                localL1 = localL1.next
            } else {
                pre.next = localL2
                localL2 = localL2.next
            }

            pre = pre.next!!
        }

        return preHeader.next
    }
}