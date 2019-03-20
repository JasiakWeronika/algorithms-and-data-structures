// import statements
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
/**
 * Singly linked list
 * @author          Weronika Jasiak
 * @version         1.0
 */
// comparison counters
int countMTF = 0;
int countTRANS = 0;
/**
 * Linked list node
 */
struct Node {
  int data;
  struct Node* next;
};

/**
 * Check if linked list is empty
 * @param node      reference to head of a list
 * @return          1 if list is empty, otherwise 0
 */
int isEmpty(struct Node* node) {
  if(node == NULL) {
    return 1;
  }
  return 0;
}

/* Insert a node at the front of a list
 * @param head      reference to head of a list
 * @param data      number to be put on the list
 */
void insert(struct Node** head, int data) {
  struct Node* node = (struct Node*)malloc(sizeof(struct Node));
  node -> data = data;
  node -> next = *head;
  *head = node;
}

/**
 * Insert a node after a given node
 * @param previous  reference to given node
 * @param data      number to be put on the list
 */
void insertAfter(struct Node* previous, int data) {
  if(previous == NULL) {
    return;
  }
  struct Node* node = (struct Node*)malloc(sizeof(struct Node));
  node -> data = data;
  node -> next = previous -> next;
  previous -> next = node;
}

/**
 * Insert a node at the end
 * @param head      reference to head of a list
 * @param data      number to be put on the list
 */
void insertEnd(struct Node** head, int data) {
  struct Node* node = (struct Node*)malloc(sizeof(struct Node));
  struct Node* last = *head;
  node -> data = data;
  node -> next = NULL;
  if(*head == NULL) {
    *head = node;
    return;
  }
  while(last -> next != NULL) {
    last = last -> next;
  }
  last -> next = node;
  return;
}

/**
 * Delete the first occurrence of key in linked list
 * @param head      reference to head of a list
 * @param key       number to be deleted
 */
void delete(struct Node** head, int key) {
  struct Node* temporary = *head, *previous;
  if(temporary != NULL && temporary -> data == key) {
    *head = temporary -> next;
    free(temporary);
    return;
  }
  while(temporary != NULL && temporary -> data != key) {
    previous = temporary;
    temporary = temporary -> next;
  }
  if(temporary == NULL) {
    return;
  }
  previous -> next = temporary -> next;
  free(temporary);
}

/**
 * Delete the node at given position
 * @param gead      reference to head of a list
 * @param position  item number to be deleted
 */
void deleteGiven(struct Node** head, int position) {
  if(*head == NULL) {
    return;
  }
  struct Node* temporary = *head;
  if(position == 0) {
    *head = temporary -> next;
    free(temporary);
    return;
  }
  for(int i = 0; temporary != NULL && i < position - 1; i++) {
    temporary = temporary -> next;
  }
  if(temporary == NULL || temporary -> next == NULL) {
    return;
  }
  struct Node* next = temporary -> next -> next;
  free(temporary -> next);
  temporary -> next = next;
}

/**
 * Delete the entire linked list
 * @param head      reference to head of a list
 */
void deleteList(struct Node** head) {
  struct Node* current = *head;
  struct Node* next;
  while(current != NULL) {
    next = current -> next;
    free(current);
    current = next;
  }
  *head = NULL;
}

/** If linked list contains x, move it to the front
 * @param node      reference to head of a list
 * @param x         searched number
 * @return          1 if list contains x, move it to the front, otherwise 0
 */
int findMTF(struct Node** node, int x) {
  countMTF += 1;
  if(*node == NULL) {
    return 0;
  } else if((*node) -> next == NULL && (*node) -> data == x) {
    countMTF += 2;
    return 1;
  } else if((*node) -> next == NULL) {
    countMTF += 1;
    return 1;
  } else if((*node) -> data == x) {
    countMTF += 1;
    return 1;
  }
  struct Node* previous = NULL;
  struct Node* current = *node;
  countMTF += 1;
  while(current != NULL) {
    countMTF += 3;
    if(current -> data == x && previous != NULL) {
      previous -> next = current -> next;
      current -> next = *node;
      *node = current;
      return 1;
    }
    previous = current;
    current = current -> next;
  }
  return 0;
}

/**
 * Swap two given pointers
 * @param a         pointer a
 * @param b         pointer b
 */
