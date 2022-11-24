### Exercise 11

1. In my implementation, the guardian has 6 state, they're 2 mobile apps, 2 banks and 2 accounts.

   The message it must handle is KickOff.

   To start off the actor system, I create a new Guardian and send KickOff message to it to start the whole system. ![截屏2022-11-24 下午5.06.13](/Users/zq2lii/Library/Application Support/typora-user-images/截屏2022-11-24 下午5.06.13.png)

![截屏2022-11-24 下午5.07.18](/Users/zq2lii/Library/Application Support/typora-user-images/截屏2022-11-24 下午5.07.18.png)

2.  For the Account actor, each Account has a state called 'balance', which is the balance of this Account, it is initialized to 0 for each account. The message it must handle is *Deposite* message send from Bank actor. 

3. Bank actor doesn't maintain state in our implementation. The message it must handle with is *Transaction*, it will send 2 *Deposite* message to sender account and the receiver.

4. Mobile App also doesn't hold any state. The message it must handle with is *PaymentReq* which has 3 parameters: bank, Account_from and Account_to. It will make a random payment from Account_from to Account_to via the given bank.

5. finished.

6. finished. The mobileApp will generate 100 random amount, and then send 'Transaction' message to banks.

7. The balance printed is the one before all payments are made. I guess it is because this info printed before the message sent, and the code will print the most stable balance of each Account.

   ![截屏2022-11-24 下午5.16.58](/Users/zq2lii/Desktop/截屏2022-11-24 下午5.16.58.png)

8. I think there won't be a race condition in this case. Since each Thread(Actor) communicate with each other by sending message, and even if they send at the same time, the receive time counld be different, all of these messages will be stored in the receiver Actor's mailbox by some order. The order of these message may not be the same as they were sent, but there won't be a race condition.