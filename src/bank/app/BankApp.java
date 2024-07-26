package bank.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankApp {
	private static Scanner scanner = new Scanner(System.in);
	private static List<Account> accounts = new ArrayList<>();
	
	public static void main(String[] args) {
		boolean run = true;
		
		while(run) {
			System.out.println("---------------------------------------------");
			System.out.println("1. 계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
			System.out.println("---------------------------------------------");
			System.out.print("선택> ");
			
			int selectNo = Integer.parseInt(scanner.nextLine());
			if(selectNo == 1)
			{
				createAccount();
			}else if(selectNo == 2)
			{
				accountList();
			}else if(selectNo == 3)
			{
				deposit();
			}else if(selectNo == 4)
			{
				withdraw();
			}else if(selectNo == 5)
			{
				run = false;
			}
		}
		
		System.out.println("프로그램 종료");
	}
	
	// 계좌생성 메서드
	private static void createAccount() {
		System.out.println("--------------------계좌생성--------------------");
		
		try {
			// 계좌 정보 입력
			System.out.print("계좌번호: ");
			String ano = scanner.nextLine();
			System.out.print("계좌주: ");
			String owner = scanner.nextLine();
			System.out.print("초기입금액: ");
			int balance = Integer.parseInt(scanner.nextLine());
			
			
			// 계저 생성 후 리스트 주입
			Account newAccount = new Account(ano, owner, balance);
			accounts.add(newAccount);
			
			// 결과 출력
			System.out.println("결과: 계좌가 생성되었습니다.");
			
		} catch (NumberFormatException e) {
			// 입금액에 문자를 입력하면 예외처리
			System.out.println("결과: 계좌생성에 실패했습니다.");
		}
		
	};
	
	// 계좌목록 메서드
	private static void accountList() {
		System.out.println("--------------------계좌목록--------------------");
		// 계좌 목록 출력
		accounts.stream()
				.forEach((account)->{
					System.out.println(account.getAno() + " " + account.getOwner() + " " + account.getBalance());
					});
	}
	
	// 입금 메서드
	private static void deposit() {
		System.out.println("--------------------예금--------------------");
		
		try {
			// 입금할 계좌, 예금액 입력
			System.out.print("계좌번호: ");
			String ano = scanner.nextLine();
			System.out.print("예금액: ");
			int balance = Integer.parseInt(scanner.nextLine());
			
			// 계좌 검색
			Account foundAccount = findAccount(ano);
			
			// 찾은 계좌가 없을 시 종료
			if(foundAccount == null)
			{
				System.out.println("결과: 해당 계좌가 없습니다.");
				return;
			}
			
			// 계좌가 있다면 예금 성공
			int originBanlance = foundAccount.getBalance();
			foundAccount.setBalance(originBanlance + balance);
			System.out.println("결과: 예금이 성공되었습니다.");
			
		} catch (NumberFormatException e) {
			// 예금액에 문자를 입력하면 예외처리
			System.out.println("결과: 예금이 실패되었습니다.");
		}
		
	}
	// 출금 메서드
	private static void withdraw() {
		System.out.println("--------------------예금--------------------");
		try {
			// 출금할 계좌, 예금액 입력
			System.out.print("계좌번호: ");
			String ano = scanner.nextLine();
			System.out.print("출금액: ");
			int balance = Integer.parseInt(scanner.nextLine());
			
			// 계좌 검색
			Account foundAccount = findAccount(ano);
			
			// 찾은 계좌가 없을 시 종료
			if(foundAccount == null)
			{
				System.out.println("결과: 해당 계좌가 없습니다.");
				return;
			}
			
			// 계좌가 있다면 출금 시도
			int originBalance = foundAccount.getBalance();
			int remainBalance = originBalance - balance;
			
			// 만약 출금 후 금액이 0보다 작으면 출금 실패
			if(remainBalance < 0)
			{
				System.out.println("결과: 출금액이 기존 금액보다 많습니다.");
				return; 
			}
			
			// 출금 실행
			foundAccount.setBalance(remainBalance);
			System.out.println("결과: 출금이 성공되었습니다.");
			
		} catch (NumberFormatException e) {
			// 출금액에 문자를 입력하면 예외처리
			System.out.println("결과: 출금이 실패되었습니다.");
		}
		
	}
	
	// 계좌검색 메서드
	private static Account findAccount(String ano) { 
		return accounts.stream().filter(account->account.getAno().equals(ano)).findFirst().orElse(null);
		}
}