void swap(int* a, int* b) {
  int temporary;
  temporary = *b;
  *b = *a;
  *a = temporary;
}

/**
 * If linked list contains x, move it one position forward
 * @param node      reference to head of a list
 * @param x         searched number
 * @return          1 if list contains x, move it one position forward, otherwise 0
 */
int findTRANS(struct Node** node, int x) {
  countTRANS += 1;
  if(*node == NULL) {
    return 0;
  } else if((*node) -> next == NULL && (*node) -> data == x) {
    countTRANS += 2;
    return 1;
  } else if((*node) -> next == NULL) {
    countTRANS += 1;
    return 0;
  } else if((*node) -> data == x) {
    countTRANS += 1;
    return 1;
  }
  struct Node* previous = NULL;
  struct Node* current = *node;
  countTRANS += 1;
  while(current != NULL) {
    countTRANS += 3;
    if(current -> data == x && previous != NULL) {
      swap(&previous -> data, &current -> data);
      return 1;
    }
    previous = current;
    current = current -> next;
  }
  return 0;
}

/*
 * Print linked list starting from the head
 * @param node      reference to head of a list
*/
void printList(struct Node* node) {
  while(node != NULL) {
    printf("%d ", node -> data);
    node = node -> next;
  }
  printf("\n");
}

/*
 * Test
 */
int main(int argc, char const *argv[]) {
  int repeat = 1000;
  int mtf = 0;
  int trans = 0;
  srand(time(0));
  for(int a = 0; a < repeat; a++) {
    struct Node* headMTF = NULL;
    struct Node* headTRANS = NULL;
    int size = 100;
    int *arrayMTF = malloc(sizeof(int)*size+1);
    int *arrayTRANS = malloc(sizeof(int)*size+1);
    for(int i = 1; i <= size; i++) {
      arrayMTF[i] = i;
      arrayTRANS[i] = i;
    }
    for(int i = 1; i <= size - 1; i++) {
      int j = i + (rand() % (size - i));
      int x = arrayMTF[i];
      int y = arrayTRANS[i];
      arrayMTF[i] = arrayMTF[j];
      arrayTRANS[i] = arrayTRANS[j];
      arrayMTF[j] = x;
      arrayTRANS[j] = y;
    }
    for(int i = 1; i <= size; i++) {
      insert(&headMTF, arrayMTF[i]);
      insert(&headTRANS, arrayTRANS[i]);
    }
    //printList(headMTF);
    //printList(headTRANS);
    for(int i = 1; i <= size; i++) {
      if(isEmpty(headMTF) == 0) {
        findMTF(&headMTF, i);
        delete(&headMTF, size - i + 1);
        //printList(headMTF);
      }
      if(isEmpty(headTRANS) == 0) {
        findTRANS(&headTRANS, i);
        delete(&headTRANS, size - i + 1);
        //printList(headTRANS);
      }
    }
    //printf("findMTF %d\n", countMTF);
    //printf("findTRANS %d\n", countTRANS);
    mtf += countMTF;
    countMTF = 0;
    trans += countTRANS;
    countTRANS = 0;
  }
  mtf /= repeat;
  printf("findMTF %d\n", mtf);
  trans /= repeat;
  printf("findTRANS %d\n", trans);
  /*struct Node* head = NULL;
  if(isEmpty(head) == 1) {
    printf("1\n");
  } else {
    printf("0\n");
  }
  insert(&head, 1);
  insert(&head, 2);
  insert(&head, 3);
  insert(&head, 4);
  insert(&head, 5);
  insert(&head, 6);
  insert(&head, 7);
  printList(head);
  insertAfter(head -> next, 8);
  printList(head);
  insertEnd(&head, 9);
  printList(head);
  delete(&head, 8);
  printList(head);
  deleteGiven(&head, 3);
  printList(head);
  //deleteList(&head);
  if(isEmpty(head) == 1) {
    printf("1\n");
  } else {
    printf("0\n");
  }
  if(findMTF(&head, 3) == 1) {
    printf("1\n");
  } else {
    printf("0\n");
  }
  printList(head);
  if(findTRANS(&head, 5) == 1) {
    printf("1\n");
  } else {
    printf("0\n");
  }
  printList(head);
  */
  return 0;
}
