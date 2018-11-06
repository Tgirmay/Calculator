import java.util.ArrayList;

public class MyLong {
    public ArrayList<Integer> numberContainer = new ArrayList<Integer>();
    public boolean negative = false;
    public boolean zero = false;

    public void setLong(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && ("" + s.charAt(i)) == "0") {
                i = i;
            } else {
                Integer digit = Integer.parseInt("" + s.charAt(i));
                this.numberContainer.add(digit);
            }
        }
    }

    public int compareTo(MyLong l) {
        if (this.numberContainer.size() > l.numberContainer.size()) {
            return 1;
        } else if (this.numberContainer.size() < l.numberContainer.size()) {
            return -1;
        } else {
            for (int i = 0; i < this.numberContainer.size(); i++) {
                if (this.numberContainer.get(i) > l.numberContainer.get(i)) {
                    return 1;
                } else if (this.numberContainer.get(i) < l.numberContainer.get(i)) {
                    return -1;
                }
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        String stringLong = "";
        for (int i = 0; i < numberContainer.size(); i++) {
            stringLong = stringLong + numberContainer.get(i).toString();
        }
        if (this.negative) {
            stringLong = "-" + stringLong;
        }

        return stringLong;
    }

    public void ceckFirstDigit() {
        if (this.numberContainer.get(0) == 0) {//if the first number is a 0, get rid of it
            this.numberContainer.remove(0);
        }
    }

    public MyLong reverse() {
        MyLong revLng = new MyLong();
        for (int i = (this.numberContainer.size() - 1); i > -1; i--) {
            revLng.numberContainer.add(this.numberContainer.get(i));
        }
        return revLng;
    }

    public MyLong add(MyLong long2) {
        MyLong long1 = this.reverse();//numbers need to be reversed in order to operate
        MyLong resultLong = new MyLong();//long that will be returned is set
        long2 = long2.reverse();
        int carry = 0;//carry bit
        String result = new String();//this will keep track of the string that needs to be set as a MyLong
        boolean endOfNumber = false;

        //if only one is negative, subtract negative from positive
        if (long1.negative ^= long2.negative) {
            if (long1.negative) {
                return long2.subtract(long1);
            } else {
                return long1.subtract(long2);

            }
        }

        //if the first number is larger, go down this path
        if (long1.numberContainer.size() > long2.numberContainer.size()) {
            //adds each number by column (index)
            for (int i = 0; i < long1.numberContainer.size(); i++) {
                //checks if end of num has been reached so that the rest of the neumbers can be put in + carry
                if (endOfNumber) {
                    //is the result greater than 10? yes: sets carry no: does not. also adds previous carry
                    if (long1.numberContainer.get(i) + carry >= 10) {
                        result = result + (long1.numberContainer.get(i) + carry - 10);
                        carry = 1;
                    } else {
                        result = result + (long1.numberContainer.get(i) + carry);
                        carry = 0;

                    }
                } else {
                    if (i == long2.numberContainer.size()) {
                        //is the result greater than 10? yes: sets carry no: does not. also adds previous carry
                        if (long1.numberContainer.get(i) + carry >= 10) {
                            result = result + (long1.numberContainer.get(i) + carry - 10);
                            endOfNumber = true;
                            carry = 1;
                        } else {
                            result = result + (long1.numberContainer.get(i) + carry);
                            endOfNumber = true;
                            carry = 0;

                        }

                    }
                    //is the result greater than 10? yes: sets carry no: does not. also adds previous carry
                    else if (long1.numberContainer.get(i) + long2.numberContainer.get(i) >= 10) {
                        result = result + (long1.numberContainer.get(i) + long2.numberContainer.get(i) + carry - 10);
                        carry = 1;
                    } else {
                        result = result + (long1.numberContainer.get(i) + long2.numberContainer.get(i) + carry);
                        carry = 0;
                    }
                }
            }
        } else if (long1.numberContainer.size() < long2.numberContainer.size()) {

            for (int i = 0; i < long2.numberContainer.size(); i++) {

                if (endOfNumber) {
                    //is the result greater than 10? yes: sets carry no: does not. also adds previous carry
                    if (long2.numberContainer.get(i) + carry >= 10) {
                        result = result + (long2.numberContainer.get(i) + carry - 10);
                        carry = 1;
                    } else {
                        result = result + (long2.numberContainer.get(i) + carry);
                        carry = 0;

                    }
                } else {
                    if (i == long1.numberContainer.size()) {
                        //is the result greater than 10? yes: sets carry no: does not. also adds previous carry
                        if (long2.numberContainer.get(i) + carry >= 10) {
                            result = result + (long2.numberContainer.get(i) + carry - 10);
                            endOfNumber = true;
                            carry = 1;
                        } else {
                            result = result + (long2.numberContainer.get(i) + carry);
                            endOfNumber = true;
                            carry = 0;

                        }

                    }
                    //is the result greater than 10? yes: sets carry no: does not. also adds previous carry
                    else if (long2.numberContainer.get(i) + long1.numberContainer.get(i) >= 10) {
                        result = result + (10 + long2.numberContainer.get(i) + long1.numberContainer.get(i) + carry);
                        carry = 1;
                    } else {
                        result = result + (long2.numberContainer.get(i) + long1.numberContainer.get(i) + carry);
                        carry = 0;
                    }
                }
            }
        } else {
            for (int i = 0; i < long2.numberContainer.size(); i++) {
                //is the result greater than 10? yes: sets carry no: does not. also adds previous carry
                if (long1.numberContainer.get(i) + long2.numberContainer.get(i) >= 10) {
                    result = result + (long1.numberContainer.get(i) + long2.numberContainer.get(i) + carry - 10);
                    carry = 1;

                } else {
                    result = result + (long1.numberContainer.get(i) + long2.numberContainer.get(i) + carry);
                    carry = 0;

                }
            }

        }
        //if carry is left at end, add it at end
        if (carry == 1) {
            result += 1;
        }

        resultLong.setLong(result);//long is set to the result string
        resultLong = resultLong.reverse();//answer is reversed for readability
        resultLong.ceckFirstDigit();//removes first digit if it is 0
        return resultLong;
    }


    public MyLong subtract(MyLong long2) {
        MyLong long1 = this.reverse();//numbers must be reversed in order to operate
        long2 = long2.reverse();
        MyLong resultLong = new MyLong();//long that will be returned is set
        Integer carry = 0;//carry bit for subtracting
        String result = new String();//will carry info of result which will be set as a long
        boolean endOfNumber = false;//is it the end of number?
        boolean resultIsNeg = false;//should this number be negative?

        //if the first number is larger, go down this path
        if (long1.numberContainer.size() > long2.numberContainer.size()) {

            for (int i = 0; i < long1.numberContainer.size(); i++) {

                if (endOfNumber) {
                    //is the result less than 0? yes: sets carry no: does not. also adds previous carry
                    if (long1.numberContainer.get(i) - carry < 0) {
                        //this block adds the cutrrent index, takes out a 1 if it was needed to for the last index, and adds 10 because a carry is needed.
                        result = result + (long1.numberContainer.get(i) - carry + 10);
                        endOfNumber = true;
                        carry = 1;
                    } else {
                        //this block just adds the current index and takes the carry off
                        result = result + (long1.numberContainer.get(i) - carry);
                        endOfNumber = true;
                        carry = 0;

                    }
                } else {
                    if (i == long2.numberContainer.size()) {
                        //is the result less than 0? yes: sets carry no: does not. also adds previous carry
                        if (long1.numberContainer.get(i) - carry < 0) {
                            result = result + (long1.numberContainer.get(i) - carry + 10);
                            endOfNumber = true;
                            carry = 1;
                        } else {
                            result = result + (long1.numberContainer.get(i) - carry);
                            endOfNumber = true;
                            carry = 0;

                        }

                    }
                    //is the result less than 0? yes: sets carry no: does not. also adds previous carry
                    else if (long1.numberContainer.get(i) - long2.numberContainer.get(i) < 0) {
                        result = result + (10 + long1.numberContainer.get(i) - long2.numberContainer.get(i) - carry);
                        carry = 1;
                    } else {
                        result = result + (long1.numberContainer.get(i) - long2.numberContainer.get(i) - carry);
                        carry = 0;
                    }
                }
            }
        } else if (long1.numberContainer.size() < long2.numberContainer.size()) {
            resultIsNeg = true;

            for (int i = 0; i < long2.numberContainer.size(); i++) {

                if (endOfNumber) {
                    if (long2.numberContainer.get(i) - carry < 0) {
                        //is the result less than 0? yes: sets carry no: does not. also adds previous carry
                        result = result + (long2.numberContainer.get(i) - carry + 10);
                        carry = 1;
                    } else {
                        //is the result less than 0? yes: sets carry no: does not. also adds previous carry
                        result = result + (long2.numberContainer.get(i) - carry);
                        carry = 0;

                    }
                } else {
                    if (i == long1.numberContainer.size()) {
                        //is the result less than 0? yes: sets carry no: does not. also adds previous carry
                        if (long2.numberContainer.get(i) - carry < 0) {
                            result = result + (long2.numberContainer.get(i) - carry + 10);
                            endOfNumber = true;
                            carry = 1;
                        } else {
                            result = result + (long2.numberContainer.get(i) - carry);
                            endOfNumber = true;
                            carry = 0;

                        }

                    }

                    //is the result less than 0? yes: sets carry no: does not. also adds previous carry
                    else if (long2.numberContainer.get(i) - long1.numberContainer.get(i) < 0) {
                        result = result + (10 + long2.numberContainer.get(i) - long1.numberContainer.get(i) - carry);
                        carry = 1;
                    } else {
                        result = result + (long2.numberContainer.get(i) - long1.numberContainer.get(i) - carry);
                        carry = 0;
                    }
                }
            }
        } else {
            //lengths are equal so we make long1 be the larger long. Set negative to be true if the switch needed to occur.
            if (long1.compareTo(long2) == -1) {
                MyLong temp = long2;
                long2 = long1;
                long1 = temp;
                resultIsNeg = true;
            }
            for (int i = 0; i < long2.numberContainer.size(); i++) {
                //is the result less than 0? yes: sets carry no: does not. also adds previous carry
                if (long1.numberContainer.get(i) - long2.numberContainer.get(i) < 0) {
                    result = result + (long1.numberContainer.get(i) - long2.numberContainer.get(i) - carry + 10);
                    carry = 1;

                } else {
                    result = result + (long1.numberContainer.get(i) - long2.numberContainer.get(i) - carry);
                    carry = 0;

                }
            }
            //if the last digit needed a carry
        }

        resultLong.setLong(result);
        resultLong = resultLong.reverse();
        if (resultIsNeg) {
            resultLong.negative = true;
        }
        resultLong.ceckFirstDigit();
        return resultLong;
    }


    public MyLong multiply(MyLong long2) {
        MyLong long1 = this.reverse();
        long2 = long2.reverse();
        Integer carry = 0;
        MyLong resultLong = new MyLong();

        //flop numbers so that the longer one is always long2
        if (long1.numberContainer.size() < long2.numberContainer.size()) {
            MyLong temp = long2;
            long2 = long1;
            long1 = temp;
        }

        for (int i = 0; i < long2.numberContainer.size(); i++) {
            MyLong lineToAdd = new MyLong();//used to keep track of the current line of multiplied numbers as a long
            String result = new String();//used to keep track of the current line of multiplied numbers as a string

            for (int z = 0; z < i; ++z) {
                result += "0";
            }

            //multiply current digit of long2 with all digits of long1
            for (int j = 0; j < long1.numberContainer.size(); j++) {

                if ((long1.numberContainer.get(j) * long2.numberContainer.get(i) + carry) >= 10) {//if the multiplication yields a result > 10
                    String temp = Integer.toString((long1.numberContainer.get(j) * long2.numberContainer.get(i) + carry)); //temp is the result
                    result += (Integer.parseInt("" + temp.charAt(1)));//second is added to the result line tracker along with the carry
                    carry = Integer.parseInt("" + temp.charAt(0));//first digit is carry
                } else {
                    result += (long1.numberContainer.get(j) * long2.numberContainer.get(i) + carry);
                    carry = 0;
                }

            }

            if (carry > 0) {
                result += carry;
                carry = 0;
            }

            lineToAdd.setLong(result);
            lineToAdd = lineToAdd.reverse();
            resultLong = resultLong.add(lineToAdd);
        }
        //get rid of the leading zeros
        while ((resultLong.numberContainer.get(0) == 0) && resultLong.numberContainer.size() > 1) {
            resultLong.ceckFirstDigit();
        }

        //set negative if only one is negative
        if (this.negative ^= long1.negative) {
            resultLong.negative = true;
        }
        return resultLong;
    }
}


/*
    public static void main(String[] args) {
        MyLong dong = new MyLong();
        dong.setLong("11");
        System.out.println("First " + dong);

       MyLong dong2 = new MyLong();
        dong2.setLong("11");
        System.out.println("Second: " + dong2);

        MyLong dong3 = dong.multiply(dong2);
        System.out.println("Product " + dong3);

        MyLong dong4 = dong.add(dong2);
        System.out.println("Addition " + dong4);

       // MyLong dong5 = dong.subtract(dong2);
       // System.out.println("Subtraction " + dong5);


    }
}
*/