package main

import (
	"fmt"
	"log"
	"strconv"
)

func main() {
	fmt.Println("Testing.")
	myNode := BinaryNode{1, nil, nil}
	fmt.Println(myNode.value)
	myNode.insert(2)
	myNode.insert(5)
	myNode.insert(3)
	myNode.insert(7)
	fmt.Println(myNode.InfixString())
}

type BinaryNode struct {
	value int
	left  *BinaryNode
	right *BinaryNode
}

func (node BinaryNode) InfixString() string {
	var leftString, rightString string
	if node.left != nil {
		leftString = node.left.InfixString()
	} else {
		leftString = ""
	}

	if node.right != nil {
		rightString = node.right.InfixString()
	} else {
		rightString = ""
	}

	return leftString + strconv.Itoa(node.value) + rightString
}

func (curNode *BinaryNode) insert(value int) *BinaryNode {
	nextNode := curNode

	if curNode == nil {
		nextNode = &BinaryNode{value, nil, nil}
		fmt.Println("current is null")
	} else if curNode.value > value {
		curNode.left = curNode.left.insert(value)
		fmt.Println("current is greater than")
	} else if curNode.value < value {
		curNode.right = curNode.right.insert(value)
		fmt.Println("current is less than")
	} else {
		log.Fatal("No duplicates silly...")
		nextNode = nil
	}

	return nextNode
}
