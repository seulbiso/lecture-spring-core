package hello.core.order;

import hello.core.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;

@Getter
@Setter
//@RequiredArgsConstructor // final 변수 생성자 생성
public class OrderServiceImpl implements OrderService{
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new  FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //DiscountPolicy -> RateDiscountPolicy, FixDiscountPolicy 두개 존재, 구분한 방법 2가지
    //1. Autowired 구분 방법 -> 파라미터명 변경 DiscountPolicy discountPolicy -> DiscountPolicy rateDiscountPolicy
    //2. @Quailifier 사용 -> @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy
    //3. @Primary
    //4. annotation 만들기(추적가능한 장점) -> @MainDiscountPolicy Annotation 만들고, RateDiscountPolicy클래스에도 붙인상태, 가져다 쓰는곳도 @MainDiscountPolicy DiscountPolicy discountPolicy
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}
